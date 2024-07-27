package br.com.microservices.orchestrated.inventoryservice.core.services.impl;

import br.com.microservices.orchestrated.inventoryservice.core.dtos.Event;
import br.com.microservices.orchestrated.inventoryservice.core.dtos.History;
import br.com.microservices.orchestrated.inventoryservice.core.dtos.Order;
import br.com.microservices.orchestrated.inventoryservice.core.dtos.OrderProduct;
import br.com.microservices.orchestrated.inventoryservice.core.enums.ESagaStatus;
import br.com.microservices.orchestrated.inventoryservice.core.model.Inventory;
import br.com.microservices.orchestrated.inventoryservice.core.model.OrderInventory;
import br.com.microservices.orchestrated.inventoryservice.core.producer.KafkaProducer;
import br.com.microservices.orchestrated.inventoryservice.core.repository.InventoryRepository;
import br.com.microservices.orchestrated.inventoryservice.core.repository.OrderInventoryRepository;
import br.com.microservices.orchestrated.inventoryservice.core.services.InventoryService;
import br.com.microservices.orchestrated.inventoryservice.core.utils.JsonUtil;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author aaristides
 */
@Service
@Slf4j
@AllArgsConstructor
public class DefaultInventoryService implements InventoryService {

    private static final String CURRENT_SOURCE = "INVENTORY_SERVICE";

    private final JsonUtil jsonUtil;
    private final KafkaProducer kafkaProducer;
    private final InventoryRepository inventoryRepository;
    private final OrderInventoryRepository orderInventoryRepository;

    @Override
    public void updateInventory(final Event event) {
        try {
            checkCurrentValidation(event);
            createOrderInventory(event);
            updateInventory(event.getPayload());
            handleSuccess(event);
        } catch (Exception e) {
            log.error("Error trying to update inventory: ", e);
            handleFailCurrentNotExecuted(event, e.getMessage());
        }
        kafkaProducer.sendEvent(jsonUtil.toJson(event));
    }

    @Override
    public void rollbackInventory(final Event event) {
        event.setStatus(ESagaStatus.FAIL);
        event.setSource(CURRENT_SOURCE);
        try {
            returnInventoryToPreviousValue(event);
            addHistory(event, "Rollback executed for inventory.");
        } catch (Exception e) {
            addHistory(event, "Rollback not executed for inventory: ".concat(e.getMessage()));
        }
        kafkaProducer.sendEvent(jsonUtil.toJson(event));
    }

    private void returnInventoryToPreviousValue(final Event event) {
        orderInventoryRepository.findByOrderIdAndTransactionId(event.getPayload().getId(), event.getTransactionId())
                .forEach(orderInventory -> {
                    var inventory = orderInventory.getInventory();
                    inventory.setAvailable(orderInventory.getOldQuantity());
                    inventoryRepository.save(inventory);
                    log.info("Restored inventory for order {} from {} to {}", event.getPayload().getId(), orderInventory.getNewQuantity(), inventory.getAvailable());
                });
    }

    private void updateInventory(final Order order) {
        order
                .getProducts()
                .forEach(product -> {
                   var inventory = findInventoryByProductCode(product.getProduct().getCode());
                   checkInventory(inventory.getAvailable(), product.getQuantity());
                   inventory.setAvailable(inventory.getAvailable() - product.getQuantity());
                   inventoryRepository.save(inventory);
                });
    }

    private void checkInventory(final int available, final int orderQuantity) {
        if (orderQuantity > available) {
            throw new ValidationException("Product is out of stock");
        }
    }

    private void createOrderInventory(final Event event) {
        event
                .getPayload()
                .getProducts()
                .forEach(product -> {
                    var inventory = findInventoryByProductCode(product.getProduct().getCode());
                    var orderInventory = createOrderInventory(event, product, inventory);
                    orderInventoryRepository.save(orderInventory);
                });
    }

    private OrderInventory createOrderInventory(final Event event, final OrderProduct product, final Inventory inventory) {
        return OrderInventory.builder()
                .inventory(inventory)
                .oldQuantity(inventory.getAvailable())
                .orderQuantity(product.getQuantity())
                .newQuantity(inventory.getAvailable() - product.getQuantity())
                .orderId(event.getPayload().getId())
                .transactionId(event.getTransactionId())
                .build();
    }

    private void checkCurrentValidation(final Event event) {
        if (orderInventoryRepository.existsByOrderIdAndTransactionId(event.getOrderId(), event.getTransactionId())) {
            throw new ValidationException("There's another transactionId for this validation.");
        }
    }

    private Inventory findInventoryByProductCode(final String productCode) {
        return inventoryRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ValidationException("Inventory not found by informed product: " + productCode));
    }

    private void handleSuccess(final Event event) {
        event.setStatus(ESagaStatus.SUCCESS);
        event.setSource(CURRENT_SOURCE);
        addHistory(event, "Inventory updated successfully!");
    }

    private void handleFailCurrentNotExecuted(final Event event, final String message) {
        event.setStatus(ESagaStatus.ROLLBACK_PENDING);
        event.setSource(CURRENT_SOURCE);
        addHistory(event, "Fail to update inventory: ".concat(message));
    }

    private void addHistory(final Event event, final String message) {
        final var history = History
                .builder()
                .source(event.getSource())
                .status(event.getStatus())
                .message(message)
                .createdAt(LocalDateTime.now())
                .build();
        event.addToHistory(history);
    }

}

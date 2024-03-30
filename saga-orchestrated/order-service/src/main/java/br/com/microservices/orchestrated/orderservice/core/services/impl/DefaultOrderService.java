package br.com.microservices.orchestrated.orderservice.core.services.impl;

import br.com.microservices.orchestrated.orderservice.core.documents.Event;
import br.com.microservices.orchestrated.orderservice.core.documents.Order;
import br.com.microservices.orchestrated.orderservice.core.dtos.OrderRequest;
import br.com.microservices.orchestrated.orderservice.core.producer.SagaProducer;
import br.com.microservices.orchestrated.orderservice.core.repository.OrderRepository;
import br.com.microservices.orchestrated.orderservice.core.services.EventService;
import br.com.microservices.orchestrated.orderservice.core.services.OrderService;
import br.com.microservices.orchestrated.orderservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author aaristides
 */
@Service
@AllArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository repository;
    private final JsonUtil jsonUtil;
    private final SagaProducer producer;
    private final EventService eventService;

    private static final String TRANSACTION_ID_PATTRN = "%s_%s";

    @Override
    public Order create(final OrderRequest request) {
        final var order = Order
                .builder()
                .products(request.getProducts())
                .createdAt(LocalDateTime.now())
                .transactionId(
                        String.format(TRANSACTION_ID_PATTRN, Instant.now().toEpochMilli(), UUID.randomUUID())
                )
                .build();
        repository.save(order);
        producer.sendEvent(jsonUtil.toJson(createPayload(order)));
        return order;
    }

    private Event createPayload(final Order order) {
        final var event = Event
                .builder()
                .orderId(order.getId())
                .transactionId(order.getTransactionId())
                .payload(order)
                .createdAt(LocalDateTime.now())
                .build();
        return eventService.save(event);
    }
}

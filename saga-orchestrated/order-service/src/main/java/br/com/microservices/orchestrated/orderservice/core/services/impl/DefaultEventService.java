package br.com.microservices.orchestrated.orderservice.core.services.impl;

import br.com.microservices.orchestrated.orderservice.config.exceptions.ValidationException;
import br.com.microservices.orchestrated.orderservice.core.documents.Event;
import br.com.microservices.orchestrated.orderservice.core.dtos.EventFilters;
import br.com.microservices.orchestrated.orderservice.core.repository.EventRepository;
import br.com.microservices.orchestrated.orderservice.core.services.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * @author aaristides
 */
@Service
@AllArgsConstructor
@Slf4j
public class DefaultEventService implements EventService {

    private final EventRepository repository;

    @Override
    public Event save(final Event event) {
        return repository.save(event);
    }

    @Override
    public void notifyEnding(final Event event) {
        event.setOrderId(event.getOrderId());
        event.setCreatedAt(LocalDateTime.now());
        save(event);
        log.info("Order {} with saga notified! TransactionId: {}", event.getOrderId(), event.getTransactionId());
    }

    @Override
    public List<Event> findAll() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Event findByFilters(final EventFilters filters) {
        validateEmptyFilters(filters);
        if (!isEmpty(filters.getOrderId())) {
            return repository.findTop1ByOrderIdOrderByCreatedAtDesc(filters.getOrderId())
                    .orElseThrow(() -> new ValidationException("Event not found by orderId."));
        } else {
            return repository.findTop1ByTransactionIdOrderByCreatedAtDesc(filters.getTransactionId())
                    .orElseThrow(() -> new ValidationException("Event not found by transactionId."));
        }
    }



    private void validateEmptyFilters(final EventFilters filters) {
        if (isEmpty(filters.getOrderId()) && isEmpty(filters.getTransactionId())) {
            throw new ValidationException("OrderId or TransactionId must be informed.");
        }
    }
}

package br.com.microservices.orchestrated.orderservice.core.services;

import br.com.microservices.orchestrated.orderservice.core.documents.Event;
import br.com.microservices.orchestrated.orderservice.core.dtos.EventFilters;

import java.util.List;

/**
 * @author aaristides
 */
public interface EventService {

    Event save(final Event event);

    void notifyEnding(final Event event);

    List<Event> findAll();

    Event findByFilters(final EventFilters filters);

}

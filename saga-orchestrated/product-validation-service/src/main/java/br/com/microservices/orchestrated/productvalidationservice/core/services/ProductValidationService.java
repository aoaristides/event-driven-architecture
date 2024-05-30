package br.com.microservices.orchestrated.productvalidationservice.core.services;

import br.com.microservices.orchestrated.productvalidationservice.core.dtos.Event;

/**
 * @author aaristides
 */
public interface ProductValidationService {

    void validateExistingProducts(final Event event);

    void rollbackEvent(final Event event);

}

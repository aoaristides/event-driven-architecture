package br.com.microservices.orchestrated.orderservice.core.services;

import br.com.microservices.orchestrated.orderservice.core.documents.Event;
import br.com.microservices.orchestrated.orderservice.core.documents.Order;
import br.com.microservices.orchestrated.orderservice.core.dtos.OrderRequest;

/**
 * @author aaristides
 */
public interface OrderService {

    Order create(final OrderRequest request);

}

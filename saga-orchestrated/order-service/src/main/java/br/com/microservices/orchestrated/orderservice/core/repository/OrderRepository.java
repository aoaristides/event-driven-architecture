package br.com.microservices.orchestrated.orderservice.core.repository;

import br.com.microservices.orchestrated.orderservice.core.documents.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author aaristides
 */
public interface OrderRepository extends MongoRepository<Order, String> {
}

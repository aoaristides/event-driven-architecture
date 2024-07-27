package br.com.microservices.orchestrated.inventoryservice.core.repository;

import br.com.microservices.orchestrated.inventoryservice.core.model.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {

    boolean existsByOrderIdAndTransactionId(final String orderId, final String transactionId);

    List<OrderInventory> findByOrderIdAndTransactionId(final String orderId, final String transactionId);

}

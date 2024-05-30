package br.com.microservices.orchestrated.productvalidationservice.core.repository;

import br.com.microservices.orchestrated.productvalidationservice.core.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author aaristides
 */
public interface ValidationRepository extends JpaRepository<Validation, Integer> {

    boolean existsByOrderIdAndTransactionId(final String orderId, final String transactionId);

    Optional<Validation> findByOrderIdAndTransactionId(final String orderId, final String transactionId);

}

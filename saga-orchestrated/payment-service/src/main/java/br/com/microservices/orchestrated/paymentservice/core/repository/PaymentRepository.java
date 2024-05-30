package br.com.microservices.orchestrated.paymentservice.core.repository;

import br.com.microservices.orchestrated.paymentservice.core.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author aaristides
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    boolean existsByOrderIdAndTransactionId(final String orderId, final String transactionId);

    Optional<Payment> findByOrderIdAndTransactionId(final String orderId, final String transactionId);

}

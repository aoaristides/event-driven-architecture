package br.com.microservices.orchestrated.paymentservice.core.services;

import br.com.microservices.orchestrated.paymentservice.core.dtos.Event;

/**
 * @author aaristides
 */
public interface PaymentService {

    void realizePayment(final Event event);

    void realizeRefund(final Event event);

}

package br.com.makersweb.mscustomer.application.customer.create;

import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import br.com.makersweb.mscustomer.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public class DefaultCreateCustomerUseCase extends CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultCreateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public Either<Notification, CreateCustomerOutput> execute(final CreateCustomerCommand aCommand) {
        return null;
    }
}

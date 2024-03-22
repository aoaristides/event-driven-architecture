package br.com.makersweb.mscustomer.application.customer.create;

import br.com.makersweb.mscustomer.domain.customer.Customer;
import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import br.com.makersweb.mscustomer.domain.validation.handler.Notification;
import io.vavr.control.Either;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

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
        final var name = aCommand.name();
        final var document = aCommand.document();
        final var type = aCommand.type();
        final var phoneNumber = aCommand.phoneNumber();
        final var birthDate = aCommand.birthDate();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();
        final var aCustomer = Customer.newCustomer(name, document, type, phoneNumber, birthDate, isActive);

        return notification.hasError() ? Left(notification) : create(aCustomer);
    }

    private Either<Notification, CreateCustomerOutput> create(final Customer aCustomer) {
        return Try(() -> this.customerGateway.create(aCustomer))
                .toEither()
                .bimap(Notification::create, CreateCustomerOutput::from);
    }

}

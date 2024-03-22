package br.com.makersweb.mscustomer.application.address.create;

import br.com.makersweb.mscustomer.domain.address.Address;
import br.com.makersweb.mscustomer.domain.address.AddressGateway;
import br.com.makersweb.mscustomer.domain.customer.Customer;
import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import br.com.makersweb.mscustomer.domain.customer.CustomerID;
import br.com.makersweb.mscustomer.domain.exceptions.NotFoundException;
import br.com.makersweb.mscustomer.domain.validation.Error;
import br.com.makersweb.mscustomer.domain.validation.ValidationHandler;
import br.com.makersweb.mscustomer.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

/**
 * @author aaristides
 */
public class DefaultCreateAddressUseCase extends CreateAddressUseCase {

    private final AddressGateway addressGateway;
    private final CustomerGateway customerGateway;

    public DefaultCreateAddressUseCase(final AddressGateway addressGateway, final CustomerGateway customerGateway) {
        this.addressGateway = addressGateway;
        this.customerGateway = customerGateway;
    }

    @Override
    public Either<Notification, CreateAddressOutput> execute(final CreateAddressCommand anIn) {
        final var customerID = CustomerID.from(anIn.customer());
        final var street = anIn.street();
        final var streetNumber = anIn.streetNumber();
        final var city = anIn.city();
        final var state = anIn.state();
        final var postalCode = anIn.postalCode();
        final var complement = anIn.complement();
        final var district = anIn.district();
        final var isActive = anIn.isActive();
        final var isDefault = anIn.isDefault();

        final var notification = Notification.create();
        notification.append(validateCustomer(customerID));

        final var aAddress = notification.validate(() -> Address.newAddress(street, streetNumber, city, state, postalCode, complement, district, isActive, isDefault));
        aAddress.addCustomer(this.customerGateway.findById(customerID)
                .orElseThrow(() -> NotFoundException.with(Customer.class, customerID)));

        return notification.hasError() ? Left(notification) : create(aAddress);
    }

    private Either<Notification, CreateAddressOutput> create(final Address aAddress) {
        return Try(() -> this.addressGateway.create(aAddress))
                .toEither()
                .bimap(Notification::create, CreateAddressOutput::from);
    }

    private ValidationHandler validateCustomer(final CustomerID customerID) {
        final var notification = Notification.create();
        if (customerID == null) {
            return notification;
        }

        final var existCustomerID = this.customerGateway.findById(customerID);
        if (existCustomerID.isEmpty()) {
            notification.append(new Error("Some customer could not be found: %s".formatted(customerID)));
        }

        return notification;
    }
}

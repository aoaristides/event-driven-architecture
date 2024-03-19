package br.com.makersweb.mscustomer.application.customer.update;

import br.com.makersweb.mscustomer.domain.Identifier;
import br.com.makersweb.mscustomer.domain.address.AddressGateway;
import br.com.makersweb.mscustomer.domain.address.AddressID;
import br.com.makersweb.mscustomer.domain.customer.Customer;
import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import br.com.makersweb.mscustomer.domain.customer.CustomerID;
import br.com.makersweb.mscustomer.domain.exceptions.DomainException;
import br.com.makersweb.mscustomer.domain.exceptions.NotFoundException;
import br.com.makersweb.mscustomer.domain.validation.Error;
import br.com.makersweb.mscustomer.domain.validation.ValidationHandler;
import br.com.makersweb.mscustomer.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

/**
 * @author aaristides
 */
public class DefaultUpdateUserUseCase extends UpdateCustomerUseCase {

    private final CustomerGateway customerGateway;
    private final AddressGateway addressGateway;

    public DefaultUpdateUserUseCase(final CustomerGateway customerGateway, final AddressGateway addressGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.addressGateway = Objects.requireNonNull(addressGateway);
    }

    @Override
    public Either<Notification, UpdateCustomerOutput> execute(final UpdateCustomerCommand aCommand) {
        final var anId = CustomerID.from(aCommand.id());
        final var name = aCommand.name();
        final var document = aCommand.document();
        final var type = aCommand.type();
        final var phoneNumber = aCommand.phoneNumber();
        final var birthDate = aCommand.birthDate();
        final var isActive = aCommand.isActive();

        final var aUser = this.customerGateway.findById(anId).orElseThrow(notFound(anId));
        final var notification = Notification.create();
        aUser.update(name, document, type, phoneNumber, birthDate, isActive).validate(notification);
        return notification.hasError() ? Left(notification) : update(aUser);
    }

    private Either<Notification, UpdateCustomerOutput> update(final Customer aUser) {
        return Try(() -> this.customerGateway.update(aUser))
                .toEither()
                .bimap(Notification::create, UpdateCustomerOutput::from);
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Customer.class, anId);
    }

    private ValidationHandler validateAddress(final List<AddressID> ids) {
        final var notification = Notification.create();
        if (ids == null || ids.isEmpty()) {
            return notification;
        }

        final var retrievedIds = addressGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);

            final var missingIdsMessage = missingIds.stream()
                    .map(AddressID::getValue)
                    .collect(Collectors.joining(", "));

            notification.append(new Error("Some categories could not be found: %s".formatted(missingIdsMessage)));
        }

        return notification;
    }

    private List<AddressID> toAddressID(final List<String> addresses) {
        return !addresses.isEmpty() ? addresses.stream()
                .map(AddressID::from)
                .toList() : Collections.emptyList();
    }
}

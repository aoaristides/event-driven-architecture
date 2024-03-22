package br.com.makersweb.mscustomer.infrastructure.api.controllers;

import br.com.makersweb.mscustomer.application.address.create.CreateAddressCommand;
import br.com.makersweb.mscustomer.application.address.create.CreateAddressOutput;
import br.com.makersweb.mscustomer.application.address.create.CreateAddressUseCase;
import br.com.makersweb.mscustomer.application.customer.create.CreateCustomerCommand;
import br.com.makersweb.mscustomer.application.customer.create.CreateCustomerOutput;
import br.com.makersweb.mscustomer.application.customer.create.CreateCustomerUseCase;
import br.com.makersweb.mscustomer.application.customer.delete.DeleteCustomerCase;
import br.com.makersweb.mscustomer.application.customer.retrieve.get.GetCustomerByIdUseCase;
import br.com.makersweb.mscustomer.application.customer.retrieve.list.ListCustomerUseCase;
import br.com.makersweb.mscustomer.application.customer.update.UpdateCustomerCommand;
import br.com.makersweb.mscustomer.application.customer.update.UpdateCustomerOutput;
import br.com.makersweb.mscustomer.application.customer.update.UpdateCustomerUseCase;
import br.com.makersweb.mscustomer.domain.pagination.Pagination;
import br.com.makersweb.mscustomer.domain.pagination.SearchQuery;
import br.com.makersweb.mscustomer.domain.validation.handler.Notification;
import br.com.makersweb.mscustomer.infrastructure.address.models.CreateAddressRequest;
import br.com.makersweb.mscustomer.infrastructure.api.CustomerAPI;
import br.com.makersweb.mscustomer.infrastructure.customer.models.CreateCustomerRequest;
import br.com.makersweb.mscustomer.infrastructure.customer.models.CustomerListResponse;
import br.com.makersweb.mscustomer.infrastructure.customer.models.CustomerResponse;
import br.com.makersweb.mscustomer.infrastructure.customer.models.UpdateCustomerRequest;
import br.com.makersweb.mscustomer.infrastructure.customer.presenters.CustomerApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.function.Function;

/**
 * @author aaristides
 */
@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final CreateAddressUseCase createAddressUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerCase deleteCustomerCase;
    private final ListCustomerUseCase listCustomerUseCase;

    public CustomerController(
            final CreateCustomerUseCase createCustomerUseCase,
            final CreateAddressUseCase createAddressUseCase,
            final GetCustomerByIdUseCase getCustomerByIdUseCase,
            final UpdateCustomerUseCase updateCustomerUseCase,
            final DeleteCustomerCase deleteCustomerCase,
            final ListCustomerUseCase listCustomerUseCase
    ) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.createAddressUseCase = createAddressUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerCase = deleteCustomerCase;
        this.listCustomerUseCase = listCustomerUseCase;
    }

    @Override
    public ResponseEntity<?> createCustomer(final CreateCustomerRequest input) {
        final var aCommand = CreateCustomerCommand.with(
                input.name(),
                input.document(),
                input.type(),
                input.phoneNumber(),
                input.birthDate(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCustomerOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/customers/" + output.id())).body(output);

        return this.createCustomerUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> createAddressById(final String id, final CreateAddressRequest input) {
        final var aCommand = CreateAddressCommand.with(
                id,
                input.street(),
                input.streetNumber(),
                input.city(),
                input.state(),
                input.postalCode(),
                input.complement(),
                input.district(),
                input.active() != null ? input.active() : true,
                input.isDefault() != null ? input.isDefault() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateAddressOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/customers/" + id + "/address/" + output.id())).body(output);

        return this.createAddressUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public Pagination<CustomerListResponse> listCustomers(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return this.listCustomerUseCase
                .execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(CustomerApiPresenter::present);
    }

    @Override
    public CustomerResponse getById(final String id) {
        return CustomerApiPresenter.present(this.getCustomerByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCustomerRequest input) {
        final var aCommand = UpdateCustomerCommand.with(
                id,
                input.name(),
                input.document(),
                input.type(),
                input.phoneNumber(),
                input.birthDate(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCustomerOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateCustomerUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteCustomerCase.execute(id);
    }
}

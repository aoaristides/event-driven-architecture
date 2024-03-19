package br.com.makersweb.mscustomer.application.customer.retrieve.get;

import br.com.makersweb.mscustomer.domain.customer.Customer;
import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import br.com.makersweb.mscustomer.domain.customer.CustomerID;
import br.com.makersweb.mscustomer.domain.exceptions.NotFoundException;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultGetCustomerByIdUseCase extends GetCustomerByIdUseCase {

    private final CustomerGateway customerGateway;

    public DefaultGetCustomerByIdUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CustomerOutput execute(final String anIn) {
        final var aUserId = CustomerID.from(anIn);
        return this.customerGateway.findById(aUserId)
                .map(CustomerOutput::from)
                .orElseThrow(() -> NotFoundException.with(Customer.class, aUserId));
    }

}

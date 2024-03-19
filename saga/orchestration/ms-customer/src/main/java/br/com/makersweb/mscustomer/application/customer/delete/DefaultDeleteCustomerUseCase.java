package br.com.makersweb.mscustomer.application.customer.delete;

import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import br.com.makersweb.mscustomer.domain.customer.CustomerID;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultDeleteCustomerUseCase extends DeleteCustomerCase {

    private final CustomerGateway customerGateway;

    public DefaultDeleteCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public void execute(final String anIn) {
        this.customerGateway.deleteById(CustomerID.from(anIn));
    }

}

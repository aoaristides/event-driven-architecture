package br.com.makersweb.mscustomer.application.customer.retrieve.list;

import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import br.com.makersweb.mscustomer.domain.pagination.Pagination;
import br.com.makersweb.mscustomer.domain.pagination.SearchQuery;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultListCustomerUseCase extends ListCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultListCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Pagination<CustomerListOutput> execute(final SearchQuery aQuery) {
        return this.customerGateway.findAll(aQuery).map(CustomerListOutput::from);
    }

}

package br.com.makersweb.mscustomer.domain.customer;

import br.com.makersweb.mscustomer.domain.pagination.Pagination;
import br.com.makersweb.mscustomer.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface CustomerGateway {

    Customer create(final Customer aCustomer);

    void deleteById(final CustomerID anId);

    Optional<Customer> findById(final CustomerID anId);

    Customer update(final Customer aCustomer);

    Pagination<Customer> findAll(final SearchQuery aQuery);

    List<CustomerID> existsByIds(final Iterable<CustomerID> ids);

}

package br.com.makersweb.mscustomer.application.customer.retrieve.list;

import br.com.makersweb.mscustomer.application.UseCase;
import br.com.makersweb.mscustomer.domain.pagination.Pagination;
import br.com.makersweb.mscustomer.domain.pagination.SearchQuery;

/**
 * @author aaristides
 */
public abstract class ListCustomerUseCase extends UseCase<SearchQuery, Pagination<CustomerListOutput>> {
}

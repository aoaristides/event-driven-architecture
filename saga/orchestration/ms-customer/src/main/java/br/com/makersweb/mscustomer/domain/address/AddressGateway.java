package br.com.makersweb.mscustomer.domain.address;

import br.com.makersweb.mscustomer.domain.pagination.Pagination;
import br.com.makersweb.mscustomer.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface AddressGateway {

    Address create(final Address aAddress);

    void deleteById(final AddressID anId);

    Optional<Address> findById(final AddressID anId);

    Address update(final Address aAddress);

    Pagination<Address> findAll(final SearchQuery aQuery);

    List<AddressID> existsByIds(final Iterable<AddressID> ids);

}

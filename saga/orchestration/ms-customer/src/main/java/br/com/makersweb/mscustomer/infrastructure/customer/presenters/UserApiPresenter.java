package br.com.makersweb.mscustomer.infrastructure.customer.presenters;

import br.com.makersweb.mscustomer.application.customer.retrieve.get.CustomerOutput;
import br.com.makersweb.mscustomer.application.customer.retrieve.list.CustomerListOutput;
import br.com.makersweb.mscustomer.infrastructure.customer.models.CustomerListResponse;
import br.com.makersweb.mscustomer.infrastructure.customer.models.CustomerResponse;

/**
 * @author aaristides
 */
public interface UserApiPresenter {

    static CustomerResponse present(final CustomerOutput output) {
        return new CustomerResponse(
                output.id().getValue(),
                output.name(),
                output.document(),
                output.type(),
                output.birthDate(),
                output.phoneNumber(),
                output.active(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CustomerListResponse present(final CustomerListOutput output) {
        return new CustomerListResponse(
                output.id(),
                output.name(),
                output.document(),
                output.type(),
                output.birthDate(),
                output.phoneNumber(),
                output.active(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

}

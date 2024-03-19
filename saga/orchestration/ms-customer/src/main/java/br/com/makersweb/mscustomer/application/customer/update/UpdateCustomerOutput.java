package br.com.makersweb.mscustomer.application.customer.update;

import br.com.makersweb.mscustomer.domain.customer.Customer;

/**
 * @author aaristides
 * @param id
 */
public record UpdateCustomerOutput(
        String id
) {

    public static UpdateCustomerOutput from(final String anId) {
        return new UpdateCustomerOutput(anId);
    }

    public static UpdateCustomerOutput from(final Customer aUser) {
        return from(aUser.getId().getValue());
    }

}

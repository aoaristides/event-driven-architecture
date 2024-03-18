package br.com.makersweb.mscustomer.application.customer.create;

import br.com.makersweb.mscustomer.domain.customer.Customer;

/**
 * @author aaristides
 * @param id
 */
public record CreateCustomerOutput(
        String id
) {

    public static CreateCustomerOutput from(final String anId) {
        return new CreateCustomerOutput(anId);
    }

    public static CreateCustomerOutput from(final Customer aUser) {
        return from(aUser.getId().getValue());
    }

}

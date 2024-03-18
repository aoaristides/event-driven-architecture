package br.com.makersweb.mscustomer.application.address.create;

import br.com.makersweb.mscustomer.domain.address.Address;

/**
 * @author aaristides
 * @param id
 */
public record CreateAddressOutput(
        String id
) {

    public static CreateAddressOutput from(final String anId) {
        return new CreateAddressOutput(anId);
    }

    public static CreateAddressOutput from(final Address aAddress) {
        return from(aAddress.getId().getValue());
    }

}

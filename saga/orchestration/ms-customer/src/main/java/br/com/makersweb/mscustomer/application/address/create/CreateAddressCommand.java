package br.com.makersweb.mscustomer.application.address.create;

/**
 * @author aaristides
 * @param customer
 * @param street
 * @param streetNumber
 * @param city
 * @param state
 * @param postalCode
 * @param complement
 * @param district
 * @param isActive
 * @param isDefault
 */
public record CreateAddressCommand(
        String customer,
        String street,
        String streetNumber,
        String city,
        String state,
        String postalCode,
        String complement,
        String district,
        boolean isActive,
        boolean isDefault
) {

    public static CreateAddressCommand with(
            final String customer,
            final String street,
            final String streetNumber,
            final String city,
            final String state,
            final String postalCode,
            final String complement,
            final String district,
            final Boolean isActive,
            final Boolean isDefault
    ) {
        return new CreateAddressCommand(customer, street, streetNumber, city, state, postalCode, complement, district, isActive, isDefault);
    }

}

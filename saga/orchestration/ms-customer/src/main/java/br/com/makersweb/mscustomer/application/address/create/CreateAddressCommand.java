package br.com.makersweb.mscustomer.application.address.create;

/**
 * @author aaristides
 * @param street
 * @param streetNumber
 * @param city
 * @param state
 * @param postalCode
 * @param complement
 * @param neighborhood
 * @param isActive
 */
public record CreateAddressCommand(
        String street,
        String streetNumber,
        String city,
        String state,
        String postalCode,
        String complement,
        String neighborhood,
        boolean isActive
) {

    public static CreateAddressCommand with(
            final String street,
            final String streetNumber,
            final String city,
            final String state,
            final String postalCode,
            final String complement,
            final String neighborhood,
            final Boolean isActive
    ) {
        return new CreateAddressCommand(street, streetNumber, city, state, postalCode, complement, neighborhood, isActive != null ? isActive : true);
    }

}

package br.com.makersweb.mscustomer.application.customer.create;

import br.com.makersweb.mscustomer.application.address.create.CreateAddressCommand;

import java.time.LocalDate;

/**
 * @author aaristides
 * @param name
 * @param document
 * @param type
 * @param phoneNumber
 * @param birthDate
 * @param address
 * @param isActive
 */
public record CreateCustomerCommand(
        String name,
        String document,
        String type,
        String phoneNumber,
        LocalDate birthDate,
        CreateAddressCommand address,
        boolean isActive
) {

    public static CreateCustomerCommand with(
            final String name,
            final String document,
            final String type,
            final String phoneNumber,
            final LocalDate birthDate,
            final CreateAddressCommand address,
            final boolean isActive
    ) {
        return new CreateCustomerCommand(name, document, type, phoneNumber, birthDate, address, isActive);
    }

}

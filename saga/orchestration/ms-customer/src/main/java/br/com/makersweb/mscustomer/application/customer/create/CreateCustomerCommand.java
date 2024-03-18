package br.com.makersweb.mscustomer.application.customer.create;

import java.time.LocalDate;

/**
 * @author aaristides
 * @param name
 * @param document
 * @param type
 * @param phoneNumber
 * @param birthDate
 * @param isActive
 */
public record CreateCustomerCommand(
        String name,
        String document,
        String type,
        String phoneNumber,
        LocalDate birthDate,
        boolean isActive
) {

    public static CreateCustomerCommand with(
            final String name,
            final String document,
            final String type,
            final String phoneNumber,
            final LocalDate birthDate,
            final boolean isActive
    ) {
        return new CreateCustomerCommand(name, document, type, phoneNumber, birthDate, isActive);
    }

}

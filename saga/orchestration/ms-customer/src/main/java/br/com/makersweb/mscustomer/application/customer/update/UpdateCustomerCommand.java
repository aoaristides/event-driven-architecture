package br.com.makersweb.mscustomer.application.customer.update;

import java.time.LocalDate;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param document
 * @param type
 * @param phoneNumber
 * @param birthDate
 * @param isActive
 */
public record UpdateCustomerCommand(
        String id,
        String name,
        String document,
        String type,
        String phoneNumber,
        LocalDate birthDate,
        boolean isActive
) {

    public static UpdateCustomerCommand with(
            final String id,
            final String name,
            final String document,
            final String type,
            final String phoneNumber,
            final LocalDate birthDate,
            final boolean isActive
    ) {
        return new UpdateCustomerCommand(id, name, document, type, phoneNumber, birthDate, isActive);
    }
}

package br.com.makersweb.mscustomer.application.customer.retrieve.list;

import br.com.makersweb.mscustomer.domain.customer.Customer;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param document
 * @param type
 * @param phoneNumber
 * @param birthDate
 * @param active
 * @param createdAt
 * @param updatedAt
 * @param deletedAt
 */
public record CustomerListOutput(
        String id,
        String name,
        String document,
        String type,
        String phoneNumber,
        LocalDate birthDate,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static CustomerListOutput from(final Customer aCustomer) {
        return new CustomerListOutput(
                aCustomer.getId().getValue(),
                aCustomer.getName(),
                aCustomer.getDocument(),
                aCustomer.getType().name(),
                aCustomer.getPhone(),
                aCustomer.getBirthday(),
                aCustomer.isActive(),
                aCustomer.getCreatedAt(),
                aCustomer.getUpdatedAt(),
                aCustomer.getDeletedAt()
        );
    }

}

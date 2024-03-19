package br.com.makersweb.mscustomer.application.customer.retrieve.get;

import br.com.makersweb.mscustomer.domain.customer.Customer;
import br.com.makersweb.mscustomer.domain.customer.CustomerID;

import java.time.Instant;
import java.time.LocalDate;

/**
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
 * @author aaristides
 */
public record CustomerOutput(
        CustomerID id,
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

    public static CustomerOutput from(final Customer aCustomer) {
        return new CustomerOutput(
                aCustomer.getId(),
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

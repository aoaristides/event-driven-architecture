package br.com.makersweb.mscustomer.application.customer.retrieve.get;

import br.com.makersweb.mscustomer.domain.customer.CustomerID;

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
}

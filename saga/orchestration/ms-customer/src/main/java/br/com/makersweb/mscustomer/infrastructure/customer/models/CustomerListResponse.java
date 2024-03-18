package br.com.makersweb.mscustomer.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param document
 * @param type
 * @param birthDate
 * @param phoneNumber
 * @param active
 * @param createdAt
 * @param updatedAt
 * @param deletedAt
 */
public record CustomerListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("document") String document,
        @JsonProperty("type") String type,
        @JsonProperty("birth_date") LocalDate birthDate,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {
}

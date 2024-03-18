package br.com.makersweb.mscustomer.infrastructure.address.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * @author aaristides
 * @param id
 * @param street
 * @param streetNumber
 * @param city
 * @param state
 * @param postalCode
 * @param complement
 * @param neighborhood
 * @param active
 * @param createdAt
 * @param updatedAt
 * @param deletedAt
 */
public record AddressResponse(
        @JsonProperty("id") String id,
        @JsonProperty("street") String street,
        @JsonProperty("street_number") String streetNumber,
        @JsonProperty("city") String city,
        @JsonProperty("state") String state,
        @JsonProperty("postal_code") String postalCode,
        @JsonProperty("complement")  String complement,
        @JsonProperty("neighborhood") String neighborhood,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {
}

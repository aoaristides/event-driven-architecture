package br.com.makersweb.mscustomer.infrastructure.address.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aaristides
 * @param street
 * @param streetNumber
 * @param city
 * @param state
 * @param postalCode
 * @param complement
 * @param district
 * @param active
 * @param isDefault
 */
public record CreateAddressRequest(
        @JsonProperty("street") String street,
        @JsonProperty("street_number") String streetNumber,
        @JsonProperty("city") String city,
        @JsonProperty("state") String state,
        @JsonProperty("postal_code") String postalCode,
        @JsonProperty("complement")  String complement,
        @JsonProperty("district") String district,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("is_default") Boolean isDefault
) {
}

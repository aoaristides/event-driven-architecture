package br.com.makersweb.mscustomer.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * @author aaristides
 * @param name
 * @param document
 * @param type
 * @param phoneNumber
 * @param birthDate
 * @param mail
 * @param active
 */
public record CreateCustomerRequest(
        @JsonProperty("name") String name,
        @JsonProperty("document") String document,
        @JsonProperty("type") String type,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("birth_date") LocalDate birthDate,
        @JsonProperty("mail") String mail,
        @JsonProperty("is_active") Boolean active
) {
}

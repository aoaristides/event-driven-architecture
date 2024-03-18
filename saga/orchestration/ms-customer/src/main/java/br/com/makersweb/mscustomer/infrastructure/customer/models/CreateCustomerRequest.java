package br.com.makersweb.mscustomer.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

/**
 * @author aaristides
 * @param name
 * @param document
 * @param mail
 * @param phoneNumber
 * @param birthDate
 * @param gender
 * @param photo
 * @param active
 * @param addresses
 */
public record CreateCustomerRequest(
        @JsonProperty("name") String name,
        @JsonProperty("document") String document,
        @JsonProperty("mail") String mail,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("birth_date") LocalDate birthDate,
        @JsonProperty("gender") String gender,
        @JsonProperty("photo") String photo,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("addresses") List<String> addresses
) {
}

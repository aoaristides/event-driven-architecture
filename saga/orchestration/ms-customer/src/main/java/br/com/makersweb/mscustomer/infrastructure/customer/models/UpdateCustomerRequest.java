package br.com.makersweb.mscustomer.infrastructure.customer.models;

import br.com.makersweb.mscustomer.infrastructure.address.models.UpdateAddressRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

/**
 * @author aaristides
 * @param name
 * @param document
 * @param type
 * @param phoneNumber
 * @param birthDate
 * @param gender
 * @param photo
 * @param active
 * @param address
 */
public record UpdateCustomerRequest(
        @JsonProperty("name") String name,
        @JsonProperty("document") String document,
        @JsonProperty("type") String type,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("birth_date") LocalDate birthDate,
        @JsonProperty("gender") String gender,
        @JsonProperty("photo") String photo,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("address") UpdateAddressRequest address
) {
}

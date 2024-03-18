package br.com.makersweb.mscustomer.application.address.retrieve.get;

import br.com.makersweb.mscustomer.domain.address.Address;
import br.com.makersweb.mscustomer.domain.address.AddressID;

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
 * @param district
 * @param isActive
 * @param createdAt
 * @param updatedAt
 * @param deletedAt
 */
public record AddressOutput(
        AddressID id,
        String street,
        String streetNumber,
        String city,
        String state,
        String postalCode,
        String complement,
        String district,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static AddressOutput from(final Address aAddress) {
        return new AddressOutput(
                aAddress.getId(),
                aAddress.getStreet(),
                aAddress.getStreetNumber(),
                aAddress.getCity(),
                aAddress.getState(),
                aAddress.getPostalCode(),
                aAddress.getComplement(),
                aAddress.getDistrict(),
                aAddress.isActive(),
                aAddress.getCreatedAt(),
                aAddress.getUpdatedAt(),
                aAddress.getDeletedAt()
        );
    }

}

package br.com.makersweb.mscustomer.application.address.retrieve.list;

import br.com.makersweb.mscustomer.domain.address.Address;

import java.time.Instant;

/**
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
 * @author aaristides
 */
public record AddressListOutput(
        String id,
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

    public static AddressListOutput from(final Address aAddress) {
        return new AddressListOutput(
                aAddress.getId().getValue(),
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

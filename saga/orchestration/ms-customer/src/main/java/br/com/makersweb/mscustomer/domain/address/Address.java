package br.com.makersweb.mscustomer.domain.address;

import br.com.makersweb.mscustomer.domain.AggregateRoot;
import br.com.makersweb.mscustomer.domain.customer.Customer;
import br.com.makersweb.mscustomer.domain.utils.InstantUtils;
import br.com.makersweb.mscustomer.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

/**
 * @author aaristides
 */
public class Address extends AggregateRoot<AddressID> {

    private String street;
    private String streetNumber;
    private String city;
    private String state;
    private String postalCode;
    private String complement;
    private String district;
    private boolean active;
    private boolean addressDefault;
    private Customer customer;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Address(
            final AddressID anId,
            final String street,
            final String streetNumber,
            final String city,
            final String state,
            final String postalCode,
            final String complement,
            final String district,
            final boolean active,
            final boolean addressDefault,
            final Customer customer,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(anId);
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.complement = complement;
        this.district = district;
        this.active = active;
        this.addressDefault = addressDefault;
        this.customer = customer;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
        this.deletedAt = deletedAt;
    }

    public static Address newAddress(
            final String street,
            final String streetNumber,
            final String city,
            final String state,
            final String postalCode,
            final String complement,
            final String district,
            final boolean active,
            final boolean addressDefault
    ) {
        final var anId = AddressID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = active ? null : now;
        return new Address(anId, street, streetNumber, city, state, postalCode, complement, district, active, addressDefault, null, now, now, deletedAt);
    }

    public static Address with(
            final AddressID anId,
            final String street,
            final String streetNumber,
            final String city,
            final String state,
            final String postalCode,
            final String complement,
            final String district,
            final boolean active,
            final boolean addressDefault,
            final Customer customer,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Address(anId, street, streetNumber, city, state, postalCode, complement, district, active, addressDefault, customer, createdAt, updatedAt, deletedAt);
    }

    public static Address with(final Address aAddress) {
        return with(
                aAddress.getId(),
                aAddress.getStreet(),
                aAddress.getStreetNumber(),
                aAddress.getCity(),
                aAddress.getState(),
                aAddress.getPostalCode(),
                aAddress.getComplement(),
                aAddress.getDistrict(),
                aAddress.isActive(),
                aAddress.isAddressDefault(),
                aAddress.getCustomer(),
                aAddress.getCreatedAt(),
                aAddress.getUpdatedAt(),
                aAddress.getDeletedAt()
        );
    }

    public Address activate(final boolean isDefault) {
        this.deletedAt = null;
        this.active = true;
        this.addressDefault = isDefault;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Address deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.active = false;
        this.addressDefault = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Address update(
            final String street,
            final String streetNumber,
            final String city,
            final String state,
            final String postalCode,
            final String complement,
            final String district,
            final boolean isActive,
            final boolean addressDefault,
            final Customer customer
    ) {
        if (isActive) {
            activate(addressDefault);
        } else {
            deactivate();
        }
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.complement = complement;
        this.district = district;
        this.addressDefault = addressDefault;
        this.customer = customer;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new AddressValidator(this, handler).validate();
    }

    public Address addCustomer(final Customer customer) {
        this.customer = customer;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public AddressID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public boolean isActive() {
        return active;
    }
    public boolean isAddressDefault() {
        return addressDefault;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}

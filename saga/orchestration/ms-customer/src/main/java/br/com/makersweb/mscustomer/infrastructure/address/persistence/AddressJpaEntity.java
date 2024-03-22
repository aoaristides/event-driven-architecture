package br.com.makersweb.mscustomer.infrastructure.address.persistence;

import br.com.makersweb.mscustomer.domain.address.Address;
import br.com.makersweb.mscustomer.domain.address.AddressID;
import br.com.makersweb.mscustomer.infrastructure.customer.persistence.CustomerJpaEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

/**
 * @author aaristides
 */
@Entity(name = "Address")
@Table(name = "addresses")
public class AddressJpaEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street_number", nullable = false, length = 30)
    private String streetNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "postal_code", nullable = false, length = 8)
    private String postalCode;

    @Column(name = "complement", nullable = false)
    private String complement;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "ad_default", nullable = false)
    private boolean addressDefault;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerJpaEntity customer;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private Instant deletedAt;

    public AddressJpaEntity() {
    }

    private AddressJpaEntity(
            final String id,
            final String street,
            final String streetNumber,
            final String city,
            final String state,
            final String postalCode,
            final String complement,
            final String district,
            final boolean active,
            final boolean addressDefault,
            final CustomerJpaEntity customer,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = id;
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static AddressJpaEntity from(final Address aAddress) {
        return new AddressJpaEntity(
                aAddress.getId().getValue(),
                aAddress.getStreet(),
                aAddress.getStreetNumber(),
                aAddress.getCity(),
                aAddress.getState(),
                aAddress.getPostalCode(),
                aAddress.getComplement(),
                aAddress.getDistrict(),
                aAddress.isActive(),
                aAddress.isAddressDefault(),
                CustomerJpaEntity.from(aAddress.getCustomer()),
                aAddress.getCreatedAt(),
                aAddress.getUpdatedAt(),
                aAddress.getDeletedAt()
        );
    }

    public Address toAggregate() {
        return Address.with(
                AddressID.from(getId()),
                getStreet(),
                getStreetNumber(),
                getCity(),
                getState(),
                getPostalCode(),
                getComplement(),
                getDistrict(),
                isActive(),
                isAddressDefault(),
                Optional.ofNullable(getCustomer())
                        .map(CustomerJpaEntity::toAggregate)
                        .orElse(null),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
        );
    }

    public AddressJpaEntity addCustomer(final CustomerJpaEntity customer) {
        this.customer = customer;
        return this;
    }

    public String getId() {
        return id;
    }

    public AddressJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressJpaEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public AddressJpaEntity setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressJpaEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public AddressJpaEntity setState(String state) {
        this.state = state;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressJpaEntity setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getComplement() {
        return complement;
    }

    public AddressJpaEntity setComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public AddressJpaEntity setDistrict(String district) {
        this.district = district;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public AddressJpaEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    public boolean isAddressDefault() {
        return addressDefault;
    }

    public AddressJpaEntity setAddressDefault(boolean addressDefault) {
        this.addressDefault = addressDefault;
        return this;
    }

    public CustomerJpaEntity getCustomer() {
        return customer;
    }

    public AddressJpaEntity setCustomer(CustomerJpaEntity customer) {
        this.customer = customer;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public AddressJpaEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public AddressJpaEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public AddressJpaEntity setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}

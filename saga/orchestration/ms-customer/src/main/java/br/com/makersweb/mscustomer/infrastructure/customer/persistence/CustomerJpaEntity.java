package br.com.makersweb.mscustomer.infrastructure.customer.persistence;

import br.com.makersweb.mscustomer.domain.customer.Customer;
import br.com.makersweb.mscustomer.domain.customer.CustomerID;
import br.com.makersweb.mscustomer.domain.customer.CustomerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * @author aaristides
 */
@Entity(name = "User")
@Table(name = "tb_users")
public class CustomerJpaEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false, length = 15, unique = true)
    private String document;

    @Column(name = "type", nullable = false, length = 1)
    private String type;

    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @Column(name = "birthday", nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(name = "active", nullable = false)
    private boolean active;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    private Set<UserAddressJpaEntity> addresses;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private Instant deletedAt;

    public CustomerJpaEntity() {
    }

    private CustomerJpaEntity(
            final String anId,
            final String name,
            final String document,
            final String type,
            final String phoneNumber,
            final LocalDate birthDate,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = anId;
        this.name = name;
        this.document = document;
        this.type = type;
        this.birthDate = birthDate;
        this.phone = phoneNumber;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static CustomerJpaEntity from(final Customer aUser) {
        return new CustomerJpaEntity(
                aUser.getId().getValue(),
                aUser.getName(),
                aUser.getDocument(),
                aUser.getType().name(),
                aUser.getPhone(),
                aUser.getBirthday(),
                aUser.isActive(),
                aUser.getCreatedAt(),
                aUser.getUpdatedAt(),
                aUser.getDeletedAt()
        );
    }

    public Customer toAggregate() {
        return Customer.with(
                CustomerID.from(getId()),
                getName(),
                getDocument(),
                CustomerType.valueOf(getType()),
                getPhone(),
                getBirthDate(),
                isActive(),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
        );
    }

    public String getId() {
        return id;
    }

    public CustomerJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerJpaEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDocument() {
        return document;
    }

    public CustomerJpaEntity setDocument(String document) {
        this.document = document;
        return this;
    }

    public String getType() {
        return type;
    }

    public CustomerJpaEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerJpaEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CustomerJpaEntity setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public CustomerJpaEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public CustomerJpaEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public CustomerJpaEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public CustomerJpaEntity setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}

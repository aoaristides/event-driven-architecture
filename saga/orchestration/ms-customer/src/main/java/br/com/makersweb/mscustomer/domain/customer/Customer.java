package br.com.makersweb.mscustomer.domain.customer;

import br.com.makersweb.mscustomer.domain.AggregateRoot;
import br.com.makersweb.mscustomer.domain.utils.InstantUtils;
import br.com.makersweb.mscustomer.domain.validation.ValidationHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author aaristides
 */
public class Customer extends AggregateRoot<CustomerID> {

    private String name;
    private String document;
    private CustomerType type;
    private String phone;
    private LocalDate birthday;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Customer(
            final CustomerID customerID,
            final String name,
            final String document,
            final String type,
            final String phone,
            final LocalDate birthday,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(customerID);
        CustomerType.get(type).ifPresent(customerType -> this.type = customerType);
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.birthday = birthday;
        this.active = active;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
        this.deletedAt = deletedAt;
    }

    public static Customer newCustomer(
            final String name,
            final String document,
            final String type,
            final String phone,
            final LocalDate birthday,
            final boolean isActive
    ) {
        final var anId = CustomerID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;
        return new Customer(anId, name, document, type, phone, birthday, isActive, now, now, deletedAt);
    }

    public static Customer with(
            final CustomerID customerID,
            final String name,
            final String document,
            final String type,
            final String phone,
            final LocalDate birthday,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Customer(customerID, name, document, type, phone, birthday, active, createdAt, updatedAt, deletedAt);
    }

    public static Customer with(final Customer aCustomer) {
        return with(
                aCustomer.getId(),
                aCustomer.name,
                aCustomer.document,
                aCustomer.type.name(),
                aCustomer.phone,
                aCustomer.birthday,
                aCustomer.active,
                aCustomer.createdAt,
                aCustomer.updatedAt,
                aCustomer.deletedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CustomerValidator(this, handler).validate();
    }

    public Customer activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Customer deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Customer update(
            final String name,
            final String document,
            final String type,
            final String phone,
            final LocalDate birthday,
            final boolean isActive
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        CustomerType.get(type).ifPresent(customerType -> this.type = customerType);
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.birthday = birthday;
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public CustomerID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public CustomerType getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public boolean isActive() {
        return active;
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

package br.com.makersweb.mscustomer.domain.customer;

import br.com.makersweb.mscustomer.domain.Identifier;
import br.com.makersweb.mscustomer.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class CustomerID extends Identifier {

    private final String value;

    private CustomerID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CustomerID unique() {
        return CustomerID.from(IdUtils.uuid());
    }

    public static CustomerID from(final String anId) {
        return new CustomerID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerID that = (CustomerID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}

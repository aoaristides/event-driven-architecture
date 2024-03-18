package br.com.makersweb.mscustomer.domain.address;

import br.com.makersweb.mscustomer.domain.Identifier;
import br.com.makersweb.mscustomer.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class AddressID extends Identifier {

    private final String value;

    private AddressID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static AddressID unique() {
        return AddressID.from(IdUtils.uuid());
    }

    public static AddressID from(final String anId) {
        return new AddressID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AddressID addressID = (AddressID) object;
        return Objects.equals(getValue(), addressID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}

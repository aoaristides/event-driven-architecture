package br.com.makersweb.mscustomer.domain.customer;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author aaristides
 */
public enum CustomerType {

    F, J;

    public static Optional<CustomerType> get(final String type) {
        return Arrays.stream(CustomerType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }

}

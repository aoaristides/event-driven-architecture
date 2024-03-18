package br.com.makersweb.mscustomer.domain.address;

import br.com.makersweb.mscustomer.domain.validation.Error;
import br.com.makersweb.mscustomer.domain.validation.ValidationHandler;
import br.com.makersweb.mscustomer.domain.validation.Validator;

/**
 * @author aaristides
 */
public class AddressValidator extends Validator {

    private final Address address;
    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;

    public AddressValidator(final Address address, final ValidationHandler aHandler) {
        super(aHandler);
        this.address = address;
    }

    @Override
    public void validate() {
        this.checkStreetConstraints();
    }

    private void checkStreetConstraints() {
        final var street = this.address.getStreet();
        if (street == null) {
            this.validationHandler().append(new Error("'street' should not be null"));
            return;
        }

        if (street.isBlank()) {
            this.validationHandler().append(new Error("'street' should not be empty"));
            return;
        }

        final var length = street.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'street' must be between 3 and 255 characters"));
        }
    }
}

package br.com.makersweb.mscustomer.domain.customer;

import br.com.makersweb.mscustomer.domain.validation.Error;
import br.com.makersweb.mscustomer.domain.validation.ValidationHandler;
import br.com.makersweb.mscustomer.domain.validation.Validator;

public class CustomerValidator extends Validator {

    private final Customer customer;

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;

    public CustomerValidator(final Customer customer, final ValidationHandler aHandler) {
        super(aHandler);
        this.customer = customer;
    }

    @Override
    public void validate() {
        this.checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.customer.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final var length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}

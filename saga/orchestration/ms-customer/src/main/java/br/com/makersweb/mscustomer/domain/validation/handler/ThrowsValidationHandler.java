package br.com.makersweb.mscustomer.domain.validation.handler;

import br.com.makersweb.mscustomer.domain.exceptions.DomainException;
import br.com.makersweb.mscustomer.domain.validation.Error;
import br.com.makersweb.mscustomer.domain.validation.ValidationHandler;

import java.util.List;

/**
 * @author aaristides
 */
public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation anValidation) {
        try {
            anValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }
}

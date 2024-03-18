package br.com.makersweb.mscustomer.application.customer.create;

import br.com.makersweb.mscustomer.application.UseCase;
import br.com.makersweb.mscustomer.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class CreateCustomerUseCase extends UseCase<CreateCustomerCommand, Either<Notification, CreateCustomerOutput>> {
}

package br.com.makersweb.mscustomer.application.customer.update;

import br.com.makersweb.mscustomer.application.UseCase;
import br.com.makersweb.mscustomer.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class UpdateCustomerUseCase extends UseCase<UpdateCustomerCommand, Either<Notification, UpdateCustomerOutput>> {
}

package br.com.makersweb.mscustomer.application.address.create;

import br.com.makersweb.mscustomer.application.UseCase;
import br.com.makersweb.mscustomer.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class CreateAddressUseCase extends UseCase<CreateAddressCommand, Either<Notification, CreateAddressOutput>> {
}

package br.com.makersweb.mscustomer.infrastructure.consumer;

import br.com.makersweb.mscustomer.application.address.create.CreateAddressCommand;
import br.com.makersweb.mscustomer.application.address.create.CreateAddressUseCase;
import br.com.makersweb.mscustomer.application.customer.create.CreateCustomerCommand;
import br.com.makersweb.mscustomer.application.customer.create.CreateCustomerUseCase;
import br.com.makersweb.mscustomer.domain.exceptions.DomainException;
import br.com.makersweb.mscustomer.domain.exceptions.NotificationException;
import br.com.makersweb.mscustomer.domain.validation.Error;
import br.com.makersweb.mscustomer.infrastructure.consumer.models.CreateNotificationErrorRequest;
import br.com.makersweb.mscustomer.infrastructure.consumer.models.CreateNotificationSuccessRequest;
import br.com.makersweb.mscustomer.infrastructure.consumer.models.NotificationStatus;
import br.com.makersweb.mscustomer.infrastructure.customer.models.CreateCustomerRequest;
import br.com.makersweb.mscustomer.infrastructure.services.EventService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.annotation.SqsListenerAcknowledgementMode;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author aaristides
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerMessageListener {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final CreateAddressUseCase createAddressUseCase;
    private final EventService eventService;

    @SqsListener(value = "${makersweb.aws.sqs.customer-service.queue}", acknowledgementMode = SqsListenerAcknowledgementMode.MANUAL, id = "async-customer-processing-container", messageVisibilitySeconds = "3")
    public void onCustomerMessage(@Payload final Message<CreateCustomerRequest> request, final Acknowledgement ack) {
        try {
            log.info("Consumer message queue payload - {}", request.getPayload());
            final var customer = request.getPayload();
            final var address = customer.address();
            final var customerCommand = CreateCustomerCommand.with(
                    customer.name(),
                    customer.document(),
                    customer.type(),
                    customer.phoneNumber(),
                    customer.birthDate(),
                    customer.active() != null ? customer.active() : true
            );

            final var customerExecute = this.createCustomerUseCase.execute(customerCommand)
                    .getOrElseThrow(notification -> NotificationException.with(notification.getErrors()));

            final var addressCommand = CreateAddressCommand.with(
                    customerExecute.id(),
                    address.street(),
                    address.streetNumber(),
                    address.city(),
                    address.state(),
                    address.postalCode(),
                    address.complement(),
                    address.district(),
                    address.active() != null ? address.active() : true,
                    address.isDefault() != null ? address.isDefault() : true
            );
            this.createAddressUseCase.execute(addressCommand);
            eventService.send(CreateNotificationSuccessRequest.with(customerExecute.id(), NotificationStatus.SUCCESS, "Customer verified success."));
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Error in consumer queue message customer.");
            if (e instanceof DomainException) {
                eventService.send(CreateNotificationErrorRequest.with(NotificationStatus.ERROR, ((DomainException) e).getErrors()));
            } else {
                eventService.send(CreateNotificationErrorRequest.with(NotificationStatus.ERROR, Arrays.asList(new Error("Error in consumer queue message customer."))));
            }
            ack.acknowledge();
        }
    }

}

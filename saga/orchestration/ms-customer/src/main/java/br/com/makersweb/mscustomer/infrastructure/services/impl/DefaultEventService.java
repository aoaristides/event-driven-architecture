package br.com.makersweb.mscustomer.infrastructure.services.impl;

import br.com.makersweb.mscustomer.infrastructure.json.Json;
import br.com.makersweb.mscustomer.infrastructure.services.EventService;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author aaristides
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultEventService implements EventService {

    private final SqsTemplate sqsTemplate;

    @Value("${makersweb.aws.sqs.notification-return.queue}")
    private String notificationReturnQueue;

    @Override
    public void send(final Object event) {
        log.info("Init method send message notification return.");
        final var result = this.sqsTemplate.send(notificationReturnQueue, Json.writeValueAsString(event));
        log.info("Message send success with id - {}", result.messageId());
    }
}

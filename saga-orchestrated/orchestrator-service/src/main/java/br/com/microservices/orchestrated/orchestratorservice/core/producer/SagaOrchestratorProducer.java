package br.com.microservices.orchestrated.orchestratorservice.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author aaristides
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SagaOrchestratorProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(final String payload, final String topic) {
        try {
            log.info("Sending event to topic {} with data {}", topic, payload);
            this.kafkaTemplate.send(topic, payload);
        } catch (Exception e) {
            log.error("Error trying to send data to topic {} with data {}", topic, payload, e);
        }
    }

}

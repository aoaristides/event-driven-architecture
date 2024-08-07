package br.com.microservices.orchestrated.orchestratorservice.core.consumer;

import br.com.microservices.orchestrated.orchestratorservice.core.services.OrchestratorService;
import br.com.microservices.orchestrated.orchestratorservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author aaristides
 */
@Component
@Slf4j
@AllArgsConstructor
public class SagaOrchestratorConsumer {

    private final JsonUtil jsonUtil;
    private final OrchestratorService orchestratorService;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.start-saga}"
    )
    public void consumeStartSagaEvent(final String payload) {
        log.info("Receiving event {} from start-saga topic", payload);
        final var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
        orchestratorService.startSaga(event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.orchestrator}"
    )
    public void consumeOrchestratorEvent(String payload) {
        log.info("Receiving event {} from orchestrator topic", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
        orchestratorService.continueSaga(event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-success}"
    )
    public void consumeFinishSagaSuccessEvent(String payload) {
        log.info("Receiving event {} from finish-success topic", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
        orchestratorService.finishSagaSuccess(event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-fail}"
    )
    public void consumeFinishSagaFailEvent(String payload) {
        log.info("Receiving event {} from finish-fail topic", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
        orchestratorService.finishSagaFail(event);
    }

}

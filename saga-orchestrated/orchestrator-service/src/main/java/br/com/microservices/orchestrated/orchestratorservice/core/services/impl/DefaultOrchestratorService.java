package br.com.microservices.orchestrated.orchestratorservice.core.services.impl;

import br.com.microservices.orchestrated.orchestratorservice.core.dtos.Event;
import br.com.microservices.orchestrated.orchestratorservice.core.dtos.History;
import br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopics;
import br.com.microservices.orchestrated.orchestratorservice.core.producer.SagaOrchestratorProducer;
import br.com.microservices.orchestrated.orchestratorservice.core.saga.SagaExecutionController;
import br.com.microservices.orchestrated.orchestratorservice.core.services.OrchestratorService;
import br.com.microservices.orchestrated.orchestratorservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.microservices.orchestrated.orchestratorservice.core.enums.EEventSource.ORCHESTRATOR;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ESagaStatus.FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ESagaStatus.SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopics.NOTIFY_ENDING;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultOrchestratorService implements OrchestratorService {

    private final JsonUtil jsonUtil;
    private final SagaOrchestratorProducer sagaOrchestratorProducer;
    private final SagaExecutionController sagaExecutionController;

    @Override
    public void startSaga(final Event event) {
        event.setSource(ORCHESTRATOR);
        event.setStatus(SUCCESS);
        var topic = getTopic(event);
        log.info("SAGA STARTED!");
        addHistory(event, "Saga Started!");
        sendToProducerWithTopic(event, topic);
    }

    @Override
    public void finishSagaSuccess(final Event event) {
        event.setSource(ORCHESTRATOR);
        event.setStatus(SUCCESS);
        log.info("SAGA FINISHED SUCCESSFULLY FOR EVENT: {}", event.getId());
        addHistory(event, "Saga finished successfully!");
        notifyFinishedSaga(event);
    }

    @Override
    public void finishSagaFail(final Event event) {
        event.setSource(ORCHESTRATOR);
        event.setStatus(FAIL);
        log.info("SAGA FINISHED WITH ERRORS FOR EVENT: {}", event.getId());
        addHistory(event, "Saga finished with errors!");
        notifyFinishedSaga(event);
    }

    @Override
    public void continueSaga(final Event event) {
        var topic = getTopic(event);
        log.info("SAGA CONTINUING FOR EVENT: {}", event.getId());
        sendToProducerWithTopic(event, topic);
    }

    private ETopics getTopic(final Event event) {
        return sagaExecutionController.getNextTopic(event);
    }

    private void notifyFinishedSaga(final Event event) {
        sendToProducerWithTopic(event, NOTIFY_ENDING);
    }

    private void sendToProducerWithTopic(final Event event, final ETopics topic) {
        sagaOrchestratorProducer.sendEvent(jsonUtil.toJson(event), topic.getTopic());
    }

    private void addHistory(final Event event, final String message) {
        final var history = History
                .builder()
                .source(event.getSource())
                .status(event.getStatus())
                .message(message)
                .createdAt(LocalDateTime.now())
                .build();
        event.addToHistory(history);
    }
}

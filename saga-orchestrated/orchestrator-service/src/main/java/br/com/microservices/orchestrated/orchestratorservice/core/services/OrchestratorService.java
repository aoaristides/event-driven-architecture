package br.com.microservices.orchestrated.orchestratorservice.core.services;

import br.com.microservices.orchestrated.orchestratorservice.core.dtos.Event;

/**
 * @author anderson
 */
public interface OrchestratorService {

    void startSaga(final Event event);

    void finishSagaSuccess(final Event event);

    void finishSagaFail(final Event event);

    void continueSaga(final Event event);

}

package br.com.makersweb.mscustomer.infrastructure.services;

/**
 * @author aaristides
 */
public interface EventService {

    void send(final Object event);

}

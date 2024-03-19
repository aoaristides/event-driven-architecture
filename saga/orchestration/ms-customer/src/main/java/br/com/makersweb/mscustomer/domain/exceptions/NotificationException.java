package br.com.makersweb.mscustomer.domain.exceptions;

import br.com.makersweb.mscustomer.domain.validation.handler.Notification;

/**
 * @author aaristides
 */
public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }

}

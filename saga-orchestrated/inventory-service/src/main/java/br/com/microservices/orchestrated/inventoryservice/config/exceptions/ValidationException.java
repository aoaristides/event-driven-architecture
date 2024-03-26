package br.com.microservices.orchestrated.inventoryservice.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author aaristides
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends NoStacktraceException {

    public ValidationException(String message) {
        super(message);
    }

}

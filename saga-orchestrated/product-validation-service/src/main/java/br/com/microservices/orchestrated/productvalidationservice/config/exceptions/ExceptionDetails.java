package br.com.microservices.orchestrated.productvalidationservice.config.exceptions;

/**
 * @author aaristides
 * @param status
 * @param message
 */
public record ExceptionDetails(int status, String message) {
}

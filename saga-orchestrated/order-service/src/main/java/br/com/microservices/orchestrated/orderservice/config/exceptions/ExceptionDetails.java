package br.com.microservices.orchestrated.orderservice.config.exceptions;

/**
 * @author aaristides
 * @param status
 * @param message
 */
public record ExceptionDetails(int status, String message) {
}

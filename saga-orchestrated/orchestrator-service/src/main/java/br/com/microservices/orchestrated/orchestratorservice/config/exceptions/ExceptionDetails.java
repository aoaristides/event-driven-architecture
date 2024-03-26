package br.com.microservices.orchestrated.orchestratorservice.config.exceptions;

/**
 * @author aaristides
 * @param status
 * @param message
 */
public record ExceptionDetails(int status, String message) {
}

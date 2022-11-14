package br.com.felipe.gadelha.coffeandit.transactionbff.domain.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}

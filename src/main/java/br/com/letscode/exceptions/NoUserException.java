package br.com.letscode.exceptions;

public class NoUserException extends RuntimeException {
    public NoUserException(String message) {
        super(message);
    }
}

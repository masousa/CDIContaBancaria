package br.com.letscode.exceptions;

public class NoUserException extends RuntimeException {
    public NoUserException() {
        super("Usuário não encontrado");
    }

    public NoUserException(String message) {
        super(message);
    }
}

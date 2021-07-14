package br.com.letscode.exceptions;

public class PasswordAuthenticationError extends RuntimeException {
    public PasswordAuthenticationError(String senha_errada) {
        super(senha_errada);
    }
}

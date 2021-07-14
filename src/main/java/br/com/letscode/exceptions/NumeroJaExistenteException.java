package br.com.letscode.exceptions;

public class NumeroJaExistenteException extends RuntimeException {
    public NumeroJaExistenteException() {
        super("Já existe uma conta com este número");
    }
}

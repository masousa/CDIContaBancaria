package br.com.letscode.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(){
        super("Saldo insuficiente");
    }
    public SaldoInsuficienteException(String message){
        super(message);
    }
}

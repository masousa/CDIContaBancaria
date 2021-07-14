package br.com.letscode.service;

import br.com.letscode.dominio.Conta;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ContaService {
    Conta criarConta(Conta conta) throws IOException;


    int sacar(BigDecimal valor, Conta conta, String senha) throws IOException;


    int depositar(BigDecimal valor, Conta conta, String senha) throws IOException;


    BigDecimal saldo(Conta conta, String senha) throws IOException;

    String validateNumeroConta(String next) throws IOException;

    Conta getConta(String numeroConta) throws IOException;

    List<Conta> listarContaUsuario(String cpf) throws IOException;
}

package br.com.letscode.view;

import br.com.letscode.dominio.Conta;

import java.util.Scanner;

public interface ContaView {
    Conta createConta(Scanner sc);

    void sacar(Scanner sc);

    void depositar(Scanner sc);
}

package br.com.letscode.service;

import br.com.letscode.TipoConta;
import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.ContaEnum;

@TipoConta(value = ContaEnum.POUPANCA)
public class ContaPoupancaServiceImpl implements ContaService {

    @Override
    public Conta criarConta(Conta conta) {
        System.out.println("conta poupanca");
        return null;
    }
}

package br.com.letscode.service;

import br.com.letscode.Dao.ContaDao;
import br.com.letscode.TipoConta;
import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.ContaEnum;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;

@TipoConta(value = ContaEnum.POUPANCA)
public class ContaPoupancaServiceImpl extends ContaHelper implements ContaService {

    private BigDecimal taxa = new BigDecimal(0.07D);


    @Inject
    public ContaPoupancaServiceImpl(ContaDao contaDao) {
        super(contaDao);
    }

    @Override
    public int sacar(BigDecimal valor, Conta conta, String senha) throws IOException {
        BigDecimal valorTaxa = valor.multiply(taxa);
        BigDecimal valorASerSacado = valor.add(valorTaxa);
        return super.sacar(valorASerSacado, conta, senha);
    }

    @Override
    public int depositar(BigDecimal valor, Conta conta, String senha) throws IOException {
        BigDecimal valorTaxa = valor.multiply(taxa);
        BigDecimal valorASerDepositado = valor.subtract(valorTaxa);
        return super.depositar(valorASerDepositado, conta, senha);
    }

    @Override
    public Conta criarConta(Conta conta) throws IOException {
        conta.setSaldo(BigDecimal.valueOf(100D));
        return super.criarConta(conta);
    }
}

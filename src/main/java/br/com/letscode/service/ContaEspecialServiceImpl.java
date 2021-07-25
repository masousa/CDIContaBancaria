package br.com.letscode.service;

import br.com.letscode.Dao.ContaDao;
import br.com.letscode.TipoConta;
import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.ContaEnum;
import br.com.letscode.dominio.ContaEspecial;
import br.com.letscode.exceptions.SaldoInsuficienteException;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;

@TipoConta(value = ContaEnum.ESPECIAL)
public class ContaEspecialServiceImpl extends ContaHelper implements ContaService {

    private ContaDao contaDao;

    @Inject
    public ContaEspecialServiceImpl(ContaDao contaDao) {
        super(contaDao);
        this.contaDao = contaDao;
    }


    @Override
    public int sacar(BigDecimal valor, Conta conta, String senha) throws IOException {
        ContaEspecial contaEspecial = (ContaEspecial) conta;
        BigDecimal saldoSubtraido = contaEspecial.getSaldo().subtract(valor);
        BigDecimal saldoLimiteSubtraido = BigDecimal.ZERO;
        if (saldoLimiteSubtraido.compareTo(BigDecimal.ZERO) <= 0) {
            saldoLimiteSubtraido = contaEspecial.getLimite().add(saldoSubtraido);
        }
        //Não tem saldo mas tem limite
        if (saldoSubtraido.compareTo(BigDecimal.ZERO) < 0 && saldoLimiteSubtraido.compareTo(BigDecimal.ZERO) > 0) {

            login(contaEspecial, senha);
            contaEspecial.setSaldo(BigDecimal.ZERO);
            contaEspecial.setLimite(saldoLimiteSubtraido);
            contaDao.modificarRegistro(contaEspecial, contaEspecial.getIdentificador());
        } else if (saldoSubtraido.compareTo(BigDecimal.ZERO) < 0 && saldoLimiteSubtraido.compareTo(BigDecimal.ZERO) < 0) {
            //não tem saldo nem limite
            throw new SaldoInsuficienteException();
        } else {
            //tem saldo
            return super.sacar(valor, contaEspecial, senha);
        }
        return 0;

    }

    @Override
    public int depositar(BigDecimal valor, Conta conta, String senha) throws IOException {
        ContaEspecial contaEspecial = (ContaEspecial) conta;
        BigDecimal limiteCalculado = contaEspecial.getLimite().add(valor);

        BigDecimal saldoRestante = valor;
        final BigDecimal valorTetoLimite = new BigDecimal(200);
        if (limiteCalculado.compareTo(valorTetoLimite) > 0) {
            saldoRestante = limiteCalculado.subtract(valorTetoLimite);
        }

        login(contaEspecial, senha);
        contaEspecial.setSaldo(contaEspecial.getSaldo().add(saldoRestante));
        contaEspecial.setLimite(valorTetoLimite);
        contaDao.modificarRegistro(contaEspecial, conta.getIdentificador());
        return 1;
    }

    @Override
    public Conta criarConta(Conta conta) throws IOException {
        ((ContaEspecial) conta).setLimite(new BigDecimal(200D));
        conta.setSaldo(BigDecimal.valueOf(400D));
        return super.criarConta(conta);
    }
}

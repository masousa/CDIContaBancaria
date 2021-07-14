package br.com.letscode.service;

import br.com.letscode.Dao.ContaDao;
import br.com.letscode.dominio.Conta;
import br.com.letscode.exceptions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class ContaHelper implements ContaService {
    private ContaDao contaDao;

    public ContaHelper(ContaDao contaDao) {
        this.contaDao = contaDao;
    }

    public void login(Conta conta, String senha) {
        if (!conta.getSenha().equals(senha)) {
            throw new PasswordAuthenticationError("Senha errada");
        }
    }

    @Override
    public int sacar(BigDecimal valor, Conta conta, String senha) throws IOException {
        login(conta, senha);
        final BigDecimal subtract = conta.getSaldo().subtract(valor);
        if (subtract.compareTo(BigDecimal.ZERO) > 0) {
            conta.setSaldo(subtract);
            contaDao.modificarRegistro(conta, conta.getIdentificador());
            return 1;
        } else {
            throw new SaldoInsuficienteException();
        }

    }

    @Override
    public int depositar(BigDecimal valor, Conta conta, String senha) throws IOException {
        login(conta, senha);
        conta.setSaldo(conta.getSaldo().add(valor));
        contaDao.modificarRegistro(conta, conta.getIdentificador());
        return 1;
    }

    @Override
    public BigDecimal saldo(Conta conta, String senha) throws IOException {
        return contaDao.getRegistro(conta.getIdentificador()).getSaldo();
    }

    @Override
    public Conta criarConta(Conta conta) throws IOException {
        System.out.println("criando conta especial");
        conta.setSaldo(BigDecimal.ZERO);
        conta.setCaminhoArquivo("teste");
        if (null == conta.getUsuario()) {
            throw new NoUserException("Usuário inexistente");
        }
        contaDao.inserirRegistro(conta);
        return conta;
    }

    @Override
    public String validateNumeroConta(String numeroConta) throws IOException {
        Optional<Conta> optionalConta = Optional.ofNullable(contaDao.getRegistro(numeroConta));
        if (optionalConta.isPresent()) {
            throw new NumeroJaExistenteException();
        }
        return numeroConta;
    }

    @Override
    public Conta getConta(String numeroConta) throws IOException {
        Optional<Conta> optionalConta = Optional.ofNullable(contaDao.getRegistro(numeroConta));
        return optionalConta.orElseThrow(ContaInexistenteException::new);
    }

    @Override
    public List<Conta> listarContaUsuario(String cpf) throws IOException {
        List<Conta> contas = contaDao.listarRegistros();
        return contas.stream().filter(conta -> conta.getUsuario().getCpf().equals(cpf))
                .collect(Collectors.toList());
    }
}

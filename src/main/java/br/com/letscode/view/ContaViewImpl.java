package br.com.letscode.view;

import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.ContaEnum;
import br.com.letscode.dominio.ContaEspecial;
import br.com.letscode.dominio.Usuario;
import br.com.letscode.exceptions.ContaInexistenteException;
import br.com.letscode.exceptions.NoUserException;
import br.com.letscode.exceptions.NumeroJaExistenteException;
import br.com.letscode.exceptions.SaldoInsuficienteException;
import br.com.letscode.service.ContaFactory;
import br.com.letscode.service.ContaService;
import br.com.letscode.service.UsuarioService;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

public class ContaViewImpl implements ContaView {
    @Inject
    private ContaFactory contaFactory;

    @Inject
    private UsuarioService usuarioService;

    private ContaService contaService;

    @Override
    public Conta createConta(Scanner sc) {
        try {
            Conta conta = obterConta(sc);
            return contaService.criarConta(conta);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.println("Não foi possível abrir o arquivo");
        }
        return null;
    }

    @Override
    public void depositar(Scanner sc) {
        try {
            Conta conta = findConta(sc);
            contaService = contaFactory.createConta(conta.getTipoConta());
            System.out.println("Valor a ser depositado");
            BigDecimal bigDecimal = new BigDecimal(sc.next());
            System.out.println("Senha para a operação");
            contaService.depositar(bigDecimal, conta, sc.next());
            System.out.println("Operação realizada com sucesso");
            imprimirSaldo(conta.getIdentificador());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.println("Não foi possível abrir o arquivo");
        }
    }

    public void sacar(Scanner sc) {
        try {
            Conta conta = findConta(sc);
            contaService = contaFactory.createConta(conta.getTipoConta());
            System.out.println("Valor a ser sacado");
            BigDecimal bigDecimal = new BigDecimal(sc.next());
            System.out.println("Senha para a operação");
            contaService.sacar(bigDecimal, conta, sc.next());
            System.out.println("Operação realizada com sucesso");
            imprimirSaldo(conta.getIdentificador());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.println("Não foi possível abrir o arquivo");
        } catch (SaldoInsuficienteException saldoInsuficienteException) {
            System.err.println(saldoInsuficienteException.getMessage());
        }
    }

    private void imprimirSaldo(String identificador) throws IOException {
        Conta conta = contaService.getConta(identificador);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Conta de número %s \n", conta.getNumeroConta()));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        builder.append(String.format("\t  Saldo: %s \n", decimalFormat.format(conta.getSaldo())));
        if (conta.getTipoConta().equals(ContaEnum.ESPECIAL)) {
            builder.append(String.format("\t  Limite: %s \n", decimalFormat.format(((ContaEspecial) conta).getLimite())));
        }
        System.out.println(builder.toString());
    }

    private Conta findConta(Scanner sc) throws IOException {
        System.out.println("Informe o número da conta");
        String numeroConta = sc.next();
        if (Objects.isNull(contaService)) {
            contaService = contaFactory.createConta(ContaEnum.ESPECIAL);
        }
        try {
            return contaService.getConta(numeroConta);
        } catch (ContaInexistenteException contaInexistenteException) {
            System.err.println("Número não encontrado");
        }
        return findConta(sc);
    }


    private Conta obterConta(Scanner sc) throws IOException {
        Conta conta = null;
        ContaEnum contaEnum = extrairOpcao(sc);
        if (contaEnum.equals(ContaEnum.ESPECIAL)) {
            conta = new ContaEspecial();
        } else {
            conta = new Conta();
        }
        contaService = contaFactory.createConta(contaEnum);
        conta.setTipoConta(contaEnum);
        System.out.println("Vamos a criação da conta");

        conta.setNumeroConta(obterNumeroConta(sc));
        conta.setSenha(obterSenha(sc));
        conta.setUsuario(obterUsuario(sc));
        return conta;
    }

    private String obterNumeroConta(Scanner sc) throws IOException {
        try {
            System.out.println("Informe o número da conta");
            return contaService.validateNumeroConta(sc.next());
        } catch (NumeroJaExistenteException numeroJaExistenteException) {
            System.err.println(numeroJaExistenteException.getMessage());
        }
        return obterNumeroConta(sc);
    }

    private String obterSenha(Scanner sc) {
        System.out.println("Informe a senha da conta");
        String senha = sc.next();
        System.out.println("Informe novamente a senha da conta");
        String confirmacaoSenha = sc.next();
        if (senha.equals(confirmacaoSenha)) {
            return senha;
        }
        System.out.println("as senha não conferem");
        return obterSenha(sc);
    }

    private Usuario obterUsuario(Scanner sc) {
        System.out.println("Informe o CPF do usuário");
        String cpf = sc.next();
        try {
            return usuarioService.findUsuario(cpf);
        } catch (NoUserException noUserException) {
            System.err.println(noUserException.getMessage());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.println("Não foi possível abrir o arquivo");
        }
        return null;
    }

    private ContaEnum extrairOpcao(Scanner sc) {
        System.out.println("Qual o tipo de conta a ser criada? \n 1 - Especial; \n 2 - Poupança");
        Integer opcao = sc.nextInt();
        ContaEnum contaEnum = null;
        switch (opcao) {
            case 1:
                contaEnum = ContaEnum.ESPECIAL;
                break;
            case 2:
                contaEnum = ContaEnum.POUPANCA;
                break;
            default:
                System.out.println("Opção inválida. Foi selecionada a opção poupança");
                contaEnum = ContaEnum.POUPANCA;
        }
        return contaEnum;
    }
}

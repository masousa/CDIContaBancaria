package br.com.letscode.view;

import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.ContaEnum;
import br.com.letscode.service.ContaFactory;
import br.com.letscode.service.ContaService;

import javax.inject.Inject;
import java.util.Scanner;

public class ContaViewImpl implements ContaView {
    @Inject
    private ContaFactory contaFactory;

    @Override
    public Conta createConta(Scanner sc) {
        Conta conta = new Conta();
        System.out.println("Qual o tipo de conta a ser criada? \n 1 - Especial; \n 2 - Poupança");
        Integer opcao = sc.nextInt();
        ContaEnum contaEnum = null;
        switch (opcao) {
            case 1:
                contaEnum = ContaEnum.ESPECIAL;
                break;
            case 2:
                contaEnum = ContaEnum.POUPANCA;
        }
        ContaService contaService = contaFactory.createConta(contaEnum);
        return contaService.criarConta(conta);
    }
}

package br.com.letscode.aplicacao;

import br.com.letscode.dominio.Usuario;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final WeldContainer container = new Weld().initialize();
        final Aplicacao aplicacao = container.select(Aplicacao.class).get();
        init(aplicacao);
    }

    private static void init(Aplicacao aplicacao) {
        int opcao = 0;
        System.out.println("Bem vindo");
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("O que gostaria de fazer? \n 1 - Cadastrar usuário \n 2 - Criar conta \n 0 - Sair \n");
            opcao = sc.nextInt();
            definirOpcao(sc, opcao, aplicacao);
        } while (opcao > 0);
    }

    private static void definirOpcao(Scanner sc, int opcao, Aplicacao aplicacao) {
        switch (opcao) {
            case 1:
                aplicacao.createUsuario(sc);
                aplicacao.getUsuarios().stream()
                        .map(Usuario::getCaminhoArquivo).forEach(System.out::println);
                break;
            case 2:
                aplicacao.createConta(sc);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("digite um valor válido");
        }
    }
}

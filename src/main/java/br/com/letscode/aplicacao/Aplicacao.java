package br.com.letscode.aplicacao;

import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.ContaEnum;
import br.com.letscode.dominio.Usuario;
import br.com.letscode.service.ContaFactory;
import br.com.letscode.service.ContaService;
import br.com.letscode.view.UsuarioView;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    private List<Usuario> usuarios;
    @Inject
    private UsuarioView usuarioView;

    @Inject
    private ContaFactory contaFactory;

    @PostConstruct
    public void iniciar() {
        System.out.println("iniciando a aplicacao");
        usuarios = new ArrayList<>();
    }

    public void createUsuario(Scanner sc) {
        getUsuarios().add(usuarioView.create(sc));
    }

    public Conta createConta(ContaEnum contaEnum, Conta conta) {
        final ContaService contaFactoryConta = contaFactory.createConta(contaEnum);
        return contaFactoryConta.criarConta(conta);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}

package br.com.letscode.aplicacao;

import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.Usuario;
import br.com.letscode.view.ContaView;
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
    private ContaView contaView;

    @PostConstruct
    public void iniciar() {
        System.out.println("iniciando a aplicacao");
        usuarios = new ArrayList<>();
    }

    public void createUsuario(Scanner sc) {
        getUsuarios().add(usuarioView.create(sc));
    }

    public Conta createConta(Scanner sc) {

        return contaView.createConta(sc);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}

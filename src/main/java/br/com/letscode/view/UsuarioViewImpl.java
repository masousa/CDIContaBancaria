package br.com.letscode.view;

import br.com.letscode.dominio.Usuario;
import br.com.letscode.exceptions.PrecondicaoException;
import br.com.letscode.service.UsuarioService;

import javax.inject.Inject;
import java.util.Scanner;

public class UsuarioViewImpl implements UsuarioView {
    @Inject
    private UsuarioService usuarioService;

    @Override
    public Usuario create(Scanner sc) {
        Usuario usuario = new Usuario();
        System.out.println("Informe o nome do usuário");
        usuario.setNome(sc.next());
        System.out.println("Informe a idade do usuário");
        usuario.setIdade(sc.nextInt());
        System.out.printf("Usuário %s criado com sucesso \n", usuario.getNome());
        try {
            return usuarioService.create(usuario);
        } catch (PrecondicaoException e) {
            System.err.println(e.getMessage());
            return new Usuario();
        }
    }
}

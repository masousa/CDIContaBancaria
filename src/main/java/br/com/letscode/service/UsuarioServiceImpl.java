package br.com.letscode.service;

import br.com.letscode.Dao.UsuarioDAO;
import br.com.letscode.dominio.Usuario;
import br.com.letscode.exceptions.NoUserException;
import br.com.letscode.exceptions.PrecondicaoException;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {


    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario create(Usuario usuario) throws PrecondicaoException, IOException {
        System.out.println("passou aqui");
        if (usuario.getIdade() > 18) {
            usuarioDAO.inserirRegistro(usuario);
        }
        throw new PrecondicaoException("Usuario com precondições não satisfeitas");
    }

    @Override
    public Usuario findUsuario(String cpf) throws NoUserException, IOException {
        Optional<Usuario> optionalUsuario = Optional.of(usuarioDAO.getRegistro(cpf));
        return optionalUsuario.orElseThrow(NoUserException::new);
    }
}

package br.com.letscode.service;

import br.com.letscode.dominio.Usuario;
import br.com.letscode.exceptions.NoUserException;
import br.com.letscode.exceptions.PrecondicaoException;

import java.io.IOException;

public interface UsuarioService {
    Usuario create(Usuario usuario) throws PrecondicaoException, IOException;

    Usuario findUsuario(String cpf) throws NoUserException, IOException;
}

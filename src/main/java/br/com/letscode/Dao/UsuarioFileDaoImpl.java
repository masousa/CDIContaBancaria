package br.com.letscode.Dao;

import br.com.letscode.dominio.Usuario;

public class UsuarioFileDaoImpl implements UsuarioDAO {
    @Override
    public Usuario create(Usuario usuario) {
        //TODO salvar em um arquivo e resgatar o caminho do arquivo.
        usuario.setCaminhoArquivo("caminho do usuário");
        System.out.println("passou na persistencia");
        return usuario;
    }
}

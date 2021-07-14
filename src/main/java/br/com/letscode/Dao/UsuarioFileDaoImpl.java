package br.com.letscode.Dao;

import br.com.letscode.dominio.Usuario;

import java.util.StringTokenizer;

public class UsuarioFileDaoImpl extends DaoFileManager<Usuario> implements UsuarioDAO {

    @Override
    public String getFile() {
        return "usuarios.csv";
    }

    @Override
    public Usuario mapRegister(String file) {
        Usuario usuario = new Usuario();
        StringTokenizer token = new StringTokenizer(file, ";");
        usuario.setNome(token.nextToken());
        usuario.setIdade(Integer.parseInt(token.nextToken()));
        usuario.setCpf(token.nextToken());
        return usuario;
    }

    @Override
    public String toStringFile(Usuario entidade) {
        return String.format("%s;%d;%s\r\n",entidade.getNome(),entidade.getIdade(),entidade.getCpf().replace(".",""));
    }

    @Override
    protected void setRecord(Usuario fileToChange, Usuario entidade) {
        fileToChange.setNome(entidade.getNome());
        fileToChange.setIdade(entidade.getIdade());
    }
}

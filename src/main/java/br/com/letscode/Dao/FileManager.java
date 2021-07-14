package br.com.letscode.Dao;

import br.com.letscode.dominio.Entidade;

import java.io.IOException;
import java.util.List;

public interface FileManager<E extends Entidade> {
    public void inserirRegistro(E entidade) throws IOException;

    public void modificarRegistro(E entidade, String identificador) throws IOException;

    public void removerRegistro(String identificador) throws IOException;

    public List<E> listarRegistros() throws IOException;

    public E getRegistro(String registro) throws IOException;
}

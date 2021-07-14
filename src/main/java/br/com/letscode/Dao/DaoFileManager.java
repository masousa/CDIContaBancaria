package br.com.letscode.Dao;

import br.com.letscode.dominio.Entidade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class DaoFileManager<E extends Entidade> implements FileManager<E> {

    private Path getPath() {
        try {
            Path path = Paths.get(getClass().getResource(File.separator + "arquivos" + File.separator + getFile()).toURI());
            if (!path.toFile().exists()) {
                Files.createFile(path);
            }
            return path;
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void inserirRegistro(E entidade) throws IOException {
        write(toStringFile(entidade), StandardOpenOption.APPEND);
    }

    @Override
    public List<E> listarRegistros() throws IOException {
        List<E> entidades;
        try (BufferedReader br = Files.newBufferedReader(getPath())) {
            entidades = br.lines().filter(Objects::nonNull).filter(Predicate.not(String::isEmpty)).map(this::mapRegister).collect(Collectors.toList());
        }
        return entidades;
    }

    @Override
    public E getRegistro(String registro) throws IOException {
        List<E> entidades;
        Optional<String> optionalStr;
        try (BufferedReader br = Files.newBufferedReader(getPath())) {

            optionalStr = br.lines().filter(Objects::nonNull).filter(Predicate.not(String::isEmpty))
                    .filter(s -> s.contains(registro)).findFirst();
        }
        return optionalStr.map(this::mapRegister).orElse(null);
    }

    @Override
    public void modificarRegistro(E entidade, String registro) throws IOException {
        List<E> registros = listarRegistros();
        Optional<E> optionalE = registros.stream()
                .filter(clienteSearch -> clienteSearch.getIdentificador().equals(registro)).findFirst();
        if (optionalE.isPresent()) {
            setRecord(optionalE.get(), entidade);
            reescreverArquivo(registros);
        }
    }

    @Override
    public void removerRegistro(String identificador) throws IOException {
        List<E> eList = listarRegistros();
        List<E> eResultList = new ArrayList<>();
        for (E eObject : eList) {
            if (!eObject.getIdentificador().equals(identificador)) {
                eResultList.add(eObject);
            }
        }
        eraseContent();
        reescreverArquivo(eResultList);
    }

    private void reescreverArquivo(List<E> eList) throws IOException {

        StringBuilder builder = new StringBuilder();
        for (E eObject : eList) {
            builder.append(toStringFile(eObject));
        }

        write(builder.toString(), StandardOpenOption.CREATE);
    }


    private void write(String clienteStr, StandardOpenOption option) throws IOException {

        try (BufferedWriter bf = Files.newBufferedWriter(getPath(), option)) {
            bf.flush();
            bf.write(clienteStr);
        }
    }

    public void eraseContent() throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(getPath());
        writer.write("");
        writer.flush();
    }

    public abstract String getFile();

    public abstract E mapRegister(String file);

    public abstract String toStringFile(E entidade);

    protected abstract void setRecord(E fileToChange, E entidade);
}

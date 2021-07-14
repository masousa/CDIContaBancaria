package br.com.letscode.dominio;

import lombok.Data;

import java.util.StringTokenizer;

@Data
public class Usuario implements Entidade{

    private String nome;
    private Integer idade;
    String cpf;
    private String caminhoArquivo;


    public String toStringFile() {
        return String.format("%n;%d;%n;%n",nome,idade,cpf.replace(".",""),caminhoArquivo);

    }

    public Entidade toObject(String file) {
        StringTokenizer token = new StringTokenizer(file, ";");
        nome = token.nextToken();
        idade = Integer.parseInt(token.nextToken());
        cpf = token.nextToken();
        caminhoArquivo = token.nextToken();
        return this;
    }

    @Override
    public String getIdentificador() {
        return cpf;
    }
}

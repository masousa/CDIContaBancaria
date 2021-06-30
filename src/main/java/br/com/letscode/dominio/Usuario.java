package br.com.letscode.dominio;

public class Usuario {

    private String nome;
    private Integer idade;
    private String caminhoArquivo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
}

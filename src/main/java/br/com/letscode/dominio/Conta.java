package br.com.letscode.dominio;

import lombok.Data;

import java.math.BigDecimal;
import java.util.StringTokenizer;

@Data
public class Conta implements Entidade{

    private String numeroConta;
    private String senha;
    private Usuario usuario;
    private String caminhoArquivo;
    private ContaEnum tipoConta;
    private BigDecimal saldo;


    @Override
    public String getIdentificador() {
        return numeroConta;
    }
}

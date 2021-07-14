package br.com.letscode.dominio;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaEspecial extends Conta{
    private BigDecimal limite;

}

package br.com.letscode.service;

import br.com.letscode.TipoConta;
import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.ContaEnum;
import br.com.letscode.exceptions.NoUserException;

@TipoConta(value = ContaEnum.ESPECIAL)
public class ContaEspecialServiceImpl implements ContaService {

    @Override
    public Conta criarConta(Conta conta) {
        System.out.println("criando conta especial");
        conta.setCaminhoArquivo("teste");
        if (null == conta.getUsuario()) {
            throw new NoUserException("Usuário inexistente");
        }
        return conta;
    }
}

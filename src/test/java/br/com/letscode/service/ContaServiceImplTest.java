package br.com.letscode.service;

import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.Usuario;
import br.com.letscode.exceptions.NoUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ContaServiceImplTest {

    private ContaEspecialServiceImpl contaEspecialService;

    @Before
    public void init() {
        contaEspecialService = new ContaEspecialServiceImpl(null);
    }

    @Test
    public void conta_criada_sucesso() throws IOException {
        Conta conta = new Conta();
        conta.setUsuario(new Usuario());
        Conta contaRetornada = contaEspecialService.criarConta(conta);
        Assert.assertTrue(!contaRetornada.getCaminhoArquivo().isEmpty());
    }

    @Test(expected = NoUserException.class)
    public void falhou_criacao_conta_sem_usuario() throws IOException{
        Conta conta = contaEspecialService.criarConta(new Conta());
        Assert.assertTrue(null != conta.getUsuario());
        Assert.assertTrue(null != conta.getUsuario().getCaminhoArquivo());
    }


}

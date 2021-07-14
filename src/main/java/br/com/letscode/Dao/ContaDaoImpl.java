package br.com.letscode.Dao;

import br.com.letscode.dominio.Conta;
import br.com.letscode.dominio.ContaEnum;
import br.com.letscode.dominio.ContaEspecial;
import br.com.letscode.dominio.Usuario;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class ContaDaoImpl extends DaoFileManager<Conta> implements ContaDao {
    @Override
    public String getFile() {
        return "contas.csv";
    }

    @Override
    public Conta mapRegister(String file) {
        Conta conta;
        StringTokenizer token = new StringTokenizer(file, ";");
        String numeroConta = token.nextToken();
        String senha = token.nextToken();
        Usuario usuario = new Usuario();
        usuario.setCpf(token.nextToken());

        String caminhoArquivo = (token.nextToken());
        ContaEnum tipoConta = ContaEnum.valueOf(token.nextToken());
        BigDecimal saldo = new BigDecimal(token.nextToken());
        if (tipoConta.equals(ContaEnum.ESPECIAL)) {
            conta = new ContaEspecial();
            ((ContaEspecial) conta).setLimite(new BigDecimal(token.nextToken()));
        } else {
            conta = new Conta();
        }
        conta.setNumeroConta(numeroConta);
        conta.setSenha(senha);
        conta.setUsuario(usuario);
        conta.setCaminhoArquivo(caminhoArquivo);
        conta.setTipoConta(tipoConta);
        conta.setSaldo(saldo);

        return conta;
    }

    @Override
    public String toStringFile(Conta entidade) {
        if (entidade.getTipoConta().equals(ContaEnum.ESPECIAL)) {
            ContaEspecial contaEspecial = (ContaEspecial) entidade;
            return String.format("%s;%s;%s;%s;%s;%s;%s\r\n", entidade.getNumeroConta(), entidade.getSenha(),
                    entidade.getUsuario().getCpf(), entidade.getCaminhoArquivo()
                    , entidade.getTipoConta().toString(), entidade.getSaldo().toString()
                    , contaEspecial.getLimite().toString());
        }
        return String.format("%s;%s;%s;%s;%s;%s\r\n", entidade.getNumeroConta(), entidade.getSenha(),
                entidade.getUsuario().getCpf(), entidade.getCaminhoArquivo()
                , entidade.getTipoConta().toString(), entidade.getSaldo().toString());
    }


    @Override
    protected void setRecord(Conta fileToChange, Conta entidade) {
        fileToChange.setSaldo(entidade.getSaldo());
        if (fileToChange.getTipoConta().equals(ContaEnum.ESPECIAL)) {
            ((ContaEspecial) fileToChange).setLimite(((ContaEspecial) entidade).getLimite());
        }
    }
}

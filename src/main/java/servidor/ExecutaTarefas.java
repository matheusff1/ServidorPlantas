package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ExecutaTarefas extends Thread{
    private Socket conexao;

    public ExecutaTarefas(Socket conexao) throws Exception {
        if (conexao == null) throw new Exception("Conexao nula");
        this.conexao = conexao;
    }
    public void run() {
        ObjectInputStream receptor = null;
        ObjectOutputStream transmissor = null;
        Comunicacao trabalhador = null;
        try {
            receptor = new ObjectInputStream(this.conexao.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            trabalhador = new Comunicacao(this.conexao, receptor, transmissor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Object recebido = null;
        try {
            recebido = trabalhador.cloneObjetoRecebido();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (recebido instanceof Vendedor) ConectaMongo.salvarVendedor((Vendedor) recebido);
        else if (recebido instanceof Produto) ConectaMongo.salvarProduto((Produto) recebido);
        else if (recebido instanceof String) {
            if (recebido.toString().equals("VENDEDORES")) {
                try {
                    trabalhador.enviar(ConectaMongo.buscarVendedores());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (recebido.toString().equals("PRODUTOS")) {
                try {
                    trabalhador.enviar(ConectaMongo.buscarProdutos());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Thread.yield();

        }
    }


}

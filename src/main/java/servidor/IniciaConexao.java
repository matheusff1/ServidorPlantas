package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class IniciaConexao extends Thread{

    private ServerSocket pedido;
    private boolean interrompido=true;


    public IniciaConexao(int porta) throws Exception {
        // Verifica se a porta não é nula e tenta criar um ServerSocket com a porta fornecida
        if (porta == 0) throw new Exception("Porta ausente");

        try {
            this.pedido = new ServerSocket(porta);
        } catch (Exception erro) {
            throw new Exception("Porta invalida");
        }
    }

    public void morre(){
        this.interrompido = false;
    }

    public void run (){
        Socket conexao = null;

        while(interrompido){
            try {
                conexao = this.pedido.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                ExecutaTarefas junin = new ExecutaTarefas(conexao);
                junin.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            PrintWriter escrita = new  PrintWriter(conexao.getOutputStream());//transmissor de string
            escrita.println("conexao interrompida");
            escrita.flush();
            conexao.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

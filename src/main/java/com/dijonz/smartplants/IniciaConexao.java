package com.dijonz.smartplants;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Classe que aceita e gerencia a conexão do servidor com clientes
public class IniciaConexao extends Thread{

    private ServerSocket pedido; // ServerSocket para aceitar conexões de clientes
    private boolean interrompido=true; // Flag para controlar a execução da thread

    // Construtor que recebe a porta como parâmetro e cria o ServerSocket
    public IniciaConexao(int porta) throws Exception {
        // Verifica se a porta não é nula e tenta criar um ServerSocket com a porta fornecida
        if (porta == 0) throw new Exception("Porta ausente");

        try {
            this.pedido = new ServerSocket(porta);
        } catch (Exception erro) {
            throw new Exception("Porta invalida");
        }
    }

    // Método para interromper a execução da thread
    public void morre(){
        this.interrompido = false;
    }

    // Método principal que será executado pela thread
    public void run (){
        Socket conexao = null;

        // Loop para aceitar conexões enquanto a thread estiver ativa
        while(interrompido){
            try {
                conexao = this.pedido.accept(); // Aguarda e aceita uma conexão de cliente
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Cria uma nova instância de ExecutaTarefas para lidar com a conexão do cliente
            ExecutaTarefas junin = null;
            try {
                junin = new ExecutaTarefas(conexao);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Inicia a thread para processar as tarefas do cliente
            junin.start();
        }
        // Quando a execução é interrompida, envia uma mensagem ao cliente e fecha a conexão
        try {
            assert conexao != null;
            PrintWriter escrita = new  PrintWriter(conexao.getOutputStream());//transmissor de string
            escrita.println("conexao interrompida");
            escrita.flush(); // envia
            conexao.close(); // fecha conexão com o cliente
            this.morre(); // Chama o método para encerrar a execução da thread
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

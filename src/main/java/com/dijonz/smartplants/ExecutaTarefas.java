package com.dijonz.smartplants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// Classe que executa tarefas em uma thread para processar operações recebidas do cliente
public class ExecutaTarefas extends Thread{
    private Socket conexao;
    private boolean executa=true;

    // Construtor que recebe a conexão como parâmetro
    public ExecutaTarefas(Socket conexao) throws Exception {
        if (conexao == null) throw new Exception("Conexao nula");
        this.conexao = conexao;
    }

    // Método para interromper a execução da thread
    private void stoop(){
        this.executa = false;
    }

    // Método principal que será executado pela thread
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
        // Loop principal da thread
        while(executa) {
            try {
                recebido = trabalhador.cloneObjetoRecebido();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Verifica o tipo do objeto recebido e realiza a operação correspondente e fecha a thread
            if (recebido instanceof Vendedor) {
                ConectaMongo.salvarVendedor((Vendedor) recebido);System.out.println("Novo vendedor salvo: "+ recebido.toString()); this.stoop();}
            else if (recebido instanceof Produto){
                ConectaMongo.salvarProduto((Produto) recebido);System.out.println("Novo produto salvo:"+recebido.toString());this.stoop();}
            else if (recebido instanceof Mensagem) {
                if (((Mensagem) recebido).isTipo()){
                    if (((Mensagem) recebido).getConteudo().equals("VENDEDORES")) {
                        try {
                            trabalhador.enviar(ConectaMongo.buscarVendedores());
                            System.out.println("Lista enviada.");
                            this.stoop();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else if (((Mensagem) recebido).getConteudo().equals("PRODUTOS")) {
                        try {
                            trabalhador.enviar(ConectaMongo.buscarProdutos());
                            System.out.println("Lista enviada.");
                            this.stoop();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else if (((Mensagem) recebido).getConteudo().equals("LOGIN")) {
                        try {
                            trabalhador.enviar(ConectaMongo.buscarUsuarioVendedor(((Mensagem) recebido).getEmail()));
                            System.out.println("Usário enviado.");
                            this.stoop();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else{
                    try {
                        trabalhador.enviar(ConectaMongo.buscarProdutosVendedor(((Mensagem) recebido).getConteudo()));
                        System.out.println("Lista enviada");
                        this.stoop();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else {
                // Se o objeto não for reconhecido, aguarda
                Thread.yield();

            }
        }
    }


}

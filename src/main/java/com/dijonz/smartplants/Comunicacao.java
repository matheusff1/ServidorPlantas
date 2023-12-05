package com.dijonz.smartplants;

import java.io.*;
import java.net.*;

// Classe responsável pela comunicação entre cliente e servidor
public class Comunicacao
{
    private Socket conexao; // Conexão com o servidor
    private ObjectInputStream receptor; // Fluxo de entrada para receber objetos
    private ObjectOutputStream transmissor; // Fluxo de saída para enviar objetos

    // Construtor que inicializa os membros da classe
    public Comunicacao (Socket             conexao,
                     ObjectInputStream  receptor,
                     ObjectOutputStream transmissor)
            throws Exception // se parametro nulos
    {
        // Verifica se os parâmetros são nulos e lança uma exceção em caso afirmativo
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (receptor==null)
            throw new Exception ("Receptor ausente");

        if (transmissor==null)
            throw new Exception ("Transmissor ausente");

        // Inicializa os membros da classe
        this.conexao     = conexao;
        this.receptor    = receptor;
        this.transmissor = transmissor;
    }

        // Método para enviar um objeto para o servidor
        public void enviar (Object x) throws Exception
    {
        try
        {
            // Escreve o objeto no fluxo de saída e o envia
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();
        }
        catch (IOException erro)
        {
            // Em caso de erro de transmissão, lança uma exceção
            throw new Exception ("Erro de transmissao",erro);
        }
    }

    // Método para clonar um objeto recebido do servidor
    public Object cloneObjetoRecebido() throws Exception{
        try {

            Object ret =null;
            // Lê o objeto do fluxo de entrada
            ret = receptor.readObject();
            // Verifica o tipo do objeto e retorna uma cópia clonada adequada
            if (ret instanceof Vendedor) return (((Vendedor) ret).clone());
            else if (ret instanceof Produto) return (((Produto) ret).clone());
            else if(ret instanceof Mensagem) return (((Mensagem) ret).clone());
            else return null;

        } catch (IOException | ClassNotFoundException e) {
            // Em caso de erro, lança uma exceção com a mensagem adequada
            throw new Exception("Erro ocorrido em: " + e.getMessage()+e.getClass()+e);
        }
    }


    // Método para fechar os fluxos e a conexão com o servidor
    public void adeus () throws Exception
    {
        try
        {
            this.transmissor.close();
            this.receptor   .close();
            this.conexao    .close();
        }
        catch (Exception erro)
        {
            // Em caso de erro ao desconectar, lança uma exceção
            throw new Exception ("Erro de desconexao");
        }
    }
}


package servidor;

import netscape.javascript.JSObject;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Comunicacao
{
    private Socket conexao;
    private ObjectInputStream receptor;
    private ObjectOutputStream transmissor;

    public Comunicacao (Socket             conexao,
                     ObjectInputStream  receptor,
                     ObjectOutputStream transmissor)
            throws Exception // se parametro nulos
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (receptor==null)
            throw new Exception ("Receptor ausente");

        if (transmissor==null)
            throw new Exception ("Transmissor ausente");

        this.conexao     = conexao;
        this.receptor    = receptor;
        this.transmissor = transmissor;
    }

        public void enviar (Object x) throws Exception
    {
        try
        {
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();
        }
        catch (IOException erro)
        {
            throw new Exception ("Erro de transmissao");
        }
    }

    public Object cloneObjetoRecebido() throws Exception{
        try {

            Object ret =null;
            ret = receptor.readObject();
            if (ret instanceof Vendedor) return (((Vendedor) ret).clone());
            else if (ret instanceof Produto) return (((Produto) ret).clone());
            else return null;

        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Erro ocorrido em: " + e.getMessage());
        }
    }




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
            throw new Exception ("Erro de desconexao");
        }
    }
}


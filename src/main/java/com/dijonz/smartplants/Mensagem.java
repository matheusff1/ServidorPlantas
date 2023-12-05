package com.dijonz.smartplants;

import java.io.Serializable;

// Classe Mensagem trata-se das mensagens trocadas entre cliente e servidor
public class Mensagem implements Serializable,Cloneable{

    // Flag para definir o tipo de mensagem:
    // false + email para lista de produtos de um único vendedor,
    // true + TIPO para listas completas,
    // true + email + "LOGIN" para login
    private boolean tipo;

    // true para retorno de listas completas e login
    // e false para retorno de listas de um vendedor especifico
    private String conteudo;

    // email do vendedor ou "VENDEDORES" para lista de vendedores e
    // "PRODUTOS" para lista de produtos, "LOGIN" para pedir um usuário
    private String email;

    // Construtor para mensagens sem email
    public Mensagem(Boolean tipo,String conteudo){
        this.conteudo=conteudo;
        this.tipo= tipo;
    }

    // Construtor para mensagens com email
    public Mensagem(Boolean tipo,String email, String conteudo){
        this.conteudo=conteudo;
        this.tipo= tipo;
        this.email = email;
    }

    // Construtor para clonar uma mensagem existente
    public Mensagem(Object obj){
        Mensagem copia = (Mensagem) obj;
        this.conteudo=copia.getConteudo();
        this.tipo= copia.isTipo();
        this.email = copia.getEmail();
    }

    // Getter para obter o email da mensagem
    public String getEmail() {
        return email;
    }

    // Getter para obter o conteúdo da mensagem
    public String getConteudo() {
        return conteudo;
    }

    // Getter para obter o tipo da mensagem
    public boolean isTipo() {
        return tipo;
    }

    // Método de clone para criar uma cópia da mensagem
    @Override
    public Object clone()  {
        return new Mensagem(this);
    }
}

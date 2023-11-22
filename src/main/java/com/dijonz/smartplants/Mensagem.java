package com.dijonz.smartplants;

import java.io.Serializable;

public class Mensagem implements Serializable,Cloneable{

    //false + email para lista de produtos de um único vendedor, true + TIPO para listas completas, true + email + "LOGIN" para login
    private boolean tipo;
    // true para retorno de listas completas e login
    // e false para retorno de listas de um vendedor especifico
    private String conteudo;
    //email do vendedor ou "VENDEDORES" para lista de vendedores e
    // "PRODUTOS" para lista de produtos, "LOGIN" para pedir um usuário

    private String email;

    public Mensagem(Boolean tipo,String conteudo){
        this.conteudo=conteudo;
        this.tipo= tipo;
    }

    public Mensagem(Boolean tipo,String email, String conteudo){
        this.conteudo=conteudo;
        this.tipo= tipo;
        this.email = email;
    }

    public Mensagem(Object obj){
        Mensagem copia = (Mensagem) obj;
        this.conteudo=copia.getConteudo();
        this.tipo= copia.isTipo();
        this.email = copia.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public String getConteudo() {
        return conteudo;
    }

    public boolean isTipo() {
        return tipo;
    }


    @Override
    public Object clone()  {
        return new Mensagem(this);
    }
}

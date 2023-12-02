package com.dijonz.smartplants;

import java.io.Serializable;

public class Produto implements Serializable, Cloneable {

    private final String nome;
    private final String preco;

    public String getNome() {
        return nome;
    }
    public String getPreco() {return preco;}

    public Produto(String nome, String preco){
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(Object obj){
        Produto copia = (Produto) obj;
        this.nome = copia.getNome();
        this.preco= copia.getPreco();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco='" + preco + '}';
    }

    @Override
    public Object clone() {
        return new Produto(this);
    }
}

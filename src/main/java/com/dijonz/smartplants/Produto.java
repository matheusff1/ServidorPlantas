package com.dijonz.smartplants;

import java.io.Serializable;

// Classe Produto representa informações sobre um produto serializável e clonável
public class Produto implements Serializable, Cloneable {

    private final String nome; // Nome do produto
    private final String preco; // Preço do produto

    // Getter para obter o nome do produto
    public String getNome() {
        return nome;
    }

    // Getter para obter o preço do produto
    public String getPreco() {
        return preco;
    }

    // Construtor para criar um objeto Produto com nome e preço
    public Produto(String nome, String preco){
        this.nome = nome;
        this.preco = preco;
    }

    // Construtor para clonar um objeto Produto existente
    public Produto(Object obj){
        Produto copia = (Produto) obj;
        this.nome = copia.getNome();
        this.preco= copia.getPreco();
    }

    // Método toString para representar o objeto Produto como uma string
    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco='" + preco + '}';
    }

    // Método de clone para criar uma cópia do objeto Produto
    @Override
    public Object clone() {
        return new Produto(this);
    }
}

package com.dijonz.smartplants;

import java.io.Serializable;

// Classe Vendedor representa informações sobre vendedor
public class Vendedor implements Serializable,Cloneable {

    private final String nome;      // Nome do vendedor
    private final String email;     // Email do vendedor
    private final String senha;     // Senha do vendedor
    private final String telefone;  // Número de telefone do vendedor
    private final String local;     // Localização do vendedor
    //private final String fotoUri; // URI da foto do vendedor (comentada, não utilizada)

    // Construtor para criar um objeto Vendedor com informações essenciais
    public Vendedor(String nome, String email, String senha, String telefone, String local) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.local = local;
        //this.fotoUri = fotoUri;
    }


    // Método toString para representar o objeto Vendedor como uma string
    @Override
    public String toString() {
        return "Vendedor{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", local='" + local + '\'' +
                //", fotoUri='" + fotoUri + '\''
                +
                '}';
    }

    // Construtor para clonar um objeto Vendedor existente
    public Vendedor(Object obj){
        Vendedor copia = (Vendedor) obj;
        this.nome = copia.getNome();
        this.email= copia.getEmail();
        //this.fotoUri=copia.getFotoUri();
        this.local = copia.getLocal();
        this.senha = copia.getSenha();
        this.telefone = copia.getTelefone();
    }

    // Getter para obter o email do vendedor
    public String getEmail() {
        return email;
    }

    //public String getFotoUri() {
    //    return fotoUri;
    //}

    // Getter para obter a localização do vendedor
    public String getLocal() {
        return local;
    }

    // Getter para obter o nome do vendedor
    public String getNome() {
        return nome;
    }

    // Getter para obter a senha do vendedor
    public String getSenha() {
        return senha;
    }

    // Getter para obter o número de telefone do vendedor
    public String getTelefone() {
        return telefone;
    }

    // Método de clone para criar uma cópia do objeto Vendedor
    @Override
    public Object clone() {
        return new Vendedor(this);
    }
}







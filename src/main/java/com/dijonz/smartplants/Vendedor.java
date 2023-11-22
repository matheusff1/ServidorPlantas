package com.dijonz.smartplants;



import java.io.Serializable;

public class Vendedor implements Serializable,Cloneable {

    private final String nome;
    private final String email;
    private final String senha;
    private final String telefone;
    private final String local;
    private final String fotoUri;

    public Vendedor(String nome, String email, String senha, String telefone, String local, String fotoUri) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.local = local;
        this.fotoUri = fotoUri;
    }



    @Override
    public String toString() {
        return "Vendedor{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", local='" + local + '\'' +
                ", fotoUri='" + fotoUri + '\'' +
                '}';
    }

    public Vendedor(Object obj){
        Vendedor copia = (Vendedor) obj;
        this.nome = copia.getNome();
        this.email= copia.getEmail();
        this.fotoUri=copia.getFotoUri();
        this.local = copia.getLocal();
        this.senha = copia.getSenha();
        this.telefone = copia.getTelefone();
    }

    public String getEmail() {
        return email;
    }

    public String getFotoUri() {
        return fotoUri;
    }

    public String getLocal() {
        return local;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public Object clone() {
        return new Vendedor(this);
    }
}







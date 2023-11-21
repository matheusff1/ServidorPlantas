package src;

import java.io.Serializable;

public class Mensagem implements Serializable,Cloneable{
    private boolean tipo;
    // true para retorno delistas completas
    // e false para retorno de listas de um vendedor especifico
    private String conteudo;
    //email do vendedor ou "VENDEDORES" para lista de vendedores e
    // "PRODUTOS" para lista de produtos

    public Mensagem(Boolean tipo,String conteudo){
        this.conteudo=conteudo;
        this.tipo= tipo;
    }

    public Mensagem(Object obj){
        Mensagem copia = (Mensagem) obj;
        this.conteudo=copia.getConteudo();
        this.tipo= copia.isTipo();
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

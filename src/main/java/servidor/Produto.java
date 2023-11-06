package servidor;

public class Produto {
    private String nome;

    public String getNome() {
        return nome;
    }

    public Produto(String nome){
        this.nome=nome;
    }
    public Produto(Object obj){
        Produto copia = (Produto) obj;
        this.nome  = ((Produto) obj).getNome();
    }

    @Override
    public Object clone(){
        return new Produto(this);
    }
}

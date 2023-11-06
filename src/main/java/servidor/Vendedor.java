package servidor;

public class Vendedor {
    private String nome;

    public String getNome() {
        return nome;
    }
    public Vendedor(String nome){
        this.nome=nome;
    }
    public Vendedor(Object obj){
        Vendedor copia = (Vendedor) obj;
        this.nome = copia.getNome();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Vendedor(this);
    }
}

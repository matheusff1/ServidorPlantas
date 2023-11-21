package src;

import java.io.*;
import java.util.Objects;


public class Servidor {

    private static final int PORTA = 7977;
    public static void main(String[] args) throws Exception {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String texto=null;
        int porta = Servidor.PORTA;
        IniciaConexao PABLO = new IniciaConexao(porta);
        PABLO.start();
        System.out.println("O servidor ligado, digite sair para deligá-lo.");
        do {
            try {
                texto =teclado.readLine();
                if(!texto.equalsIgnoreCase("sair")){
                    System.err.println("Comando inválido, digite sair para desligar o servidor. ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while(!Objects.requireNonNull(texto).equalsIgnoreCase("sair"));
        System.out.println("Servidor desligado.");
        PABLO.morre();
        System.exit(0);
    }
}

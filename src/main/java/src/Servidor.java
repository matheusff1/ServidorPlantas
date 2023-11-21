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

        do {
            try {
                System.out.println("O servidor ligado, digite sair para deligá-lo.");
                texto =teclado.readLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while(!Objects.requireNonNull(texto).equalsIgnoreCase("sair"));
        PABLO.morre();

    }
}

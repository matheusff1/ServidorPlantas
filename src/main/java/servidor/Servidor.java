package servidor;

import java.io.*;
import java.net.*;
import java.util.Locale;


public class Servidor {

    private static final int PORTA = 7577;
    public static void main(String[] args) throws Exception {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int porta = Servidor.PORTA;
        IniciaConexao PABLO = new IniciaConexao(porta);
        PABLO.start();
        while (true) {
            try {

                System.out.println("O servidor logado, digite sair para deligá-lo.");
                String texto =teclado.readLine();
                if(texto.equalsIgnoreCase("sair")){
                    PABLO.interrupt();
                    break;
                }



            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

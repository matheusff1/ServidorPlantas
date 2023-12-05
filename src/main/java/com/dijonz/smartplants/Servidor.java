package com.dijonz.smartplants;

import java.io.*;
import java.util.Objects;

// Classe principal que inicia o servidor e aguarda um comando para desligá-lo
public class Servidor {

    private static final int PORTA = 7977;
    // Método principal do servidor
    public static void main(String[] args) throws Exception {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String texto=null;
        int porta = Servidor.PORTA;

        // Inicia a thread para aceitar conexões de clientes na porta especificada
        IniciaConexao PABLO = new IniciaConexao(porta);
        PABLO.start();
        // Mensagem informando que o servidor está ligado e aguardando comando para desligar
        System.out.println("O servidor ligado, digite sair para deligá-lo.");

        // Loop para aguardar o comando para desligar o servidor
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

        // Mensagem indicando que o servidor está sendo desligado
        System.out.println("Servidor desligado.");

        // Chama o método para interromper a execução da thread de conexão
        PABLO.morre();

        // Encerra o programa
        System.exit(0);
    }
}

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


        IniciaConexao PABLO = new IniciaConexao(porta); //Cria uma instância da classe `IniciaConexao` passando
        // a porta como parâmetro e atribui a variável `PABLO`.
        PABLO.start(); // Inicia a thread para aceitar conexões de clientes na porta especificada

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

        System.out.println("Servidor desligado.");

        // Chama o método para interromper a execução da thread de conexão
        PABLO.morre();

        // Encerra o programa
        System.exit(0);
    }
}

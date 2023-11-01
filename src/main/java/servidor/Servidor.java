package servidor;

import java.io.*;
import java.net.*;
import java.util.Locale;


public class Servidor {
    public static void main(String[] args)  {
        try {
            // ip do host: "192.168.0.101"/ usar outputStream-> enviar, inputStream->receber. função flush->envia.
            /*
            System.out.println("Servidor ativado. Digite sair para desativar.");
            ServerSocket porta = new ServerSocket(7777);
            Socket conexao = porta.accept();

            ObjectInputStream recebeObj = new ObjectInputStream(conexao.getInputStream());

            BufferedReader receptor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String texot = "";
            Object obj =null;
            */

            /*
            try{
                obj = recebeObj.readObject();
                System.out.println(obj);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            while(texot.toLowerCase().equals("fim")){
                texot = receptor.readLine();
                System.out.println(texot);
                if(texot.toLowerCase()=="fim"){
                    break;
                }
            }
            conexao.close();
            receptor.close();
            */



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

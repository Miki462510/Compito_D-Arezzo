package com.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server;
    Socket servSocket;
    BufferedReader in;
    DataOutputStream out;
    public Socket attendi() {
        try {
            //creo il server sulla porta ****
            System.out.println("Server in esecuzione..");
            server = new ServerSocket(6789);

            //accetto eventuale connessione da parte del client
            servSocket = server.accept();
            System.out.println("Client connesso con successo! ");

            //chiudo la connessione per evitare altre connessioni
            server.close();

            //inizializzo gli stream per consentire la comunicazione
            out = new DataOutputStream(servSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(servSocket.getInputStream()));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return servSocket;
    }


    public void calcola(){
        try{
            double x;
            double y;
            double risultato=0;
            //leggo la scelta e in base a quella eseguo le operazioni
            String scelta = in.readLine();

            //accetto prima i numeri
            x = in.read();

            System.out.println("x "+x);
            y = in.read();
            System.out.println("y "+y);

            switch (scelta){
                case "+":
                    System.out.println("Scelta: "+scelta);
                    //somma
                    risultato = x+y;
                case "-":
                    System.out.println("Scelta: "+scelta);
                    //sottrazione
                    risultato = x-y;
                case "*":
                    System.out.println("Scelta: "+scelta);
                    //moltiplicazione
                    risultato = x*y;
                case "/":
                    System.out.println("Scelta: "+scelta);
                    //divisione
                    risultato = x/y;

            }
            System.out.println("Risultato: "+risultato);

        }catch (Exception e){

        }
    }
}

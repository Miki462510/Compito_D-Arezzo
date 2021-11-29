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
    boolean alive = true;

    public Socket attendi() {
        try {
            //creo il server sulla porta ****
            System.out.println("Server in esecuzione..");
            server = new ServerSocket(6789);

            //accetto eventuale connessione da parte del client
            servSocket = server.accept();
            System.out.println("Client connesso con successo! ");

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
            String x;
            String y;
            String risultato;
            //leggo la scelta e in base a quella eseguo le operazioni
            String scelta = in.readLine();
            for(;;){
            //accetto prima i numeri

            x = in.readLine();

            System.out.println("x "+x);
            y = in.readLine();
            System.out.println("y "+y);
            risultato = in.readLine();
            double xop = Double.parseDouble(x);
            double yop = Double.parseDouble(y);
            double rop = Double.parseDouble(risultato);

            switch (scelta){
                case "+":
                    System.out.println("Scelta: "+scelta);
                    //somma
                    rop = xop+yop;
                    String rfp=Double.toString(rop); 
                    out.writeBytes(rfp);
                case "-":
                    System.out.println("Scelta: "+scelta);
                    //sottrazione
                    rop = xop-yop;
                    String rfs=Double.toString(rop); 
                    out.writeBytes(rfs);
                case "*":
                    System.out.println("Scelta: "+scelta);
                    //moltiplicazione
                    rop = xop*yop;
                    String rfm=Double.toString(rop); 
                    out.writeBytes(rfm);
                case "/":
                    System.out.println("Scelta: "+scelta);
                    //divisione
                    rop = xop/yop;
                    String rfd=Double.toString(rop); 
                    out.writeBytes(rfd);
                case "x":
                disconnessione();
                break;

            };
            
        }

        }catch (Exception e){

        }
    }
    public void disconnessione(){
        try{
            alive=false;
            servSocket.close();
            server.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

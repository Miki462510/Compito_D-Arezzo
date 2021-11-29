package com.example;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    String nomeServer = "localhost"; //il nome
    int portaServer= 6789; //la porta
    Socket socket;
    BufferedReader in; //stream input
    DataOutputStream out; //stream output

    
    Scanner input = new Scanner(System.in);

    public Socket connetti(){
        try {
            System.out.println("Client avviato..");
            //creo il socket per connettermi al server
            socket = new Socket(nomeServer, portaServer);
            System.out.println("Client connesso con successo al server ' "+nomeServer+" ' sulla porta: "+portaServer);
            //inizializzo gli stream per consentire la comunicazione
            out = new DataOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e){
            System.out.println("Errore, impossibile connettersi");
            System.exit(1);
        }
        return socket;

    }

    //menu per scelta operazioni
    public void menu(){
        try{
            System.out.println("---Calcolatrice---");
            System.out.println("+ \t -\t * \t /");
            System.out.println("x.ESCI");
        } catch (Exception e){
            System.out.println("Errore");
        }


    }

    //funzione per comunicare
    public void comunica(){
        try {
            String scelta = ".";
            do{
                menu();
                System.out.println("Inserire operazione ");
                scelta = input.nextLine();
                //invio la scelta al server
                out.writeBytes(scelta);;

                //Inserisco i numeri e li spedisco al server
                System.out.println("Inserisci primo numero.");
                double x = input.nextDouble();
                out.writeDouble(x);

                System.out.println("Inserisci secondo numero.");
                double y = input.nextDouble();
                out.writeDouble(y);

            }
            while(scelta != "x");
        }catch (IOException e){

        }


    }
}

package com.example;
import java.io.*;
import java.net.*;

public class Client{
   
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public void comunica(){
        for(;;){
            try{

                System.out.println("4 ... utente, inserisci la stringa da trasmettere al server:");
                stringaUtente = tastiera.readLine();

                System.out.println("5 ... invio la stringa al server e attendo ...");
                outVersoServer.writeBytes( stringaUtente + '\n');
                stringaRicevutaDalServer =inDalServer.readLine();
                System.out.println("7 ... risposta dal server "+'\n' + stringaRicevutaDalServer);
                if(stringaUtente.equals("FINE")){
                    System.out.println("8 CLIENT: termina elaborazione e chiude connessione");
                    miosocket.close();
                    break;
                }

            }catch (Exception e){

                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione col server!");
                System.exit(1);
            }
        }
    }

    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione ...");
        try{

            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer,portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));

        }catch (UnknownHostException e){
            System.err.println("Host sconosciuto");
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return miosocket;
    }
}
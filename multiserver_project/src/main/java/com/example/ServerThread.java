package com.example;
import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
   
    MultiServer multiServer = null;
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread (Socket socket, ServerSocket serverS){

        this.client = socket;
        this.server = serverS;
    }

    public ServerThread (Socket socket, MultiServer ms){

        this.client = socket;
        this.multiServer = ms;
    }




    public void run(){
        try{
            comunica();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception{

        inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        for(;;){
            stringaRicevuta = inDalClient.readLine();
            if(stringaRicevuta == null || stringaRicevuta.equals("FINE")){
                outVersoClient.writeBytes(stringaRicevuta+"(=>server in chiusura...)" + "\n");
                System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
                break;
            } else if(stringaRicevuta.equals("STOP") || stringaRicevuta.equalsIgnoreCase("FINE")){
                
                client.close();
                multiServer.stop();
                break;
            } 
            else{
                stringaModificata=stringaRicevuta.toUpperCase();
                outVersoClient.writeBytes(stringaModificata + "(ricevuta e trasmessa" + '\n');
                System.out.println("6 Echo sul server :" + stringaRicevuta);
            }
            
        }



        
    }

    
    public void close(){

        try{
        outVersoClient.close();
        inDalClient.close();
        System.out.println("9 Chiusura socket" + client);
        client.close();
        } catch(IOException e){

            e.printStackTrace();
        }
    
    }

    

   }

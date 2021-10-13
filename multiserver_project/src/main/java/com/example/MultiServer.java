package com.example;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class MultiServer {

    List<ServerThread>ThreadList= new ArrayList<>();
   ServerSocket serverSocket = null;

    public void start(){
        try{
            serverSocket = new ServerSocket(6789);
            for(;;){
                System.out.println("1 Server in attesa ...");
                Socket socket = serverSocket.accept();
                System.out.println("3 server socket " + socket);
                ServerThread serverThread = new ServerThread(socket, serverSocket);
                ServerThread thread = new ServerThread(socket, this);
                ThreadList.add(thread);
                thread.start();
                serverThread.start();
            }
         } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante l'istanza del server !");
                System.exit(1);
            }
        }


    public void stop(){

        for(int i = 0;i<ThreadList.size();i++){

            ThreadList.get(i).close();

        }

        try{

            serverSocket.close();

        } catch(IOException e){

            e.printStackTrace();
        }

    }


    }




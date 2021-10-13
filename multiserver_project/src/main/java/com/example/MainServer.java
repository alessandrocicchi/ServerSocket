package com.example;

/**
 * Hello world!
 *
 */
public class MainServer 
{
    public static void main( String[] args )
    {
        
        MultiServer tcpServer = new MultiServer();
        tcpServer.start();
    }
}

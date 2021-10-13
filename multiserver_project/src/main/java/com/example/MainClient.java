package com.example;

/**
 * Hello world!
 *
 */
public class MainClient
{
    public static void main( String[] args )
    {
        
        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();
    }
}

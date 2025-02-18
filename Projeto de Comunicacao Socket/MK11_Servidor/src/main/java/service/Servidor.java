/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author matheus.sampaio
 */
public class Servidor {
    
    private final ServerSocket servidorSocket;
    
    public Servidor (ServerSocket servidorSocket){
        this.servidorSocket = servidorSocket;
        System.out.println("Servidor ON");
    }
    
    public void iniciarServidor(){
        try {
            while(!servidorSocket.isClosed()){
                Socket socket = servidorSocket.accept();
                ClienteHandler cliente = new ClienteHandler(socket);
                Thread thread = new Thread(cliente);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar Servidor :" + e);
        }
    }
    
    public void fecharServidor(){
        try {
            if(servidorSocket != null){
                servidorSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao finalizar o servidor: " + e);
        }
    }
    
     
    public static void main(String[] args) throws IOException {
        int port = 20010;
        ServerSocket server = new ServerSocket(port);
        Servidor servidor = new Servidor(server);
        servidor.iniciarServidor();
    }
}

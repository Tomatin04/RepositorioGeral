/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.Informacoes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;



/**
 *
 * @author matheus.sampaio
 */
public class Cliente {
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private static Informacoes informacoes;
    private final Gson gson;
    private String json;
    
    public Cliente(String endereco, int porta) throws IOException{
        this.socket = new Socket(endereco, porta);
        reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        gson = new GsonBuilder() 
            .excludeFieldsWithoutExposeAnnotation()
            .create();
        informacoes = new Informacoes();
    }
    
    public void escrever(Informacoes informacoes){
        json = gson.toJson(informacoes);
        try {
            System.out.println(" --- Envinado para o servidor: " + json);
            writer.write(json);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Informacoes ler(){
        String msg;
        try {
            msg = reader.readLine();
            informacoes = gson.fromJson(msg, Informacoes.class);
            System.out.println("  --- Informacao recebida do servidor" + informacoes.toString());
            testeJson(informacoes); 
            return informacoes;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Informacoes escreverLer(Informacoes informacoes){
        escrever(informacoes);
        return ler();
    }
    
    
    
    
    
    //
    // Teste de conexão com o servidor 
    // -- Utilizado para testar o envio e recebimento das informações sem uma GUI
    //
    /*-------------------------------------------------------------------------------------------------*/
    public void sendMessagem(){
        try {
            
            String json = gson.toJson(informacoes);
            System.out.println("Informações Cliente: " + json);
            writer.write(json);
            writer.newLine();
            writer.flush();
            
           
            
            /*while(socket.isConnected()){
                
                json = gson.toJson(informacoes);
                writer.write(json);
                writer.newLine();
                writer.flush();
            }*/
        } catch (IOException e) {
             System.out.println("A");
        }
    }
    
    public void listenForMenssage(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                String msgFromGroupChat;
                
                while(socket.isConnected()){
                    try{
                        
                        msgFromGroupChat = reader.readLine();
                        informacoes = gson.fromJson(msgFromGroupChat, Informacoes.class);
                        System.out.println(informacoes.toString());
                        
                        /*
                        Object o = informacoes.getCategorias();
                        List<Categoria> cList = gson.fromJson(gson.toJson(o),  new TypeToken<List<Categoria>>(){}.getType());
                        //
                        Object o =  a.getCategoria();
                        System.out.println(o.toString());
                        Categoria c = gson.fromJson(gson.toJson(o), Categoria.class);
                        Uma solução para a deserialização da classe é reserializala e depois transformala em um objeto Categoria
                        */
                    }catch(IOException e){
                        
                    }
                }
            }
        }).start();
    }
    
    public void testeJson(Informacoes informacoes){
        String json = gson.toJson(informacoes);
        System.out.println("Cliente JSON: " + json);
    }
    
    public void escreverCampos(Informacoes informacoes){
        this.informacoes = informacoes;
    }
    /*----------------------------------------------------------------------------------------------------------*/
}

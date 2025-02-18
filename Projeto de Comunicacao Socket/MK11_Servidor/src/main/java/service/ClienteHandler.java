/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.google.gson.*;
import entities.Informacoes;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author matheus.sampaio
 */


public class ClienteHandler implements Runnable{
    private static final ArrayList<ClienteHandler> clientes = new ArrayList<>();
    private BufferedWriter writer;
    private BufferedReader reader;
    private Socket socket;
    private Gson gson;
    private AcoesTratamento acoes;
    private String json;
    private Informacoes informacoes;

    //--Informações de conexão do usuário--//
    private String raConectado;
    
     public ClienteHandler(Socket socket){
         try {
            this.raConectado = "";
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clientes.add(this);
            gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            acoes = new AcoesTratamento();
            informacoes = new Informacoes();
        } catch (IOException e) {
            System.out.println("Erro ao criar ClienteHandler " + e.getMessage());
        }
     }

    @Override
    public void run() {
        String msg;        
        System.out.println("Cliente on " + socket);
        try {
            while(!socket.isClosed()){
                msg = reader.readLine();
                informacoes = gson.fromJson(msg, Informacoes.class);
                System.out.println(" --- Recebido do cliente: "+ informacoes.toString());
                interpretarInformacoes();
                escrever();
            }
            closeEverything(socket, reader, writer);
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
            System.out.println("Erro ao ler menssagem " + e);
        }
    }

    public void escrever(){
        json = gson.toJson(informacoes);
        System.out.println(" --- Servidor saida JSON" + json);
        try {
            writer.write(json); 
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("ERRO CLIENENTE " + socket + " = Escrita");
            closeEverything(socket, reader, writer);
        } 
    }
    
    private void interpretarInformacoes(){
        switch(informacoes.getOperacao()){
            case "login":
                
                if(validationLogon()) break;
                raConectado = informacoes.getRa();
                acoes.login(informacoes);
                if(informacoes.getStatus() == 401) raConectado = "";
                userListOn();
                break;
            case "logout":
                acoes.logout(informacoes);
                if(informacoes.getStatus() == 200) raConectado = "";
                userListOn();
                break;
            case "cadastrarUsuario":
                acoes.cadastrarUsuario(informacoes);
                break;
            case "listarUsuarios":
                acoes.listarUsuarios(informacoes);
                break;
            case "localizarUsuario":
                acoes.localizarUsuario(informacoes);
                break;
            case "excluirUsuario":
                acoes.excluirUsuario(informacoes);
                break;
            case "editarUsuario":
                acoes.editarUsuario(informacoes);
                break;
            case "salvarCategoria":
                acoes.salvarCategoria(informacoes);
                break;  
            case "listarCategorias":
                acoes.listarCategorias(informacoes);
                break;
            case "localizarCategoria":
                acoes.localizarCategoria(informacoes);
                break;
            case "excluirCategoria":
                acoes.excluirCategoria(informacoes);
                break;
            case "salvarAviso":
                acoes.salvarAviso(informacoes);
                break;
            case "listarAvisos":
                acoes.listarAvisos(informacoes);
                break;
            case "localizarAviso":
                acoes.localizarAviso(informacoes);
                break;
            case "excluirAviso":
                acoes.excluirAviso(informacoes);
                break;
            case "cadastrarUsuarioCategoria":   
                acoes.cadastrarUsuarioCategoria(informacoes);
                break;
            case "listarUsuarioCategorias":
                acoes.listarUsuarioCategorias(informacoes);
                break;
            case "descadastrarUsuarioCategoria":
                acoes.descadastrarUsuarioCategoria(informacoes);
                break;
            case "listarUsuarioAvisos":
                acoes.listarUsuarioAvisos(informacoes);
                break;
            //Case especiais para ações de teste
            case "teste":
                System.out.println("Teste");
                System.out.println(informacoes.toString());
                break;
            case "socketsOn":
                for(ClienteHandler cliente : clientes){
                    System.out.println(cliente.socket);
                }
                break;
            case "userListOn":
                if(informacoes.getToken().equals("2509849"))userListOn();
                break;
            default:
                informacoes.limparInformacoes();
                informacoes.setStatus(401);
                informacoes.setMensagem("Não foi possível possível processar a requisição");
                System.out.println("++Operação não encontrada++");
           }
    }
    
    
    public void removeClietHandler(){
        clientes.remove(this);
        System.out.println("Usuario logoff");
    }
  
    
   public void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer){
        removeClietHandler();
        try{
            if(reader != null){
                reader.close();
            }
            if(writer != null){
                writer.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch(IOException e){
               closeEverything(socket, reader,writer);
               informacoes.setStatus(401);
               informacoes.setMensagem("Impossivel fazer logout");
               informacoes.setOperacao("logout");
            }
    }

    public boolean validationLogon(){
        for(ClienteHandler cliente : clientes){
            if(informacoes.getRa().equals(cliente.getRaConectado())){
                informacoes.limparInformacoes();
                informacoes.setStatus(401);
                informacoes.setMensagem("Usuário já logado no sistema");
                return true;
            }
        }
        return false;
    }

    public void userListOn(){
        for(ClienteHandler cliente : clientes){
            System.out.println("\n-- Conexões e RAs dos usuários conectador --\n== " +cliente.toString() + " com o RA: " + raConectado + "\n -- Os usários que estão sem RA estão conecttados mas não realziaram login -- \n");
        }
    }

    private String getRaConectado(){
        return raConectado;
    }
    
}

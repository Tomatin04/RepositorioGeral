/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.IOException;

import entities.Aviso;
import entities.Categoria;
import entities.Informacoes;
import entities.Usuario;

/**
 *
 * @author matheus.sampaio
 */
public class Controlador {

    /*
     * Cliente serve para fazer a comunicação, envio e recebimento do servidor
     * AcoesTratamento serve para adpatar a mensagem que sera enviada ao servidor
     * Controlador View serve para fazer a interação do usuário, deve conter apenas logica de interação
     * Controlado contem toda a logica de adaptação da mensage, ou seja, server para criar a informação que será enviada para o servidor
     */

     private Cliente cliente;
     private Informacoes informacoes;
     private AcoesTratamento acoes;
     private String msg;
    
     
     
     public Controlador(String endereco, int porta) throws IOException{
        cliente = new Cliente(endereco, porta);
        informacoes = new Informacoes();
        acoes = new AcoesTratamento();
     }

     public String getMsg(){
        return msg;
     }
     /*
      * Funções Recebidas da inerface
      */

    public Informacoes login(String ra, String senha){
        informacoes.limparInformacoes();
        informacoes = acoes.login(ra, senha);
        informacoes = cliente.escreverLer(informacoes);
        if(informacoes.getStatus() == 200){
            acoes.setToken(informacoes.getToken()); 
            System.out.println("Logado com sucesso");
            return informacoes;
            
        }
        msg = informacoes.getMensagem();
        return informacoes;
    }

    public Informacoes logout() throws InterruptedException{
        informacoes.limparInformacoesExToken();
        informacoes = acoes.logout();
        informacoes = cliente.escreverLer(informacoes);
        new Thread().sleep(100);
        if(informacoes.getStatus() == 200){
            System.out.println("Deslogado com sucesso");
            return informacoes;
        }
        return informacoes;
    }

    public Informacoes cadastrarUsuario(String ra, String nome, String senha){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.cadastro(ra, nome, senha);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes listarUsuarios(){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.listarUsuarios();
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes localizarUsuario(String ra){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.localizarUsuario(ra);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes excluirUsuario(String ra){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.excluirUsuario(ra);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes editarUsuario(String ra, String nome, String senha){
        informacoes.limparInformacoesExToken();
        Usuario usuario = new Usuario(nome, senha, ra);
        informacoes = acoes.editarUsuario(usuario);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes salvarCategoria(int id, String nome){
        informacoes.limparInformacoesExToken();
        Categoria   categoria = new Categoria(id, nome);
        informacoes = acoes.salvarCategoria(categoria);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes listarCategorias(){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.listarCategorias();
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes localizarCategoria(int id){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.localizarCategoria(id);
        informacoes = cliente.escreverLer(informacoes);
        
        return informacoes;
    }

    public Informacoes excluirCategoria(int id){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.excluirCategoria(id);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes salvarAviso(int id, String titulo, int idCategoria, String descricao){
        informacoes.limparInformacoesExToken();
        Aviso aviso = new Aviso(titulo, idCategoria, descricao, id);
        informacoes = acoes.salvarAviso(aviso);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes listarAvisos(int categoria){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.listarAvisos(categoria);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes localizarAviso(int id){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.localizarAviso(id);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes excluirAviso(int id){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.excluirAviso(id);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes cadastrarUsuarioCategoria(String ra, int categoria){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.cadastrarUsuarioCategoria(ra, categoria);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes listarUsuarioCategorias(String ra){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.listarUsuarioCategorias(ra);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes descadastrarUsuarioCategoria(String ra, int categoria){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.descadastrarUsuarioCategoria(ra, categoria);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes listarUsuarioAvisos(String ra){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.listarUsuarioAvisos(ra);
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }

    public Informacoes userListOn(){
        informacoes.limparInformacoesExToken();
        informacoes = acoes.userListOn();
        informacoes = cliente.escreverLer(informacoes);
        return informacoes;
    }
    



}

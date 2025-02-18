/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.Aviso;
import entities.Categoria;
import entities.Informacoes;
import entities.Usuario;



/**
 *
 * @author matheus.sampaio
 */
public class AcoesTratamento {
    private Informacoes informacoes;
    
    public AcoesTratamento(){
        informacoes = new Informacoes();
    }

    //                                          //
    // ---------- Funções de suporte ---------- //
    //                                          //

    public void setToken(String token){
        informacoes.setToken(token);
    }

    public String getToken(){
        return informacoes.getToken();
    }

    //                                          //
    // ---------- Funções de Usuário ---------- //
    //                                          //
    
    public Informacoes login(String ra, String senha){
        informacoes.limparInformacoesExToken();
        informacoes.setRa(ra);
        informacoes.setSenha(senha);
        informacoes.setOperacao("login");
        informacoes.setToken(null);
        return informacoes;
    }

    public Informacoes logout(){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("logout");
        return informacoes;
    }

    public Informacoes cadastro(String ra, String nome, String senha){
        informacoes.limparInformacoesExToken();
        informacoes.setRa(ra);
        informacoes.setNome(nome);
        informacoes.setSenha(senha);
        informacoes.setOperacao("cadastrarUsuario");
        return informacoes;
    }

    public Informacoes listarUsuarios(){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("listarUsuarios");
        return informacoes;
    }

    public Informacoes localizarUsuario(String ra){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("localizarUsuario");
        informacoes.setRa(ra);
        return informacoes;
    }

    public Informacoes excluirUsuario(String ra){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("excluirUsuario");
        informacoes.setRa(ra);
        return informacoes;
    }

    public Informacoes editarUsuario(Usuario Usuario){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("editarUsuario");
        informacoes.setUsuario(Usuario);
        return informacoes;
    }

    //                                          //
    // ---------- Funções de Categoria ---------- //
    //                                          //

    //Configurar a operação salvarCategoria para ser uma função de criação e ediação na interface
    public Informacoes salvarCategoria(Categoria categoria){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("salvarCategoria");
        informacoes.setCategoria(categoria);
        return informacoes;
    }

    public Informacoes listarCategorias(){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("listarCategorias");
        return informacoes;
    }

    public Informacoes localizarCategoria(int id){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("localizarCategoria");
        informacoes.setId(id);
        return informacoes;
    }

    public Informacoes excluirCategoria(int id){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("excluirCategoria");
        informacoes.setId(id);
        return informacoes;
    }

    //                                          //
    // ---------- Funções de Aviso ---------- //
    //                                          //

    //Configurar a operação salvarAviso para ser uma função de criação e ediação na interface
    public Informacoes salvarAviso(Aviso aviso){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("salvarAviso");
        informacoes.setAviso(aviso);      
        return informacoes;
    }

    public Informacoes listarAvisos(int categoria){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("listarAvisos");
        informacoes.setCategoria(categoria);
        return informacoes;
    }

    public Informacoes localizarAviso(int id){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("localizarAviso");
        informacoes.setId(id);
        return informacoes;
    }

    public Informacoes excluirAviso(int id){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("excluirAviso");
        informacoes.setId(id);
        return informacoes;
    }

    //                                         //
    // ---------- Funções de Atribuição e listagem ---------- //
    //                                          //

    public Informacoes cadastrarUsuarioCategoria(String ra, int categoria){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("cadastrarUsuarioCategoria");
        informacoes.setRa(ra);
        informacoes.setCategoria(categoria);
        return informacoes;
    }

    public Informacoes listarUsuarioCategorias(String ra){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("listarUsuarioCategorias");
        informacoes.setRa(ra);
        return informacoes;
    }

    public Informacoes  descadastrarUsuarioCategoria(String ra, Integer categoria){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("descadastrarUsuarioCategoria");
        informacoes.setRa(ra);
        informacoes.setCategoria(categoria);
        return informacoes;
    }

    public Informacoes listarUsuarioAvisos(String ra){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("listarUsuarioAvisos");
        informacoes.setRa(ra);
        return informacoes;
    }
  
    //-----------------------------------//
    //              Teste
    //-----------------------------------//
    
    public Informacoes userListOn (){
        informacoes.limparInformacoesExToken();
        informacoes.setOperacao("userListOn");
        return informacoes;
    }
}

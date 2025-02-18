/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import com.google.gson.Gson;
import java.util.List;

import com.google.gson.annotations.Expose;


/**
 *
 * @author matheus.sampaio
 */

public class Informacoes {
    @Expose
    private String operacao;
    @Expose
    private String nome;
    @Expose
    private String ra;
    @Expose
    private String senha;
    @Expose
    private Integer status;
    @Expose
    private String mensagem;
    @Expose
    private String token;
    @Expose
    private Usuario usuario;
    @Expose
    private Object categoria; //Transformar em string e tratar dentro do servidor
    @Expose
    private Aviso aviso;
    @Expose
    private List<Usuario> usuarios;
    @Expose
    private Object categorias; // Transformar em string e tratar no servidor
    @Expose
    private List<Aviso> avisos;
    @Expose
    private Integer id;

    
    /*public Informacoes(String operacao,String nome, String ra, String senha, int status, String mensagem, String token,String tipoAviso){
        this.operacao = operacao;
        this.nome = nome;
        this.ra = ra;
        this.senha = senha;
        this.status = status;
        this.mensagem = mensagem;
        this.token = token;
    }*/
    
    public Informacoes(){
        this.operacao = null;
        this.nome = null;
        this.ra = null;
        this.senha = null;
        this.status = null;
        this.mensagem = null;
        this.token = null;
        this.usuario = null;
        this.categoria = null; 
        this.aviso = null;
        this.usuarios = null;
        this.categorias = null;        
        this.avisos = null;
        this.id = null;
    }
    
    
    public int getId(){
        return id;
    }

    public String getOperacao(){
        return operacao;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getRa(){
        return ra;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public int getStatus(){
        return status;
    }
    
    public String getMensagem(){
        return mensagem;
    }
    
    public String getToken(){
        return token;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public Object getCategoria(){
        return categoria;
    }

    public Aviso getAviso(){
        return aviso;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public List<Usuario> getUsuarios(){
        return usuarios;
    }

    public Object getCategorias(){
        return categorias;
    }

    public List<Aviso> getAvisos(){
        return avisos;
    }


    public void setOperacao(String operacao){
        this.operacao = operacao;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setRa(String ra){
        this.ra = ra;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    
    public void setToken(String token){
        this.token = token;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public void setCategoria(Object categoria){
        this.categoria = categoria;
    }

    public void setAviso(Aviso aviso){
        this.aviso = aviso;
    }   

    public void setUsuarios(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    public void setCategorias(Object categorias){
        this.categorias = categorias;
    }

    public void setAvisos(List<Aviso> avisos){
        this.avisos = avisos;
    }  


    
    public void limparInformacoes(){
        this.operacao = null;
        this.nome = null;
        this.ra = null;
        this.senha = null;
        this.status = null;
        this.mensagem = null;
        this.token = null;
        this.usuario = null;
        this.categoria = null;
        this.aviso = null; 
        this.usuarios = null;
        this.categorias = null;
        this.avisos = null;
        this.id = null; 
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
        /*StringBuilder sb = new StringBuilder();
        sb.append("Informacoes {");
        sb.append("Operação =").append(operacao).append(", ");
        sb.append("Ra =").append(ra).append(", ");
        sb.append("Nome =").append(nome).append(", ");
        sb.append("Senha =").append(senha).append(", ");
        sb.append("Status =").append(status).append(", ");
        sb.append("Mensagem =").append(mensagem).append(", ");
        sb.append("Token =").append(token).append(", ");
        sb.append("Id =").append(id).append(", ");


        // Adicione todos os atributos da classe Informacoes aqui
        sb.append("categoria=").append(categoria != null ? categoria.toString() : "null");
        sb.append(", aviso=").append(aviso != null ? aviso.toString() : "null");
        sb.append(", usuario=").append(usuario != null ? usuario.toString() : "null");
        sb.append(", usuarios=").append(usuarios != null ? usuarios.toString() : "null");
        sb.append(", categorias=").append(categorias != null ? categorias.toString() : "null");
        sb.append(", avisos=").append(avisos != null ? avisos.toString() : "null");


        
        // Adicione outros atributos de classes associadas aqui
        sb.append("}");
        return sb.toString();*/
    }
}

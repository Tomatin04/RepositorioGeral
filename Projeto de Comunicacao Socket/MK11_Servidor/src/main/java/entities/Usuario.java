/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;


/**
 *
 * @author mathe
 */
public class Usuario {
    
    private int id;
    @Expose
    private String nome;
    @Expose
    private String senha;
    @Expose
    private String ra;
    
    public Usuario( String nome, String senha, String ra){
        this.nome = nome;
        this.senha = senha;
        this.ra = ra;
        this.id = 0;
    }
    
    public Usuario(){
        this.id = 0;
        this.nome = " ";
        this.senha = " ";
        this.ra = " ";
    }

    //---- Getters
    
    public int getId(){
        return id;
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

    
    //---- Setters

    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setRa(String ra){
        this.ra = ra;
    }
    
    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}


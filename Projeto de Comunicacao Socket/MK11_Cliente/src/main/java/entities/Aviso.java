/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;



import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.google.gson.annotations.Expose;

/**
 *
 * @author mathe
 */
public class Aviso {
    
    @Expose
    private String titulo;
    @Expose
    private Object categoria;
    @Expose
    private String descricao;
    @Expose
    private int id;
    
    
    public Aviso(){
        titulo = null;
        categoria = null;
        descricao = null;
        id = 0;
    }
    
    public Aviso (String titulo,Object categoria,String descricao, int id){
        this.titulo = titulo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.id = id;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public Object getCategoria(){
        return categoria;
    }
    
    public int getId(){
        return id;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public void setCategoria(Object categoria){
        this.categoria = categoria;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}

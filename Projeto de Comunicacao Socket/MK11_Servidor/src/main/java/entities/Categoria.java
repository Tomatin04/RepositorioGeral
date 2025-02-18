/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import java.util.List;


/**
 *
 * @author matheus.sampaio
 */
public class Categoria {
    
    //Criar um campo para deixar os usuários cadastrados Um Array
    

    @Expose
    private Integer id;
    @Expose
    private String nome;
    
    public Categoria(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Categoria(){
        this.id = 0;
        this.nome = "";
    }
    
    public String getNome(){
        return nome;
    }
    
    public Integer getId(){
        return id;
    }


    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setId(Integer id){
       this.id = id;
    }
    
    public Categoria getCategoria(Object categoria){
        Categoria c = new Gson().fromJson(new Gson().toJson(categoria), Categoria.class);
        this.nome = c.getNome();
        this.id = c.getId();
        return c;
    }
    
    public List<Categoria> getCategorias(Object categorias){
        return new Gson().fromJson(new Gson().toJson(categorias),  new TypeToken<List<Categoria>>(){}.getType());
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus.sampaio
 */
public class Vinculo {
    
    private String ra;
    private List<Integer> categorias;
    
    public Vinculo(String ra, List<Integer> categorias){
        this.ra = ra;
        this.categorias = categorias;
    }

    public Vinculo(String ra){
        this.ra = ra;
        this.categorias = new ArrayList<>();
    }
    
    public Vinculo(){
        this.ra = " ";
        this.categorias = null;
    }

    public String getRa() {
        return ra;
    }

    public List<Integer> getCategorias() {
        return categorias;
    }   

    public void setRa(String ra) {
        this.ra = ra;
    }   

    public void setCategorias(List<Integer> categorias) {
        this.categorias = categorias;
    }
}

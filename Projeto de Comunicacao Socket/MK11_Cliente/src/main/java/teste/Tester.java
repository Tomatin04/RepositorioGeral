/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import entities.Aviso;
import entities.Categoria;
import entities.Informacoes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Cliente;

/**
 *
 * @author mathe
 */
public class Tester {
    public static void main(String[] args) throws IOException {
        Informacoes info = new Informacoes();
      
        Aviso a = new Aviso("Teste de entrada 2", 2, "Teste de entrada pelo cliente 2", 0);
        Categoria c = new Categoria(0 ,"Teste");
        Cliente cliente = new Cliente ("localhost", 20010);
        info.setToken("2509849");
        
        
        info.setOperacao("socketsOn");
        info.setAviso(null);
        info.setCategoria(null);
        
        
        cliente.escreverCampos(info);
        cliente.sendMessagem();
        cliente.listenForMenssage();
        
    }
}

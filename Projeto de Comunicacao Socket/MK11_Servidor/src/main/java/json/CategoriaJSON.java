/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Categoria;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus.sampaio
 */
public class CategoriaJSON {
    
    //Criar uma forma de cadastrar os usuários nas categorias
    
    private static final String CAMINHO_ARQUIVO = "categorias.json";
    private final Gson gson;
    private final VinculoJSON seg;
    
    public CategoriaJSON(){
        seg = new VinculoJSON();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    
    private  List<Categoria> readCategorias(){
        try(Reader reader = new FileReader(CAMINHO_ARQUIVO)){
            return gson.fromJson(reader, new TypeToken<List<Categoria>>() {}.getType());
        }catch(IOException e){
            return new ArrayList<>();
        }
    } 
    
    private  void writeCategoria(List<Categoria> categoria){
        try(Writer writer = new FileWriter(CAMINHO_ARQUIVO)){
            gson.toJson(categoria, writer);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    private static int nextId(List<Categoria> list){
        return list.stream().mapToInt(Categoria::getId).max().orElse(0) + 1;
    }
    
    public boolean salvarCategoria(Categoria  categoria){
        //Categoria categoria = gson.fromJson(categoriaMenssagem, Categoria.class);
        List<Categoria> list = readCategorias();
        int id;
        if(categoria.getId() == 0){
            id = nextId(list);
            categoria.setId(id);
            list.add(categoria);
            writeCategoria(list);
            return true;
        }else{
            for(Categoria c : list){
                if(c.getId() == categoria.getId()){
                    c.setNome(categoria.getNome());
                    writeCategoria(list);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean excluirCategoria(int id){
        List<Categoria> list = readCategorias();
        for(Categoria c : list){
            if(c.getId() == id){
                if(seg.validarVinculoCategoriaAviso(id)){
                    return false;
                }
                list.remove(c);
                writeCategoria(list);
                return true;
            }
        }
        return false;
    }
    
    public Categoria localizarCategoria(int id){
        List<Categoria> list = readCategorias();
        for(Categoria c : list){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
    
    public List<Categoria> listarCategorias(){
        return readCategorias();
    }

    public List<Categoria> listarCategoriasCadastradas(List<Integer> list){
        List<Categoria> categorias = readCategorias();
        List<Categoria> categoriasCadastradas = new ArrayList<>();
        for(int i : list){
            for(Categoria c : categorias){
                if(c.getId() == i){
                    categoriasCadastradas.add(c);
                }
            }
        }
        return categoriasCadastradas;
    }
}

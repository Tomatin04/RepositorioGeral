/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Aviso;
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
 * @author mathe
 */
public class AvisoJSON {
    private static final String CAMINHO_ARQUIVO = "avisos.json";
    private final Gson gson;
    private final VinculoJSON vinculo;

    public AvisoJSON(){
        vinculo = new VinculoJSON();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private  List<Aviso> readAvisos(){
        try(Reader reader = new FileReader(CAMINHO_ARQUIVO)){
            return gson.fromJson(reader, new TypeToken<List<Aviso>>() {}.getType());
        }catch(IOException e){
            return new ArrayList<>();
        }
    } 
    
    private  void writeAviso(List<Aviso> aviso){
        try(Writer writer = new FileWriter(CAMINHO_ARQUIVO)){
            gson.toJson(aviso, writer);
        }catch(IOException e){
            
        }
    }
    
    private static int nextId(List<Aviso> list){
        return list.stream().mapToInt(Aviso::getId).max().orElse(0) + 1;
    }
    
    public boolean salvarAviso(Aviso aviso){
        //Aviso aviso = gson.fromJson(avisoMenssagem, Aviso.class);
        List<Aviso> avisos = readAvisos();
        int id;
        if(aviso.getId() == 0){
            id = nextId(avisos);
            aviso.setId(id);
            avisos.add(aviso);
            writeAviso(avisos);
            return true;
        }else{
            for(Aviso a : avisos){
                if(a.getId() == aviso.getId()){
                    a.setTitulo(aviso.getTitulo());
                    a.setDescricao(aviso.getDescricao());
                    a.setCategoria(aviso.getCategoria());
                    writeAviso(avisos);
                    return true;
                }
            }
        }
        return false;
        
    }
    
    public boolean excluirAviso(int id){
        List<Aviso> avisos = readAvisos();
        for(Aviso a : avisos){
            if(a.getId() == id){
                avisos.remove(a);
                writeAviso(avisos);
                return true;
            }
        }
        return false;
    }
    
    public Aviso localizarAvisos(int id){
        
        List<Aviso> list = readAvisos();
        for(Aviso a : list){
            if(a.getId() == id){
                a.setCategoria((Categoria)vinculo.localizarCategoriaDoAviso((int)(double)a.getCategoria()));
                return a;
            }
        }
        return null;
    }
    
    public List<Aviso> listarAvisos(int categoria){
        
        List<Aviso> list = readAvisos();
        List<Aviso> avisos = new ArrayList<>();
        if(categoria == 0){
            for(Aviso a : list){
               
                avisos.add(localizarAvisos(a.getId()));
            }
        }else{
            
            for(Aviso a : list){
                if((Integer)a.getCategoria() == categoria){
                    avisos.add(localizarAvisos(a.getId()));
                }
            }
        }
        return avisos;
    }
    
        
    
}


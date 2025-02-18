/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import entities.Aviso;
import entities.Categoria;
import entities.Usuario;
import entities.Vinculo;

/**
 *
 * @author mathe
 */
public class VinculoJSON {
    private static final String CAMINHO_ARQUIVO_AVISO = "avisos.json";
    private static final String CAMINHO_ARQUIVO_CATEGORIA = "categorias.json";
    private static final String CAMINHO_ARQUIVO_VINCULO = "vinculos.json";
    private final Gson gson;
    
    public VinculoJSON(){
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /*  Leitura dos Avisos */
    private List<Aviso> readAvisos(){
        try(Reader reader = new FileReader(CAMINHO_ARQUIVO_AVISO)){
            return gson.fromJson(reader, new TypeToken<List<Aviso>>() {}.getType());
        }catch(IOException e){
            return new ArrayList<>();
        }
    }

    /*  Leitura das Categorias */
    private List<Categoria> readCategorias(){
        try(Reader reader = new FileReader(CAMINHO_ARQUIVO_CATEGORIA)){
            return gson.fromJson(reader, new TypeToken<List<Categoria>>() {}.getType());
        }catch(IOException e){
            return new ArrayList<>();
        }
    }

    /*  Leitura e escrita dos Vinculos */
    private List<Vinculo> readVinculos(){
        try(Reader reader = new FileReader(CAMINHO_ARQUIVO_VINCULO)){
            return gson.fromJson(reader, new TypeToken<List<Vinculo>>() {}.getType());
        }catch(IOException e){
            return new ArrayList<>();
        }
    }

    private  void writeUsuario(List<Vinculo> vinculos){
        try(Writer writer = new FileWriter(CAMINHO_ARQUIVO_VINCULO)){
            gson.toJson(vinculos, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /*  Funções */

    public boolean validarVinculoCategoriaAviso(int idCategoria){
        List<Aviso> avisos = readAvisos();
        for(Aviso a : avisos){
            if((int) (double) a.getCategoria() == idCategoria){
                return true;
            }
        }
        return false;
    }

    public Categoria localizarCategoriaDoAviso(int idCategoria){
        List<Categoria> categorias = readCategorias();
        for(Categoria c : categorias){
            if(c.getId() == idCategoria){
                return c;
            }
        }
        return null;
    }


    public boolean criarVinculo(String ra){
        List<Vinculo> vinculos = readVinculos();
        Vinculo v = new Vinculo(ra);
        vinculos.add(v);
        writeUsuario(vinculos);
        return true;
    }

    //Criar uma função paara validar se o ID da categoria existe e repassar para UsuarioJSON

    public boolean cadastrarUsuarioCategoria(String ra, int idCategoria){
        List<Vinculo> vinculos = readVinculos();
        //Campo de validação do id da categoria
        for(Vinculo v : vinculos){
            if(v.getRa().equals(ra)){
                if(v.getCategorias().contains(idCategoria)){
                    return false;
                }
                v.getCategorias().add(idCategoria);
                writeUsuario(vinculos);
                return true;
            }
        }
        return false;
    }

    public List<Integer> listarCategoriasUsuario(String ra){
        List<Vinculo> vinculos = readVinculos();

        for(Vinculo v : vinculos){
            if(v.getRa().equals(ra)){

                return v.getCategorias();
            }
        }
        return null;
    }


    public boolean descadastrarCategoriaUsuario(String ra, int idCategoria){
        List<Vinculo> vinculos = readVinculos();
        for(Vinculo v : vinculos){
            if(v.getRa().equals(ra)){
                if(v.getCategorias().contains(idCategoria)){
                    v.getCategorias().remove(Integer.valueOf(idCategoria));
                    writeUsuario(vinculos);
                    return true;
                }
            }
        }
        return false;
    }



    public List<Aviso> localizarAvisosDoUsuario(String ra){
        List<Vinculo> vinculos = readVinculos();
        List<Integer> categoriasUser = new ArrayList<>(); // Todos as categorias que o usário está vinculado
        for(Vinculo v : vinculos){
            if(v.getRa().equals(ra)){
                categoriasUser.addAll(v.getCategorias());
            }
        }
        List<Aviso> avisos = readAvisos();
        List<Aviso> avisosUsuario = new ArrayList<>();
        for(Aviso a : avisos){
            if(categoriasUser.contains((int) (double)a.getCategoria())){
                a.setCategoria((Categoria) localizarCategoriaDoAviso((int)(double)a.getCategoria()));
                avisosUsuario.add(a);
            }
        }
        return avisosUsuario;
    }
}

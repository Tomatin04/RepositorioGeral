/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Usuario;
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
public class UsuarioJSON {
    private static final String CAMINHO_ARQUIVO = "usuarios.json";
    private final Gson gson;
    private final VinculoJSON seg;
    
    public UsuarioJSON(){
        seg = new VinculoJSON();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private  List<Usuario> readUsuarios(){
        try(Reader reader = new FileReader(CAMINHO_ARQUIVO)){
            return gson.fromJson(reader, new TypeToken<List<Usuario>>() {}.getType());
        }catch(IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    } 
    
    private  void writeUsuario(List<Usuario> user){
        try(Writer writer = new FileWriter(CAMINHO_ARQUIVO)){
            gson.toJson(user, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private static int nextId(List<Usuario> list){
        return list.stream().mapToInt(Usuario::getId).max().orElse(0) + 1;
    }

    
    public boolean adicionarUsuario(Usuario usuario){
        List <Usuario> usuarios = readUsuarios();
        int id = nextId(usuarios);
        for(Usuario u : usuarios){
            if(u.getRa().equals(usuario.getRa())){
                
                return false;
            }
        }
        usuario.setId(id);
        usuarios.add(usuario);
        writeUsuario(usuarios);
        return true;
    }
    
    public boolean excluirUsuario(String ra){
        List <Usuario> usuarios = readUsuarios();
        for(Usuario u : usuarios){
            if(u.getRa().equals(ra)){
                usuarios.remove(u);
                writeUsuario(usuarios);
                return true;
            }
        }
        return false;
    }

    public boolean editarUsuario(Usuario usuario){
        List <Usuario> users = readUsuarios();
        for(Usuario u : users){
            if(u.getRa().equals(usuario.getRa())){
                u.setNome(usuario.getNome());
                u.setSenha(usuario.getSenha());
                writeUsuario(users);
                return true;
            }
        }
        return false;
    }
    
    public Usuario localizarUsuario(String ra){
        List <Usuario> usuarios = readUsuarios();
        for(Usuario u : usuarios){
            if(u.getRa().equals(ra)){
                return u;
            }
        }
        return null;
    }
    
    public List<Usuario> listarUsuarios(){
        return readUsuarios();
    }

    /* 
    public boolean incluirCategoriasList(String ra){
        List <Usuario> usuarios = readUsuarios();
        for(Usuario u : usuarios){
            if(u.getRa().equals(ra)){
                if(u.getCategorias() == null){
                    u.setCategorias();
                    usuarios.add(u);
                    writeUsuario(usuarios);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean incluirCategoria(String ra, int id){
        List <Usuario> usuarios = readUsuarios();
        for(Usuario u : usuarios){
            if(u.getRa().equals(ra)){
                if(u.getCategorias() == null){
                    u.getCategorias().add(id);
                    usuarios.add(u);
                    writeUsuario(usuarios);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean descadastrarCategoria(String ra, int id){
        List <Usuario> usuarios = readUsuarios();
        for(Usuario u : usuarios){
            if(u.getRa().equals(ra)){
                if(u.getCategorias() != null){
                    u.getCategorias().remove(id);
                    usuarios.add(u);
                    writeUsuario(usuarios);
                    return true;
                }
            }
        }
        return false;
    }

    public Usuario listarAvisos(String ra){
        Usuario user = localizarUsuario(ra);
        user.setAvisos(seg.localizarAvisosDoUsuario(user.getCategorias()));
        return user;
    }

    /**/
}

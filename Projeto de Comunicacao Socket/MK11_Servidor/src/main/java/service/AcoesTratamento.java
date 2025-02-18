/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.Aviso;
import entities.Categoria;
import entities.Informacoes;
import entities.Usuario;
import json.AvisoJSON;
import json.CategoriaJSON;
import json.UsuarioJSON;
import json.VinculoJSON;

/**
 *
 * @author matheus.sampaio
 */


public class AcoesTratamento {
    
    private final UsuarioJSON uJSON;
    private final AvisoJSON aJSON;
    private final CategoriaJSON cJSON;
    private final VinculoJSON vJSON;
    
    
    
    public AcoesTratamento(){
        uJSON = new UsuarioJSON();
        aJSON = new AvisoJSON();
        cJSON = new CategoriaJSON();
        vJSON = new VinculoJSON();
    }
    
    //                                          //
    // ---------- Funções de suporte ---------- //
    //                                          //
    
    private boolean validarInformcacoesCadastrais(Informacoes informaceos){
        if(informaceos.getRa() != null && informaceos.getNome() != null && informaceos.getSenha() != null) return false;
        return true;
    }
    
    private Usuario transformarInfoInUser(Informacoes informacoes){
        Usuario user = new Usuario();
        user.setNome(informacoes.getNome());
        user.setSenha(informacoes.getSenha());
        user.setRa(informacoes.getRa());
        return user;
    }

    private Informacoes verificacaoADM(Informacoes informacoes){
        if(informacoes.getToken().equals("2509849")){
            informacoes.setStatus(0);
            return informacoes;
        };
        //informacoes.limparInformacoes();
        //informacoes.setOperacao("listarUsuarios");
        informacoes.setMensagem("Apenas ADMs podem usar está função");
        informacoes.setStatus(401);
        return informacoes;
    }

    private boolean verificacaoUserAcao(Informacoes informacoes){
        if(informacoes.getToken().equals(informacoes.getRa())) return true;
        if(informacoes.getToken().equals(informacoes.getUsuario().getRa())) return true;
        return false;
    }

    private boolean validarSenha(Informacoes informacoes){
        if(informacoes.getSenha().length() > 8 ) return true;
        if(informacoes.getSenha().matches("^[a-zA-Z]+$")) return true;
        return false;
    }
    
    // Criar uma função para facilitar a criação da menssagem de usuário não encontrado

    //                                          //
    // ---------- Funções de Usuário ---------- //
    //                                          //

    public Informacoes login(Informacoes informacoes){
        Usuario usuario = uJSON.localizarUsuario(informacoes.getRa());
        if(usuario != null){
            if(usuario.getSenha().equals(informacoes.getSenha())){
                informacoes.limparInformacoes();
                informacoes.setToken(usuario.getRa());
                informacoes.setStatus(200);
                //informacoes.setOperacao("login");
                //if(usuario.getIsAdmin())informacoes.setOperacao("90816281");
                return informacoes;
            }else{
                informacoes.limparInformacoes();
                informacoes.setOperacao("login");
                informacoes.setMensagem("Credenciais incorretas");
                informacoes.setStatus(401);
                return informacoes;
            }
        }
        informacoes.limparInformacoes();
        informacoes.setOperacao("login");
        informacoes.setMensagem("Campos recebidos não são validos");
        informacoes.setStatus(401);
        return informacoes;
    }
    
    
    public Informacoes logout(Informacoes informacoes){
        informacoes.limparInformacoes();
        informacoes.setStatus(200);
        return informacoes;
    }
    
    public Informacoes cadastrarUsuario(Informacoes informacoes){
        if(validarInformcacoesCadastrais(informacoes)){
            informacoes.limparInformacoes();
            informacoes.setMensagem("Os campos recebidos não são validos");
            informacoes.setStatus(401);
            return informacoes;
        }
        if(uJSON.localizarUsuario(informacoes.getRa()) != null){
            informacoes.limparInformacoes();
            informacoes.setMensagem("O usuário já está cadastrado no sistema");
            informacoes.setStatus(401);
            return informacoes;
        }
        uJSON.adicionarUsuario(transformarInfoInUser(informacoes));
        vJSON.criarVinculo(informacoes.getRa());
        informacoes.limparInformacoes();
        informacoes.setOperacao("cadastrarUsuario");
        informacoes.setStatus(201);
        informacoes.setMensagem("Cadastro realizad com sucesso");
        return informacoes;
    }
   
    public Informacoes listarUsuarios(Informacoes informacoes){
    informacoes = verificacaoADM(informacoes);
    if(informacoes.getStatus() == 401) return informacoes;
    informacoes.limparInformacoes();
    informacoes.setUsuarios(uJSON.listarUsuarios());
    informacoes.setOperacao("listarUsuarios");
    informacoes.setStatus(201);
    return informacoes;
   } 
    
    public Informacoes localizarUsuario(Informacoes informacoes){    
            if(verificacaoUserAcao(informacoes) || verificacaoADM(informacoes).getStatus() != 401){
                Usuario user = uJSON.localizarUsuario(informacoes.getRa());
                if(user == null){
                    informacoes.limparInformacoes();
                    informacoes.setOperacao("localizarUsuario");
                    informacoes.setMensagem("Usuário não encontrado");
                    informacoes.setStatus(401);
                    return informacoes;
                }
                informacoes.limparInformacoes();
                informacoes.setUsuario(user);
                informacoes.setOperacao("localizarUsuario");
                informacoes.setStatus(201);
                return informacoes;
            }else{
                informacoes.limparInformacoes();
                informacoes.setMensagem("Usuários não tem autorização para visualizar as informações de outro usuário");
                informacoes.setOperacao("localizarUsuario");
                informacoes.setStatus(401);
                return informacoes;
            }
        
    }

    public Informacoes excluirUsuario(Informacoes informacoes){
            if(verificacaoUserAcao(informacoes) || verificacaoADM(informacoes).getStatus() == 401){
                Usuario user = uJSON.localizarUsuario(informacoes.getRa());
                if(user == null){
                    informacoes.limparInformacoes();
                    informacoes.setOperacao("excluirUsuario");
                    informacoes.setMensagem("Usuário não encontrado");
                    informacoes.setStatus(401);
                    return informacoes;
                }
                uJSON.excluirUsuario(informacoes.getRa());
                informacoes.limparInformacoes();
                informacoes.setMensagem("Usuário excluido com sucesso");
                informacoes.setOperacao("excluirUsuario");
                informacoes.setStatus(201);
                return informacoes;
            }else{
                informacoes.limparInformacoes();
                informacoes.setMensagem("Usuários não tem autorização para excluir as informações de outro usuário");
                informacoes.setOperacao("excluirUsuario");
                informacoes.setStatus(401);
                return informacoes;
            }
    }

    public Informacoes editarUsuario(Informacoes informacoes){
        Usuario userNew = informacoes.getUsuario();
            if(verificacaoUserAcao(informacoes) && informacoes.getRa() != null || verificacaoADM(informacoes).getStatus() == 401){ //colocar validação para ver a existencia do ra usuário
                Usuario user = uJSON.localizarUsuario(informacoes.getUsuario().getRa());
                if(user == null){
                    informacoes.limparInformacoes();
                    informacoes.setOperacao("editarUsuario");
                    informacoes.setMensagem("Usuário não encontrado");
                    informacoes.setStatus(401);
                    return informacoes;
                }
                uJSON.editarUsuario(userNew);
                informacoes.limparInformacoes();
                informacoes.setMensagem("Edição realizada com sucesso");
                informacoes.setOperacao("editarUsuario");
                informacoes.setStatus(201);
                return informacoes;
            }else{
                informacoes.limparInformacoes();
                informacoes.setMensagem("Usuários não tem autorização para excluir as informações de outro usuário");
                informacoes.setOperacao("editarUsuario");
                informacoes.setStatus(401);
                return informacoes;
            }
        
    }

    //                                          //
    // ---------- Funções de Categoria ---------- //
    //                                          //

    public Informacoes salvarCategoria(Informacoes informacoes){
        informacoes = verificacaoADM(informacoes);
        if(informacoes.getStatus() == 401) return informacoes;
        if(!cJSON.salvarCategoria(new Categoria().getCategoria(informacoes.getCategoria()))){
            informacoes.limparInformacoes();
            informacoes.setOperacao("salvarCategoria");
            informacoes.setStatus(401);
            informacoes.setMensagem("Categoria não encontrada");
            return informacoes;
        }
        informacoes.limparInformacoes();
        informacoes.setOperacao("salvarCategoria");
        informacoes.setStatus(201);
        informacoes.setMensagem("Categoria salva com sucesso");
        return informacoes;
    }

    public Informacoes listarCategorias(Informacoes informacoes){
        informacoes.limparInformacoes();
        informacoes.setCategorias(cJSON.listarCategorias());
        informacoes.setOperacao("listarCategorias");
        informacoes.setStatus(201);
        return informacoes;
    }

    public Informacoes localizarCategoria(Informacoes informacoes){
        Categoria categoria = cJSON.localizarCategoria(informacoes.getId());
        if(categoria == null){
            informacoes.limparInformacoes();
            informacoes.setOperacao("localizarCategoria");
            informacoes.setMensagem("Categoria não encontrada");
            informacoes.setStatus(401);
            return informacoes;
        }
        informacoes.setCategoria(categoria);
        informacoes.setOperacao("localizarCategoria");
        informacoes.setStatus(201);
        return informacoes;
    }

    public Informacoes excluirCategoria(Informacoes informacoes){
        informacoes = verificacaoADM(informacoes);
        if(informacoes.getStatus() == 401) return informacoes; 
        if(cJSON.localizarCategoria(informacoes.getId()) == null){
            informacoes.limparInformacoes();
            informacoes.setOperacao("excluirCategoria");
            informacoes.setMensagem("Categoria não encontrada");
            informacoes.setStatus(401);
            return informacoes;
        }
        if(cJSON.excluirCategoria(informacoes.getId())){
            informacoes.limparInformacoes();
            informacoes.setOperacao("excluirCategoria");
            informacoes.setMensagem("Categoria excluida com sucesso");
            informacoes.setStatus(201);
            return informacoes;
        }
        informacoes.setMensagem("Categoria não pode ser excluida, existe um aviso vinculado a ela");
        informacoes.setOperacao("excluirCategoria");
        informacoes.setStatus(401);
        return informacoes;
    }

    //                                          //
    // ---------- Funções de Aviso ---------- //
    //                                          //

    public Informacoes salvarAviso(Informacoes informacoes){
        informacoes = verificacaoADM(informacoes);
        if(informacoes.getStatus() == 401) return informacoes;
        if(!aJSON.salvarAviso(informacoes.getAviso())){
            informacoes.limparInformacoes();
            informacoes.setOperacao("salvarAviso");
            informacoes.setStatus(401);
            informacoes.setMensagem("Aviso não encontrado");
            return informacoes;
        }
        informacoes.limparInformacoes();
        informacoes.setOperacao("salvarAviso");
        informacoes.setStatus(201);
        informacoes.setMensagem("Aviso salvo com sucesso");
        return informacoes;
    }

    public Informacoes listarAvisos(Informacoes informacoes){
        int categoria = (int)(double)informacoes.getCategoria();
        informacoes.limparInformacoes();
        informacoes.setAvisos(aJSON.listarAvisos(categoria));
        informacoes.setOperacao("listarAvisos");
        informacoes.setStatus(200);
        return informacoes;
    }

    public Informacoes localizarAviso(Informacoes informacoes){
        Aviso aviso = aJSON.localizarAvisos(informacoes.getId());
        if(aviso == null){
            informacoes.limparInformacoes();
            informacoes.setOperacao("localizarAviso");
            informacoes.setMensagem("Aviso não encontrado");
            informacoes.setStatus(401);
            return informacoes;
        }
        informacoes.setAviso(aviso);
        informacoes.setOperacao("localizarAviso");
        informacoes.setStatus(201);
        return informacoes;
    }

    public Informacoes excluirAviso(Informacoes informacoes){
        informacoes = verificacaoADM(informacoes);
        if(informacoes.getStatus() == 401) return informacoes;
        if(aJSON.localizarAvisos(informacoes.getId()) == null){
            informacoes.limparInformacoes();
            informacoes.setOperacao("excluirAviso");
            informacoes.setMensagem("Aviso não encontrado");
            informacoes.setStatus(401);
            return informacoes;
        }
        aJSON.excluirAviso(informacoes.getId());
        informacoes.limparInformacoes();
        informacoes.setOperacao("excluirAviso");
        informacoes.setMensagem("Aviso excluido com sucesso");
        informacoes.setStatus(201);
        return informacoes;
    }

    //                                          //
    // ---------- Funções de Atribuição e listagem ---------- //
    //                                          //

    public Informacoes cadastrarUsuarioCategoria(Informacoes informacoes){
        vJSON.cadastrarUsuarioCategoria(informacoes.getRa(), (int) (double)informacoes.getCategoria());
        informacoes.limparInformacoes();
        informacoes.setOperacao("cadastrarUsuarioCategoria");
        informacoes.setMensagem("Cadastro em categoria realizado com sucesso");
        informacoes.setStatus(201);
        return informacoes;
    }

    public Informacoes listarUsuarioCategorias(Informacoes informacoes){
        String ra = informacoes.getRa();
        informacoes.limparInformacoes();
        informacoes.setCategorias(vJSON.listarCategoriasUsuario(ra));
        informacoes.setOperacao("listarUsuarioCategorias");
        informacoes.setStatus(201);
        return informacoes;
    }

    public Informacoes descadastrarUsuarioCategoria(Informacoes informacoes){
        vJSON.descadastrarCategoriaUsuario(informacoes.getRa(), (int)(double)informacoes.getCategoria());
        informacoes.limparInformacoes();
        informacoes.setOperacao("descadastrarUsuarioCategoria");
        informacoes.setMensagem("Descadastro em categoria realizado com sucesso");
        informacoes.setStatus(201);
        return informacoes;
    }

    public Informacoes listarUsuarioAvisos(Informacoes informacoes){
        String ra = informacoes.getRa();
        informacoes.limparInformacoes();
        informacoes.setAvisos(vJSON.localizarAvisosDoUsuario(ra));
        informacoes.setOperacao("listarUsuarioAvisos");
        informacoes.setStatus(201);
        return informacoes;
    }
}

    
 

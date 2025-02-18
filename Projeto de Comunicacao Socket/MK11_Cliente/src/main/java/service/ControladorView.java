/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import entities.Aviso;
import entities.Categoria;
import entities.Informacoes;
import entities.Usuario;
import gui.Panel04;
import gui.Panel05;
import gui.Panel07;
import gui.Panel08;
import gui.Panel09;
import gui.Panel10;

/**
 *
 * @author matheus.sampaio
 */
public class ControladorView {
    
    private Controlador controlador;
    private CardLayout cardLayout;
    private JPanel painel;
    private Informacoes info;
    private String ra;
    private String senha;
    private String nome;

    public ControladorView(JPanel layout) {
        this.painel = layout;
        cardLayout = (CardLayout) painel.getLayout();
    }


    /*
     * Funções de conexão com o servidor
     */
    
    public boolean selectServer(String host, String porta) {
        try {
            controlador = new Controlador(host, Integer.parseInt(porta));
            return true;
        } catch (NumberFormatException | IOException e) {
            funcAlert("Erro ao conectar com o servidor");
            //e.printStackTrace();
            return false;
        } catch (Exception e) {
            funcAlert("Erro inesperado ao conectar com o servidor");
            //e.printStackTrace();
            return false;
        }
    }

    public boolean login(String ra, char[] senha){  
        try {
            info = controlador.login(ra, String.valueOf(senha));
            if(info.getStatus() == 200){
                this.ra = ra;
                return true;
            }
            funcAlert(controlador.getMsg());
            return false;
        } catch (Exception e) {
            funcAlert("Erro inesperado ao fazer login");
            //e.printStackTrace();
            return false;
        }
    }

    public boolean admLogon(){
        try {
            if(info.getToken().equals("2509849")){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao verificar logon de administrador");
            //e.printStackTrace();
            return false;
        }
    }

    public void logout() throws InterruptedException{
        try {
            ra = null;
            senha = null;
            nome = null;
            controlador.logout();
            panel02();
        } catch (Exception e) {
            funcAlert("Erro inesperado ao fazer logout");
            //e.printStackTrace();
        }
    }

    /*
     * Funções de avisos
     */

    public void localizarAviso(int id){
        try {
            info = controlador.localizarAviso(id);
            if(info.getStatus() == 201){
                funcAlert(info.getAviso().toString());
            } else {
                funcAlert(info.getMensagem());
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao localizar aviso");
            //e.printStackTrace();
        }
    }

    public void excluirAviso(int id){
        try {
            if(funcOption()){
                info = controlador.excluirAviso(id);
                funcAlert(info.getMensagem());
                return;
            }
            funcAlert("Operação cancelada");
        } catch (Exception e) {
            funcAlert("Erro inesperado ao excluir aviso");
            //e.printStackTrace();
        }
    }

    public void salvarAviso(int id, String titulo, int categoria, String descricao){
        try {
            info = controlador.salvarAviso(id, titulo, categoria, descricao);
            funcAlert(info.getMensagem());
        } catch (Exception e) {
            funcAlert("Erro inesperado ao salvar aviso");
            //e.printStackTrace();
        }
    }

    /*
     * Funções de categoria
     */
    public void localizarCategoria(int id){
        try {
            info = controlador.localizarCategoria(id);
            if(info.getStatus() == 201){
                funcAlert(info.getCategoria().toString());
            } else {
                funcAlert(info.getMensagem());
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao localizar categoria");
            //e.printStackTrace();
        }
    }

    public void excluirCategoria(int id){
        try {
            if(funcOption()){
                info = controlador.excluirCategoria(id);
                funcAlert(info.getMensagem());
                return;
            }
            funcAlert("Operação cancelada");
        } catch (Exception e) {
            funcAlert("Erro inesperado ao excluir categoria");
            //e.printStackTrace();
        }
    }

    public void salvarCategoria(int id, String nome){
        try {
            info = controlador.salvarCategoria(id, nome);
            funcAlert(info.getMensagem());
        } catch (Exception e) {
            funcAlert("Erro inesperado ao salvar categoria");
            //e.printStackTrace();
        }
    }

    /*
     * Funções de usuário
     */

    public void localizarUsuario(String ra){
        try {
            info = controlador.localizarUsuario(ra);
            if(info.getStatus() == 201){
                funcAlert(info.getUsuario().toString());
            } else {
                funcAlert(info.getMensagem());
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao localizar usuário");
            //e.printStackTrace();
        }
    }

    public void localizarUsuarioViewSet(){
        try {
            info = controlador.localizarUsuario(ra);
            senha = info.getUsuario().getSenha();
            nome = info.getUsuario().getNome();
            info.limparInformacoesExToken();
        } catch (Exception e) {
            funcAlert("Erro inesperado ao localizar e configurar usuário");
            //e.printStackTrace();
        }
    }

    public boolean excluirUsuario(String ra){
        try {
            if(funcOption()){
                info = controlador.excluirUsuario(ra);
                funcAlert(info.getMensagem());
                return true;
            }
            funcAlert("Operação cancelada");
            return false;
        } catch (Exception e) {
            funcAlert("Erro inesperado ao excluir usuário");
            //e.printStackTrace();
            return false;
        }
    }

    public void cadastrarUsuario(String ra, String nome, String senha){
        try {
            info = controlador.cadastrarUsuario(ra, nome, senha);
            funcAlert(info.getMensagem());
        } catch (Exception e) {
            funcAlert("Erro inesperado ao cadastrar usuário");
            //e.printStackTrace();
        }
    }

    public void editarUsuario(String ra, String nome, String senha){
        try {
            info = controlador.editarUsuario(ra, nome, senha);
            funcAlert(info.getMensagem());
        } catch (Exception e) {
            funcAlert("Erro inesperado ao editar usuário");
            //e.printStackTrace();
        }
    }

    /*
     * Funções de vinculo
     */

    public void listarUsuarioCategorias(String ra){
        try {
            info = controlador.listarUsuarioCategorias(ra);
            if(info.getStatus() == 201){
                funcAlert(info.getCategorias().toString());
            } else {
                funcAlert(info.getMensagem());
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao listar categorias de usuário");
            //e.printStackTrace();
        }
    }

    public void descadastrarUsuarioCategoria(String ra, int categoria){
        try {
            if(funcOption()){
                info = controlador.descadastrarUsuarioCategoria(ra, categoria);
                funcAlert(info.getMensagem());
                return;
            }
            funcAlert("Operação cancelada");
        } catch (Exception e) {
            funcAlert("Erro inesperado ao descadastrar categoria de usuário");
            //e.printStackTrace();
        }
    }

    public void cadastrarUsuarioCategoria(String ra, int categoria){
        try {
            info = controlador.cadastrarUsuarioCategoria(ra, categoria);
            funcAlert(info.getMensagem());
        } catch (Exception e) {
            funcAlert("Erro inesperado ao cadastrar categoria de usuário");
            //e.printStackTrace();
        }
    }

    /*
     * Peenchimeno de tabelas 
     */

    public void preencherAvisosAdm(Panel07 painel){
        try {
            DefaultTableModel modelo = (DefaultTableModel) painel.jTable1().getModel();
            modelo.setRowCount(0);
            info = controlador.listarAvisos(0);
            System.out.println(info.toString());
            for(Aviso a : info.getAvisos()){
                Categoria c = new Categoria().getCategoria(a.getCategoria());
                String[] dados = {
                    String.valueOf(a.getId()),
                    a.getTitulo(),
                    a.getDescricao(),
                    c.getNome()
                };
                modelo.addRow(dados);
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao preencher avisos");
            //e.printStackTrace();
        }
    }

    public void preencherCategoriasAdm(Panel08 painel){
        try {
            DefaultTableModel modelo = (DefaultTableModel) painel.jTable1().getModel();
            modelo.setRowCount(0);
            info = controlador.listarCategorias();
            System.out.println(info.toString());
            for(Categoria c : new Categoria().getCategorias(info.getCategorias())){
                String[] dados = {
                    String.valueOf(c.getId()),
                    c.getNome()
                };
                modelo.addRow(dados);
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao preencher categorias");
            //e.printStackTrace();
        }
    }

    public void preencherCategoriasAdm(Panel05 painel){
        try {
            DefaultTableModel modelo = (DefaultTableModel) painel.jTable1().getModel();
            modelo.setRowCount(0);
            info = controlador.listarCategorias();
            System.out.println(info.toString());
            for(Categoria c : new Categoria().getCategorias(info.getCategorias())){
                String[] dados = {
                    String.valueOf(c.getId()),
                    c.getNome()
                };
                modelo.addRow(dados);
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao preencher categorias");
            //e.printStackTrace();
        }
    }

    public void preencherUsuariosAdm(Panel09 painel){
        try {
            DefaultTableModel modelo = (DefaultTableModel) painel.jTable1().getModel();
            modelo.setRowCount(0);
            info = controlador.listarUsuarios();
            System.out.println(info.toString());
            for(Usuario u : info.getUsuarios()){
                String[] dados = {
                    u.getRa(),
                    u.getNome(),
                    u.getSenha()
                };
                modelo.addRow(dados);
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao preencher usuários");
            //E.printStackTrace();
        }
    }

    public void preencherListarUsuarioAvisos(Panel10 painel, String ra){
        try {
            DefaultTableModel modelo = (DefaultTableModel) painel.jTable1().getModel();
            modelo.setRowCount(0);
            info = controlador.listarUsuarioAvisos(ra);
            System.out.println(info.toString());
            for(Aviso a : info.getAvisos()){
                Categoria c = new Categoria().getCategoria(a.getCategoria());
                String[] dados = {
                    String.valueOf(a.getId()),
                    a.getTitulo(),
                    a.getDescricao(),
                    c.getNome()
                };
                modelo.addRow(dados);
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao preencher avisos de usuário");
            //e.printStackTrace();
        }
    }

    public void preencherListarUsuarioAvisos(Panel04 painel, String ra){
        try {
            DefaultTableModel modelo = (DefaultTableModel) painel.jTable1().getModel();
            modelo.setRowCount(0);
            info = controlador.listarUsuarioAvisos(ra);
            System.out.println(info.toString());
            for(Aviso a : info.getAvisos()){
                Categoria c = new Categoria().getCategoria(a.getCategoria());
                String[] dados = {
                    String.valueOf(a.getId()),
                    a.getTitulo(),
                    a.getDescricao(),
                    c.getNome()
                };
                modelo.addRow(dados);
            }
        } catch (Exception e) {
            funcAlert("Erro inesperado ao preencher avisos de usuário");
            //e.printStackTrace();
        }
    }

    /*
     * Funções de Apoio
     */

    public void funcAlert(String msg){
        JOptionPane.showMessageDialog(null, msg, "Menssagem", JOptionPane.WARNING_MESSAGE);
    }

    public boolean funcOption(){
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            System.out.println("Usuário escolheu sim.");
            return true;
        } else {
            System.out.println("Usuário escolheu não.");
            return false;
        }
    }

    public void userListOn(){
        controlador.userListOn();
        funcAlert("Requisição enviada ao servidor");
    }

    public String getRa(){
        return ra;
    }
    
    public String getSenha(){
        return senha;
    }

    public String getNome(){
        return nome;
    }

    /*
     * Funções de switch panel
     */


    public void panel01(){
        cardLayout.show(painel, "Panel01");
    }

    public void panel02(){
        cardLayout.show(painel, "Panel02");
    }

    public void panel03(){
        cardLayout.show(painel, "Panel03");
    }

    public void panel04(){
        cardLayout.show(painel, "Panel04");
    }

    public void panel05(){
        cardLayout.show(painel, "Panel05");
    }

    public void panel06(){
        cardLayout.show(painel, "Panel06");
    }

    public void panel07(){
        cardLayout.show(painel, "Panel07");
    }

    public void panel08(){
        cardLayout.show(painel, "Panel08");
    }

    public void panel09(){
        cardLayout.show(painel, "Panel09");
    }

    public void panel10(){
        cardLayout.show(painel, "Panel10");
    }

    
}

package com.refact.controller;

// 8. Exemplo de controlador para interação com a UI


import com.refact.factory.DAOFactory;
import com.refact.models.Usuarios;
import com.refact.service.UsuarioService;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController() {
        // Usa a factory para criar o DAO
        this.usuarioService = new UsuarioService(DAOFactory.createUsuarioDAO());
    }

    public void inicializarBanco() {
        try {
            usuarioService.inicializarBancoDados();
        } catch (Exception e) {
            exibirErro("Erro ao inicializar o banco de dados", e);
        }
    }

    public int autenticarUsuario(String nomeUsuario, String senha) {
        try {
            return usuarioService.autenticar(nomeUsuario, senha);
        } catch (Exception e) {
            exibirErro("Erro ao autenticar usuário", e);
            return 0;
        }
    }
/*
    public void cadastrarUsuario(String nomeCompleto, String nomeUsuario, String senha, String email, String telefone) {
        try {
            Usuarios usuario = new Usuarios(nomeCompleto, nomeUsuario, senha, email, telefone);
            usuarioService.cadastrar(usuario);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            exibirErro("Erro ao cadastrar usuário", e);
        }
    }
*/
    public void cadastrarUsuario(Usuarios usuario) {
        try {          
            usuarioService.cadastrar(usuario);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            exibirErro("Erro ao cadastrar usuário", e);
        }
    }
    
    public void atualizarUsuario(int id, String nomeCompleto, String nomeUsuario, String senha, String email, String telefone) {
        try {
            Usuarios usuario = new Usuarios(id, nomeCompleto, nomeUsuario, senha, email, telefone);
            usuarioService.atualizar(usuario);
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
        } catch (Exception e) {
            exibirErro("Erro ao atualizar usuário", e);
        }
    }

    public void excluirUsuario(int id) {
        try {
            usuarioService.remover(id);
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
        } catch (Exception e) {
            exibirErro("Erro ao excluir usuário", e);
        }
    }

    public List<Usuarios> listarTodosUsuarios() {
        try {
            return usuarioService.listarTodosUsuarios();
        } catch (Exception e) {
            exibirErro("Erro ao listar usuários", e);
            return null;
        }
    }

    private void exibirErro(String mensagem, Exception e) {
        JOptionPane.showMessageDialog(null, mensagem + ": " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
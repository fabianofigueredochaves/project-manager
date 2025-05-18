//package com.projectmanager.mysql;
package com.refact.service;

// 6. Classe de serviço para usuários

import com.refact.dao.UsuarioDAO;
import com.refact.models.Usuarios;
import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;

    // Injeção de dependência
    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public UsuarioService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void inicializarBancoDados() throws Exception {
        usuarioDAO.criarTabela();
    }

    public String obterNomeCompleto(int id) throws Exception {
        return usuarioDAO.obterNomeCompleto(id);
    }

    public List<String> listarNomesUsuarios() throws Exception {
        return usuarioDAO.obterUsuarios();
    }

    public List<Usuarios> listarTodosUsuarios() throws Exception {
        return usuarioDAO.obterTodosUsuarios();
    }

    public int autenticar(String nomeUsuario, String senha) throws Exception {
        return usuarioDAO.autenticarUsuario(nomeUsuario, senha);
    }

    public int obterIdUsuarioAutenticado(String nomeUsuario, String senha) throws Exception {
        return usuarioDAO.autenticarUsuario(nomeUsuario, senha);
    }

    public void cadastrar(Usuarios usuario) throws Exception {
        // Aqui poderia ter validações antes de cadastrar
        usuarioDAO.cadastrarUsuario(usuario);
    }

    public void atualizar(Usuarios usuario) throws Exception {
        usuarioDAO.atualizarUsuario(usuario);
    }

    public void remover(int id) throws Exception {
        usuarioDAO.excluirUsuario(id);
    }
}
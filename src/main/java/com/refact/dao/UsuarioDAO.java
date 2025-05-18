// 2. Interface para o DAO (Data Access Object)
package com.refact.dao;

import com.refact.models.Usuarios;
import java.util.List;


public interface UsuarioDAO {
    
    void criarTabela() throws Exception;
    String obterNomeCompleto(int id) throws Exception;
    List<String> obterUsuarios() throws Exception;
    List<Usuarios> obterTodosUsuarios() throws Exception;
    int autenticarUsuario(String usuario, String senha) throws Exception;
    void cadastrarUsuario(Usuarios usuario) throws Exception;
    void atualizarUsuario(Usuarios usuario) throws Exception;
    void excluirUsuario(int id) throws Exception;
    
}

/*
package com.projectmanager.service;

import com.projectmanager.dao.UsuarioDAO;
import com.projectmanager.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private UsuarioDAO mockUsuarioDAO;

    @BeforeEach
    public void setUp() {
        // Cria um mock do DAO
        mockUsuarioDAO = Mockito.mock(UsuarioDAO.class);
        
        // Cria o serviço com o mock do DAO
        usuarioService = new UsuarioService(mockUsuarioDAO);
    }

    @Test
    @DisplayName("Deve inicializar o banco de dados corretamente")
    public void testInicializarBancoDados() throws Exception {
        // Act
        usuarioService.inicializarBancoDados();
        
        // Assert
        verify(mockUsuarioDAO).criarTabela();
    }

    @Test
    @DisplayName("Deve obter nome completo do usuário")
    public void testObterNomeCompleto() throws Exception {
        // Arrange
        when(mockUsuarioDAO.obterNomeCompleto(1)).thenReturn("João Silva");
        
        // Act
        String nomeCompleto = usuarioService.obterNomeCompleto(1);
        
        // Assert
        assertEquals("João Silva", nomeCompleto);
        verify(mockUsuarioDAO).obterNomeCompleto(1);
    }

    @Test
    @DisplayName("Deve listar nomes de usuários")
    public void testListarNomesUsuarios() throws Exception {
        // Arrange
        List<String> nomesUsuarios = Arrays.asList("joao", "maria", "pedro");
        when(mockUsuarioDAO.obterUsuarios()).thenReturn(nomesUsuarios);
        
        // Act
        List<String> resultado = usuarioService.listarNomesUsuarios();
        
        // Assert
        assertEquals(3, resultado.size());
        assertEquals("joao", resultado.get(0));
        assertEquals("maria", resultado.get(1));
        assertEquals("pedro", resultado.get(2));
        verify(mockUsuarioDAO).obterUsuarios();
    }

    @Test
    @DisplayName("Deve listar todos os usuários")
    public void testListarTodosUsuarios() throws Exception {
        // Arrange
        List<Usuario> usuarios = Arrays.asList(
            new Usuario(1, "João Silva", "joao", "senha1", "joao@email.com", "999887766"),
            new Usuario(2, "Maria Santos", "maria", "senha2", "maria@email.com", "988776655")
        );
        when(mockUsuarioDAO.obterTodosUsuarios()).thenReturn(usuarios);
        
        // Act
        List<Usuario> resultado = usuarioService.listarTodosUsuarios();
        
        // Assert
        assertEquals(2, resultado.size());
        assertEquals(1, resultado.get(0).getId());
        assertEquals("João Silva", resultado.get(0).getNomeCompleto());
        assertEquals(2, resultado.get(1).getId());
        assertEquals("Maria Santos", resultado.get(1).getNomeCompleto());
        verify(mockUsuarioDAO).obterTodosUsuarios();
    }

    @Test
    @DisplayName("Deve autenticar usuário com credenciais corretas")
    public void testAutenticarUsuarioComCredenciaisCorretas() throws Exception {
        // Arrange
        when(mockUsuarioDAO.autenticarUsuario("joao", "senha123")).thenReturn(1);
        
        // Act
        boolean resultado = usuarioService.autenticar("joao", "senha123");
        
        // Assert
        assertTrue(resultado);
        verify(mockUsuarioDAO).autenticarUsuario("joao", "senha123");
    }

    @Test
    @DisplayName("Deve rejeitar autenticação com credenciais incorretas")
    public void testAutenticarUsuarioComCredenciaisIncorretas() throws Exception {
        // Arrange
        when(mockUsuarioDAO.autenticarUsuario("joao", "senha_errada")).thenReturn(0);
        
        // Act
        boolean resultado = usuarioService.autenticar("joao", "senha_errada");
        
        // Assert
        assertFalse(resultado);
        verify(mockUsuarioDAO).autenticarUsuario("joao", "senha_errada");
    }

    @Test
    @DisplayName("Deve rejeitar autenticação com usuário inexistente")
    public void testAutenticarUsuarioInexistente() throws Exception {
        // Arrange
        when(mockUsuarioDAO.autenticarUsuario("inexistente", "qualquer")).thenReturn(-1);
        
        // Act
        boolean resultado = usuarioService.autenticar("inexistente", "qualquer");
        
        // Assert
        assertFalse(resultado);
        verify(mockUsuarioDAO).autenticarUsuario("inexistente", "qualquer");
    }

    @Test
    @DisplayName("Deve obter ID do usuário autenticado")
    public void testObterIdUsuarioAutenticado() throws Exception {
        // Arrange
        when(mockUsuarioDAO.autenticarUsuario("joao", "senha123")).thenReturn(5);
        
        // Act
        int id = usuarioService.obterIdUsuarioAutenticado("joao", "senha123");
        
        // Assert
        assertEquals(5, id);
        verify(mockUsuarioDAO).autenticarUsuario("joao", "senha123");
    }

    @Test
    @DisplayName("Deve cadastrar usuário corretamente")
    public void testCadastrarUsuario() throws Exception {
        // Arrange
        Usuario usuario = new Usuario("João Silva", "joao", "senha123", "joao@email.com", "999887766");
        
        // Act
        usuarioService.cadastrar(usuario);
        
        // Assert
        verify(mockUsuarioDAO).cadastrarUsuario(usuario);
    }

    @Test
    @DisplayName("Deve atualizar usuário corretamente")
    public void testAtualizarUsuario() throws Exception {
        // Arrange
        Usuario usuario = new Usuario(1, "João Silva Atualizado", "joao", "senha123", "joao@email.com", "999887766");
        
        // Act
        usuarioService.atualizar(usuario);
        
        // Assert
        verify(mockUsuarioDAO).atualizarUsuario(usuario);
    }

    @Test
    @DisplayName("Deve remover usuário corretamente")
    public void testRemoverUsuario() throws Exception {
        // Act
        usuarioService.remover(1);
        
        // Assert
        verify(mockUsuarioDAO).excluirUsuario(1);
    }
}
*/
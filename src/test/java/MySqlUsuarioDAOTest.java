/*
package com.projectmanager.dao;

import com.projectmanager.dao.impl.MySqlUsuarioDAO;
import com.projectmanager.models.Usuario;
import com.projectmanager.util.DatabaseConfig;
import com.projectmanager.util.DatabaseConnection;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


 //* Testes unitários para a classe MySqlUsuarioDAO utilizando mocks
 //* para isolar o componente do banco de dados real.
 
public class MySqlUsuarioDAOTest {

    private MySqlUsuarioDAO usuarioDAO;
    private DatabaseConnection mockConnection;
    private Connection mockDbConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        // Cria mocks para os objetos de banco de dados
        mockConnection = Mockito.mock(DatabaseConnection.class);
        mockDbConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        // Configura o comportamento dos mocks
        when(mockConnection.getConnection()).thenReturn(mockDbConnection);
        when(mockDbConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Cria a instância do DAO com o mock da conexão
        usuarioDAO = new MySqlUsuarioDAO(mockConnection);
    }

    @Test
    @DisplayName("Deve criar tabela de usuários")
    public void testCriarTabela() throws Exception {
        // Act
        usuarioDAO.criarTabela();

        // Assert - verifica se os métodos esperados foram chamados
        verify(mockDbConnection).prepareStatement(argThat(sql -> sql.contains("CREATE TABLE IF NOT EXISTS Usuarios")));
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    @DisplayName("Deve obter nome completo quando usuário existe")
    public void testObterNomeCompletoUsuarioExistente() throws Exception {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("NomeCompleto")).thenReturn("João Silva");

        // Act
        String nomeCompleto = usuarioDAO.obterNomeCompleto(1);

        // Assert
        assertEquals("João Silva", nomeCompleto);
        verify(mockPreparedStatement).setInt(1, 1);
    }

    @Test
    @DisplayName("Deve retornar null quando usuário não existe")
    public void testObterNomeCompletoUsuarioInexistente() throws Exception {
        // Arrange
        when(mockResultSet.next()).thenReturn(false);

        // Act
        String nomeCompleto = usuarioDAO.obterNomeCompleto(999);

        // Assert
        assertNull(nomeCompleto);
        verify(mockPreparedStatement).setInt(1, 999);
    }

    @Test
    @DisplayName("Deve listar nomes de usuários")
    public void testObterUsuarios() throws Exception {
        // Arrange
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("NomeUsuario")).thenReturn("user1", "user2");

        // Act
        List<String> usuarios = usuarioDAO.obterUsuarios();

        // Assert
        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        assertEquals("user1", usuarios.get(0));
        assertEquals("user2", usuarios.get(1));
    }

    @Test
    @DisplayName("Deve autenticar usuário com credenciais corretas")
    public void testAutenticarUsuarioCredenciaisCorretas() throws Exception {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Id")).thenReturn(5);
        when(mockResultSet.getString("Senha")).thenReturn("senha123");

        // Act
        int id = usuarioDAO.autenticarUsuario("joao", "senha123");

        // Assert
        assertEquals(5, id);
        verify(mockPreparedStatement).setString(1, "joao");
    }

    @Test
    @DisplayName("Deve retornar 0 para senha incorreta")
    public void testAutenticarUsuarioSenhaIncorreta() throws Exception {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Id")).thenReturn(5);
        when(mockResultSet.getString("Senha")).thenReturn("senha123");

        // Act
        int id = usuarioDAO.autenticarUsuario("joao", "senha_errada");

        // Assert
        assertEquals(0, id);
        verify(mockPreparedStatement).setString(1, "joao");
    }

    @Test
    @DisplayName("Deve retornar -1 para usuário inexistente")
    public void testAutenticarUsuarioInexistente() throws Exception {
        // Arrange
        when(mockResultSet.next()).thenReturn(false);

        // Act
        int id = usuarioDAO.autenticarUsuario("usuario_inexistente", "qualquer_senha");

        // Assert
        assertEquals(-1, id);
        verify(mockPreparedStatement).setString(1, "usuario_inexistente");
    }

    @Test
    @DisplayName("Deve cadastrar usuário corretamente")
    public void testCadastrarUsuario() throws Exception {
        // Arrange
        Usuario usuario = new Usuario("João Silva", "joao", "senha123", "joao@email.com", "999887766");

        // Act
        usuarioDAO.cadastrarUsuario(usuario);

        // Assert
        verify(mockPreparedStatement).setString(1, "João Silva");
        verify(mockPreparedStatement).setString(2, "joao");
        verify(mockPreparedStatement).setString(3, "senha123");
        verify(mockPreparedStatement).setString(4, "joao@email.com");
        verify(mockPreparedStatement).setString(5, "999887766");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    @DisplayName("Deve atualizar usuário corretamente")
    public void testAtualizarUsuario() throws Exception {
        // Arrange
        Usuario usuario = new Usuario(1, "João Silva Atualizado", "joao", "senha456", "joao.novo@email.com", "988776655");

        // Act
        usuarioDAO.atualizarUsuario(usuario);

        // Assert
        verify(mockPreparedStatement).setString(1, "João Silva Atualizado");
        verify(mockPreparedStatement).setString(2, "joao");
        verify(mockPreparedStatement).setString(3, "senha456");
        verify(mockPreparedStatement).setString(4, "joao.novo@email.com");
        verify(mockPreparedStatement).setString(5, "988776655");
        verify(mockPreparedStatement).setInt(6, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    @DisplayName("Deve excluir usuário corretamente")
    public void testExcluirUsuario() throws Exception {
        // Act
        usuarioDAO.excluirUsuario(1);

        // Assert
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    @DisplayName("Deve lidar com SQLException ao obter nome completo")
    public void testObterNomeCompletoComExcecao() throws Exception {
        // Arrange
        doThrow(new SQLException("Erro de conexão")).when(mockPreparedStatement).executeQuery();

        // Act & Assert
        assertThrows(SQLException.class, () -> usuarioDAO.obterNomeCompleto(1));
    }
}
*/
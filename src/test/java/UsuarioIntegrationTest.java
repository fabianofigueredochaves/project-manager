
package com.refact;

import com.refact.dao.UsuarioDAO;
import com.refact.dao.MySqlUsuarioDAO;
import com.refact.factory.DAOFactory;
import com.refact.models.Usuarios;
import com.refact.service.UsuarioService;
import com.refact.util.DatabaseConfig;
import com.refact.util.DatabaseConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testes de integração para o fluxo completo de operações de usuários.
 * Estes testes requerem um banco de dados de teste real (MySQL).
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioIntegrationTest {

    private static UsuarioService usuarioService;
    private static UsuarioDAO usuarioDAO;
    private static DatabaseConnection databaseConnection;
    
    // Usado para armazenar IDs de usuários criados durante os testes
    private static int testUserId;
    private static String testUserName;

    @BeforeAll
    public static void setupBeforeAll() throws Exception {
        System.out.println("Configurando ambiente de teste de integração...");
        
        // Configuração de um banco de dados de teste
        DatabaseConfig testConfig = new DatabaseConfig.Builder()
                .dbName("projectmanager") // Use um banco de dados de teste separado!
                .username("ffc")
                .password("ffc")
                .build();
                
        databaseConnection = DatabaseConnection.getInstance(testConfig);
        
        // Inicializa DAO e Service
        usuarioDAO = new MySqlUsuarioDAO(databaseConnection);
        usuarioService = new UsuarioService(usuarioDAO);
     //  usuarioService = new UsuarioService(DAOFactory.createUsuarioDAO());
        
        // Cria a tabela de usuários para os testes
        usuarioService.inicializarBancoDados();
        
        // Gera um nome de usuário único para testes
        testUserName = "test_user_" + UUID.randomUUID().toString().substring(0, 4);
    }
    
    @AfterAll
    public static void cleanupAfterAll() throws Exception {
        System.out.println("Limpando ambiente de teste...");
        
        try (Connection conn = databaseConnection.getConnection()) {
            // Opcional: Remover o banco de dados de teste ou limpar apenas os dados de teste
            // conn.createStatement().execute("DROP TABLE IF EXISTS Usuarios");
            
            // Ou apenas remover o usuário de teste se ele não foi excluído nos testes
            conn.createStatement().execute("DELETE FROM Usuarios WHERE NomeUsuario LIKE 'test_user_%'");
        }
    }
    
    @Test
    @Order(1)
    @DisplayName("Deve criar um usuário com sucesso")
    public void testCriarUsuario() throws Exception {
        // Arrange
        Usuarios novoUsuario = new Usuarios(
                "Usuário de Teste",
                testUserName,
                "pass123",
                "teste@example.com",
                "11999887766"
        );
        
        // Act
        usuarioService.cadastrar(novoUsuario);
        
        // Assert
        List<String> usuarios = usuarioService.listarNomesUsuarios();
        assertTrue(usuarios.contains(testUserName), "O usuário criado deve estar na lista");
        
        // Buscar o ID do usuário criado para usar em testes subsequentes
        List<Usuarios> todosUsuarios = usuarioService.listarTodosUsuarios();
        for (Usuarios u : todosUsuarios) {
            if (u.getNomeUsuario().equals(testUserName)) {
                testUserId = u.getId();
                break;
            }
        }
        
        assertTrue(testUserId > 0, "ID do usuário deve ser obtido com sucesso");
    }
    
    @Test
    @Order(2)
    @DisplayName("Deve autenticar um usuário com credenciais corretas")
    public void testAutenticarUsuario() throws Exception {
        // Act
        int id = usuarioDAO.autenticarUsuario(testUserName, "pass123");
        
        // Assert
        assertEquals(testUserId, id, "ID retornado na autenticação deve corresponder ao ID do usuário criado");
    }
    
    @Test
    @Order(3)
    @DisplayName("Deve falhar ao autenticar um usuário com senha incorreta")
    public void testAutenticarUsuarioSenhaIncorreta() throws Exception {
        // Act
        int id = usuarioDAO.autenticarUsuario(testUserName, "senha_errada");
        
        // Assert
        assertEquals(0, id, "Autenticação com senha incorreta deve retornar 0");
    }
    
    @Test
    @Order(4)
    @DisplayName("Deve falhar ao autenticar um usuário inexistente")
    public void testAutenticarUsuarioInexistente() throws Exception {
        // Act
        int id = usuarioDAO.autenticarUsuario("usuario_inexistente", "qualquer_senha");
        
        // Assert
        assertEquals(-1, id, "Autenticação de usuário inexistente deve retornar -1");
    }
    
    @Test
    @Order(5)
    @DisplayName("Deve obter o nome completo do usuário pelo ID")
    public void testObterNomeCompleto() throws Exception {
        // Act
        String nomeCompleto = usuarioService.obterNomeCompleto(testUserId);
        
        // Assert
        assertEquals("Usuário de Teste", nomeCompleto, "Nome completo deve corresponder ao valor cadastrado");
    }
    
    @Test
    @Order(6)
    @DisplayName("Deve atualizar os dados do usuário corretamente")
    public void testAtualizarUsuario() throws Exception {
        // Arrange
        Usuarios usuarioAtualizado = new Usuarios(
                testUserId,
                "Nome Atualizado",
                testUserName,
                "pass456",
                "atualizado@example.com",
                "11955443322"
        );
        
        // Act
        usuarioService.atualizar(usuarioAtualizado);
        
        // Busca o usuário atualizado no banco
        List<Usuarios> todosUsuarios = usuarioService.listarTodosUsuarios();
        Usuarios usuarioRecuperado = null;
        for (Usuarios u : todosUsuarios) {
            if (u.getId() == testUserId) {
                usuarioRecuperado = u;
                break;
            }
        }
        
        // Assert
        assertNotNull(usuarioRecuperado, "O usuário deve existir no banco após atualização");
        assertEquals("Nome Atualizado", usuarioRecuperado.getNomeCompleto(), "Nome deve ser atualizado");
        assertEquals("atualizado@example.com", usuarioRecuperado.getEmail(), "Email deve ser atualizado");
        assertEquals("11955443322", usuarioRecuperado.getTelefone(), "Telefone deve ser atualizado");
        
        // Verifica se a autenticação funciona com a nova senha
        int id = usuarioDAO.autenticarUsuario(testUserName, "pass456");
        assertEquals(testUserId, id, "Deve autenticar com a nova senha");
    }
    
    @Test
    @Order(7)
    @DisplayName("Deve excluir o usuário corretamente")
    public void testExcluirUsuario() throws Exception {
        // Act
        usuarioService.remover(testUserId);
        
        // Assert
        List<String> usuarios = usuarioService.listarNomesUsuarios();
        assertFalse(usuarios.contains(testUserName), "Usuário não deve estar mais na lista após exclusão");
        
        // Tenta autenticar o usuário excluído
        int id = usuarioDAO.autenticarUsuario(testUserName, "pass456");
        assertEquals(-1, id, "Não deve ser possível autenticar um usuário excluído");
    }
}
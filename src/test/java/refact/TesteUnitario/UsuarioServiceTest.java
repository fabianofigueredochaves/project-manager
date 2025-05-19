
package refact.TesteUnitario;

import com.refact.dao.UsuarioDAO;
import com.refact.models.Usuarios;
import com.refact.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UsuarioServiceTest {

    public UsuarioService usuarioService;
    public UsuarioDAO mockUsuarioDAO;
    
    //private UsuarioService usuarioService;
    //private UsuarioDAO mockUsuarioDAO;

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
        List<Usuarios> usuarios = Arrays.asList(
            new Usuarios(1, "João Silva", "joao", "senha1", "joao@email.com", "999887766"),
            new Usuarios(2, "Maria Santos", "maria", "senha2", "maria@email.com", "988776655")
        );
        when(mockUsuarioDAO.obterTodosUsuarios()).thenReturn(usuarios);
        
        // Act
        List<Usuarios> resultado = usuarioService.listarTodosUsuarios();
        
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
        
        // Assert
        assertEquals(1, usuarioService.autenticar("joao", "senha123"));      
        verify(mockUsuarioDAO).autenticarUsuario("joao", "senha123");
    }

    @Test
    @DisplayName("Deve rejeitar autenticação com credenciais incorretas")
    public void testAutenticarUsuarioComCredenciaisIncorretas() throws Exception {
        // Arrange
        when(mockUsuarioDAO.autenticarUsuario("joao", "senha_errada")).thenReturn(0);        
   
        // Assert
        assertEquals(0, usuarioService.autenticar("joao", "senha_errada"));   
        verify(mockUsuarioDAO).autenticarUsuario("joao", "senha_errada");
    }

    @Test
    @DisplayName("Deve rejeitar autenticação com usuário inexistente")
    public void testAutenticarUsuarioInexistente() throws Exception {
        // Arrange
        when(mockUsuarioDAO.autenticarUsuario("inexistente", "qualquer")).thenReturn(-1);        
              
        // Assert     
        assertEquals(-1, usuarioService.autenticar("inexistente", "qualquer")); 
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
        Usuarios usuario = new Usuarios("João Silva", "joao", "senha123", "joao@email.com", "999887766");
        
        // Act
        usuarioService.cadastrar(usuario);
        
        // Assert
        verify(mockUsuarioDAO).cadastrarUsuario(usuario);
    }

    @Test
    @DisplayName("Deve atualizar usuário corretamente")
    public void testAtualizarUsuario() throws Exception {
        // Arrange
        Usuarios usuario = new Usuarios(1, "João Silva Atualizado", "joao", "senha123", "joao@email.com", "999887766");
        
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

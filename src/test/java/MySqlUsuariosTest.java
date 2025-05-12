package com.projectmanager.mysql;

import com.projectmanager.models.Usuarios;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


/**
 * Testes de integração para a classe MySqlUsuarios 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // Garante a ordem dos testes
public class MySqlUsuariosTest {

    // IDs gerados pelo banco para os usuários de teste
    private static int idUsuario1;
    private static int idUsuario2;
    private static int idUsuario3;
    
    // Os usuários que serão usados nos testes
    private static Usuarios usuario1;
    private static Usuarios usuario2;
    private static Usuarios usuario3;
    
    private static final List<List<String>> users = new ArrayList<>();
      
    private static final List<String> user1 = new ArrayList<>();        
        
    private static final List<String> user2 = new ArrayList<>();
        
    private static final List<String> user3 = new ArrayList<>();
       
    
    /**
     * Configuração inicial - cria a tabela e limpa dados anteriores
     * @throws java.sql.SQLException
     */
    @BeforeAll
    public static void setupAll() throws SQLException {
       
        user1.add("Fabiano Figueredo Chaves"); //NomeCompleto
        user1.add("fabianofc"); //NomeUsuario
        user1.add("ffc2025"); //Senha
        user1.add("fabianofigueredochaves@gmail.com"); //Email
        user1.add("(31) 98385-3684"); //Telefone
        
        user2.add("Isa Bom Tempo"); //NomeCompleto
        user2.add("isabt"); //NomeUsuario
        user2.add("isa456"); //Senha
        user2.add("isa@gmail.com"); //Email
        user2.add("(11) 93475-5919"); //Telefone
        
        user3.add("Vinícius"); //NomeCompleto
        user3.add("vini"); //NomeUsuario
        user3.add("xyz789"); //Senha
        user3.add("vini@gmail.com"); //Email
        user3.add("(37) 98837-3788"); //Telefone       
        
        users.add(user1);
        users.add(user2);
        users.add(user3);
        
        
        // Garantir que a tabela existe
        MySqlUsuarios.criarTabUsuario();
        
        // Limpar usuários de teste anteriores (se houver)
        limparUsuariosTeste();
        
        // Criar os objetos de usuários para teste
        usuario1 = new Usuarios();
        usuario1.setNomeCompleto(users.get(0).get(0));
        usuario1.setNomeUsuario(users.get(0).get(1));
        usuario1.setSenha(users.get(0).get(2));
        usuario1.setEmail(users.get(0).get(3));
        usuario1.setTelefone(users.get(0).get(4));
        
        usuario2 = new Usuarios();
        usuario2.setNomeCompleto(users.get(1).get(0));
        usuario2.setNomeUsuario(users.get(1).get(1));
        usuario2.setSenha(users.get(1).get(2));
        usuario2.setEmail(users.get(1).get(3));
        usuario2.setTelefone(users.get(1).get(4));
        
        usuario3 = new Usuarios();
        usuario3.setNomeCompleto(users.get(2).get(0));
        usuario3.setNomeUsuario(users.get(2).get(1));
        usuario3.setSenha(users.get(2).get(2));
        usuario3.setEmail(users.get(2).get(3));
        usuario3.setTelefone(users.get(2).get(4));
    }
    
    /**
     * Método auxiliar para limpar usuários de teste
     */
    private static void limparUsuariosTeste() {
        try {
            // Tentar autenticar os usuários de teste para obter seus IDs
            int id1 = MySqlUsuarios.autenticarUsuario(users.get(0).get(1), users.get(0).get(2));
            int id2 = MySqlUsuarios.autenticarUsuario(users.get(1).get(1), users.get(1).get(2));
            int id3 = MySqlUsuarios.autenticarUsuario(users.get(2).get(1), users.get(2).get(2));
            
            // Excluir usuários se existirem
            if (id1 > 0) MySqlUsuarios.excluirUsuario(id1);
            if (id2 > 0) MySqlUsuarios.excluirUsuario(id2);
            if (id3 > 0) MySqlUsuarios.excluirUsuario(id3);
        } catch (SQLException e) {
            System.err.println("Erro ao limpar usuários de teste: " + e.getMessage());
        }
    }
    
    /**
     * Limpar após todos os testes
     */
    @AfterAll
    public static void tearDownAll() {
        try {
            // Excluir usuários de teste
            if (idUsuario1 > 0) MySqlUsuarios.excluirUsuario(idUsuario1);
            if (idUsuario2 > 0) MySqlUsuarios.excluirUsuario(idUsuario2);
            if (idUsuario3 > 0) MySqlUsuarios.excluirUsuario(idUsuario3);
        } catch (SQLException e) {
            System.err.println("Erro ao limpar após testes: " + e.getMessage());
        }
    }
    
    /**
     * Teste 1: Cadastrar os três usuários de teste
     */
    @Test
    @Order(1)
    public void testCadastrarTresUsuarios() {
        System.out.println("Teste 1: Cadastrando três usuários");
        
        // Cadastrar os três usuários
        MySqlUsuarios.cadastrarUsuario(usuario1);
        MySqlUsuarios.cadastrarUsuario(usuario2);
        MySqlUsuarios.cadastrarUsuario(usuario3);
        
        try {
            // Verificar se foram cadastrados através da autenticação
            idUsuario1 = MySqlUsuarios.autenticarUsuario(users.get(0).get(1), users.get(0).get(2));
            idUsuario2 = MySqlUsuarios.autenticarUsuario(users.get(1).get(1), users.get(1).get(2));
            idUsuario3 = MySqlUsuarios.autenticarUsuario(users.get(2).get(1), users.get(2).get(2));
            
            // Verificar se os IDs são válidos
            assertTrue(idUsuario1 > 0, "Usuário 1 deveria ser cadastrado e autenticado com sucesso");
            assertTrue(idUsuario2 > 0, "Usuário 2 deveria ser cadastrado e autenticado com sucesso");
            assertTrue(idUsuario3 > 0, "Usuário 3 deveria ser cadastrado e autenticado com sucesso");
            
            // Atualizar os IDs nos objetos
            usuario1.setId(idUsuario1);
            usuario2.setId(idUsuario2);
            usuario3.setId(idUsuario3);
            
            System.out.println("Usuários cadastrados com IDs: " + idUsuario1 + ", " + idUsuario2 + ", " + idUsuario3);
        } catch (SQLException e) {
            fail("Exceção ao tentar autenticar usuários: " + e.getMessage());
        }
    }
    
    /**
     * Teste 2: Autenticar os três usuários
     */
    @Test
    @Order(2)
    public void testAutenticarTresUsuarios() {
        System.out.println("Teste 2: Autenticando três usuários");
        
        try {
            // Autenticar com credenciais corretas
            int auth1 = MySqlUsuarios.autenticarUsuario(users.get(0).get(1), users.get(0).get(2));
            int auth2 = MySqlUsuarios.autenticarUsuario(users.get(1).get(1), users.get(1).get(2));
            int auth3 = MySqlUsuarios.autenticarUsuario(users.get(2).get(1), users.get(2).get(2));
            
            // Verificar autenticações bem sucedidas
            assertEquals(idUsuario1, auth1, "Autenticação do usuário 1 falhou");
            assertEquals(idUsuario2, auth2, "Autenticação do usuário 2 falhou");
            assertEquals(idUsuario3, auth3, "Autenticação do usuário 3 falhou");
            
            // Autenticar com senha incorreta
            int authFalha1 = MySqlUsuarios.autenticarUsuario(users.get(0).get(1), "senhaerrada");
            assertEquals(0, authFalha1, "Autenticação com senha errada deveria falhar");
            
            // Autenticar com usuário inexistente
            int authFalha2 = MySqlUsuarios.autenticarUsuario("usuario_inexistente", users.get(0).get(2));
            assertEquals(-1, authFalha2, "Autenticação com usuário inexistente deveria retornar -1");
            
        } catch (SQLException e) {
            fail("Exceção ao tentar autenticar usuários: " + e.getMessage());
        }
    }
    
    /**
     * Teste 3: Obter nome completo dos três usuários
     */
    @Test
    @Order(3)
    public void testObterNomeCompletoDeTresUsuarios() {
        System.out.println("Teste 3: Obtendo nome completo de três usuários");
        
        try {
            // Obter nome completo dos três usuários
            String nome1 = MySqlUsuarios.obterNomeCompleto(idUsuario1);
            String nome2 = MySqlUsuarios.obterNomeCompleto(idUsuario2);
            String nome3 = MySqlUsuarios.obterNomeCompleto(idUsuario3);
            
            // Verificar se os nomes foram obtidos corretamente
            assertEquals(users.get(0).get(0), nome1, "Nome do usuário 1 incorreto");
            assertEquals(users.get(1).get(0), nome2, "Nome do usuário 2 incorreto");
            assertEquals(users.get(2).get(0), nome3, "Nome do usuário 3 incorreto");
            
            // Tentar obter nome de usuário inexistente
            String nomeInexistente = MySqlUsuarios.obterNomeCompleto(99999);
            assertNull(nomeInexistente, "Nome de usuário inexistente deveria retornar null");
            
        } catch (SQLException e) {
            fail("Exceção ao obter nome completo: " + e.getMessage());
        }
    }
    
    /**
     * Teste 4: Obter lista de usuários
     */
    @Test
    @Order(4)
    public void testObterListaUsuarios() {
        System.out.println("Teste 4: Obtendo lista de usuários");
        
        try {
            // Obter lista de usuários
            List<String> usuarios = MySqlUsuarios.obterUsuarios();
            
            // Verificar se a lista não é nula
            assertNotNull(usuarios, "A lista de usuários não deve ser nula");
            
            // Verificar se os usuários de teste estão na lista
            boolean temUser1 = usuarios.contains(users.get(0).get(1));
            boolean temUser2 = usuarios.contains(users.get(1).get(1));
            boolean temUser3 = usuarios.contains(users.get(2).get(1));
            
            assertTrue(temUser1, "Usuário " + users.get(0).get(1) + " deveria estar na lista");
            assertTrue(temUser2, "Usuário " + users.get(1).get(1) + " deveria estar na lista");
            assertTrue(temUser3, "Usuário " + users.get(2).get(1) + " deveria estar na lista");
            
        } catch (SQLException e) {
            fail("Exceção ao obter lista de usuários: " + e.getMessage());
        }
    }
    
    /**
     * Teste 5: Obter todas as colunas dos usuários
     */
    @Test
    @Order(5)
    public void testObterTodasColunas() {
        System.out.println("Teste 5: Obtendo todas as colunas dos usuários");
        
        try {
            // Obter todos os dados dos usuários
            List<Usuarios> todosUsuarios = MySqlUsuarios.getAllColuns();
            
            // Verificar se a lista não é nula
            assertNotNull(todosUsuarios, "A lista de usuários não deve ser nula");
            
            // Encontrar nossos usuários de teste na lista
            Usuarios usuarioEncontrado1 = null;
            Usuarios usuarioEncontrado2 = null;
            Usuarios usuarioEncontrado3 = null;
            
            for (Usuarios u : todosUsuarios) {
                if (u.getId() == idUsuario1) usuarioEncontrado1 = u;
                if (u.getId() == idUsuario2) usuarioEncontrado2 = u;
                if (u.getId() == idUsuario3) usuarioEncontrado3 = u;
            }
            
            // Verificar se os três usuários foram encontrados
            assertNotNull(usuarioEncontrado1, "Usuário 1 não encontrado na lista completa");
            assertNotNull(usuarioEncontrado2, "Usuário 2 não encontrado na lista completa");
            assertNotNull(usuarioEncontrado3, "Usuário 3 não encontrado na lista completa");
            
            // Verificar dados do usuário 1
            assertEquals(users.get(0).get(0), usuarioEncontrado1.getNomeCompleto());
            assertEquals(users.get(0).get(1), usuarioEncontrado1.getNomeUsuario());
            assertEquals(users.get(0).get(2), usuarioEncontrado1.getSenha());
            assertEquals(users.get(0).get(3), usuarioEncontrado1.getEmail());
            assertEquals(users.get(0).get(4), usuarioEncontrado1.getTelefone());
            
            // Verificar dados do usuário 2
            assertEquals(users.get(1).get(0), usuarioEncontrado2.getNomeCompleto());
            assertEquals(users.get(1).get(1), usuarioEncontrado2.getNomeUsuario());
            
            // Verificar dados do usuário 3
            assertEquals(users.get(2).get(0), usuarioEncontrado3.getNomeCompleto());
            assertEquals(users.get(2).get(1), usuarioEncontrado3.getNomeUsuario());
            
        } catch (SQLException e) {
            fail("Exceção ao obter todas colunas: " + e.getMessage());
        }
    }
    
    /**
     * Teste 6: Atualizar os três usuários
     */
    @Test
    @Order(6)
    public void testAtualizarTresUsuarios() {
        System.out.println("Teste 6: Atualizando três usuários");
        
        try {
            // Modificar dados dos usuários
            usuario1.setNomeCompleto(users.get(0).get(0) + " Atualizado");
            usuario1.setEmail("usuario_" + users.get(0).get(3));
            
            usuario2.setNomeCompleto(users.get(1).get(0) + " Atualizado");
            usuario2.setEmail("usuario_" + users.get(1).get(3));
            
            usuario3.setNomeCompleto(users.get(2).get(0) + " Atualizado");
            usuario3.setEmail("usuario_" + users.get(2).get(3));
            
            // Atualizar os usuários no banco
            MySqlUsuarios.atualizarUsuario(usuario1);
            MySqlUsuarios.atualizarUsuario(usuario2);
            MySqlUsuarios.atualizarUsuario(usuario3);
            
            // Verificar se as atualizações foram feitas
            String nome1 = MySqlUsuarios.obterNomeCompleto(idUsuario1);
            String nome2 = MySqlUsuarios.obterNomeCompleto(idUsuario2);
            String nome3 = MySqlUsuarios.obterNomeCompleto(idUsuario3);
            
            assertEquals(((users.get(0).get(0) + " Atualizado")), nome1, "Nome do usuário 1 não foi atualizado");
            assertEquals((users.get(1).get(0) + " Atualizado"), nome2, "Nome do usuário 2 não foi atualizado");
            assertEquals((users.get(2).get(0) + " Atualizado"), nome3, "Nome do usuário 3 não foi atualizado");
            
            // Verificar outros campos atualizados através do getAllColuns
            List<Usuarios> todosUsuarios = MySqlUsuarios.getAllColuns();
            
            for (Usuarios u : todosUsuarios) {
                if (u.getId() == idUsuario1) {
                    assertEquals(("usuario_" + users.get(0).get(3)), u.getEmail(), "Email do usuário 1 não foi atualizado");
                }
                if (u.getId() == idUsuario2) {
                    assertEquals(("usuario_" + users.get(1).get(3)), u.getEmail(), "Email do usuário 2 não foi atualizado");
                }
                if (u.getId() == idUsuario3) {
                    assertEquals(("usuario_" + users.get(2).get(3)), u.getEmail(), "Email do usuário 3 não foi atualizado");
                }
            }
            
        } catch (SQLException e) {
            fail("Exceção ao atualizar usuários: " + e.getMessage());
        }
    }
}
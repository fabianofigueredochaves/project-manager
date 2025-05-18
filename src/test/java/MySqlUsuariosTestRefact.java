//package com.refact;
package com.projectmanager.mysql;

import com.refact.models.Usuarios;
import com.refact.controller.UsuarioController;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

/**
 * Testes de integração para a classe MySqlUsuarios
 * Estes testes usam um banco de dados MySQL real.
 * Os usuários para teste são carregados de uma lista estática.
 * 
 * IMPORTANTE: Execute estes testes em um banco de dados de teste, nunca em produção!
 */
@WebMvcTest(UsuarioController.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // Garante a ordem dos testes
public class MySqlUsuariosTestRefact {

    // Lista de usuários carregados da estrutura de dados
    private static List<Usuarios> usuariosTeste;
    
    // Mapa para armazenar os IDs gerados pelo banco para cada usuário de teste
    private static Map<String, Integer> idsUsuarios;
    
    // Dados dos usuários para teste - usando lista de listas
    private static final List<List<String>> DADOS_USUARIOS = Arrays.asList(
        // Cada sub-lista representa: [nomeCompleto, nomeUsuario, senha, email, telefone]
        Arrays.asList("Fabiano Figueredo Chaves", "ffc", "ffc2025", "fabianofigueredochaves@gmail.com", "(31) 98385-3684"),
        Arrays.asList("Isabelly Bom Tempo Ribeiro", "ibtr", "isa456", "isa@gmail.com", "(11) 93475-5919"),
        Arrays.asList("Vinícius Souza Reis", "vsr", "xyz789", "vinisourei@gmail.com ", "(37) 98837-3788")
    //  Arrays.asList("AAA", "aaa", "abcd", "zzz@teste.com", "(11) 93333-4444")
    );
   // private final UsuarioController usuarioController;
    
    
    public UsuarioController usuarioController;

    public MySqlUsuariosTestRefact() {
        this.usuarioController = new UsuarioController();
         usuarioController.inicializarBanco();
    }
    
  //  public MySqlUsuariosTestRefact()
  //  {
 //       this.usuarioController = new UsuarioController();
 //   }
   // public UsuarioController usuarioController;// = new UsuarioController();
    
    /**
     * Configuração inicial - cria a tabela e carrega usuários dos dados estáticos
     * @throws java.sql.SQLException
     */
    @BeforeAll
    public static void setupAll() {
        System.out.println("Iniciando testes com usuários da lista estática");
        
        // Inicializar coleções
        usuariosTeste = new ArrayList<>();
        idsUsuarios = new HashMap<>();
        
        // Carregar usuários da estrutura de dados
        carregarUsuariosDaLista();
        
        // Garantir que a tabela existe
       // MySqlUsuarios.criarTabUsuario();
      // UsuarioController usuarioController = new UsuarioController();
      
       //UsuarioController usuarioController = new UsuarioController();
      
      
        
        // Limpar usuários de teste anteriores (se houver)
       // limparUsuariosTeste();
    }
    
    /**
     * Método para carregar usuários da estrutura de dados
     */
    private static void carregarUsuariosDaLista() {
        try {
            // Iterar sobre cada lista de dados de usuário
            for (List<String> dadosUsuario : DADOS_USUARIOS) {
                Usuarios usuario = new Usuarios();
                usuario.setNomeCompleto(dadosUsuario.get(0));
                usuario.setNomeUsuario(dadosUsuario.get(1));
                usuario.setSenha(dadosUsuario.get(2));
                usuario.setEmail(dadosUsuario.get(3));
                usuario.setTelefone(dadosUsuario.get(4));
                
                usuariosTeste.add(usuario);
                
                System.out.println("Usuário carregado da lista: " + usuario.getNomeUsuario());
            }
            
            System.out.println("Total de usuários carregados: " + usuariosTeste.size());
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar usuários da lista: " + e.getMessage());
            fail("Erro ao carregar usuários da lista: " + e.getMessage());
        }
    }
   
    
   //  * Método auxiliar para limpar usuários de teste anteriores
    
    private void limparUsuariosTeste() {
        System.out.println("Limpando usuários de teste anteriores...");
        
        // Para cada usuário na lista, verificar se existe e excluir
        for (Usuarios usuario : usuariosTeste) {
            // Tentar autenticar para obter ID
            
            int id = usuarioController.autenticarUsuario(
                    usuario.getNomeUsuario(),
                    usuario.getSenha());
            
            // Excluir se existir
            if (id > 0) {
                System.out.println("Excluindo usuário existente: " + usuario.getNomeUsuario() + " (ID: " + id + ")");
                usuarioController.excluirUsuario(id);
            }
        }
    }
    
    
  //  * Limpar após todos os testes
     
    @AfterAll
    public static void tearDownAll() {
        System.out.println("Finalizando testes - limpando usuários criados");
        
        // Excluir todos os usuários de teste que foram cadastrados
        for (int id : idsUsuarios.values()) {
            if (id > 0) {
                System.out.println("Excluindo usuário com ID: " + id);
               // usuarioController.excluirUsuario(id);
            }
        }
    }
    
   
    
    
   //  * Teste 1: Cadastrar todos os usuários carregados da lista
 
  }
   /*  
    
    @Test
    @Order(1)
    public void testCadastrarUsuarios() {
        System.out.println("\nTeste 1: Cadastrando " + usuariosTeste.size() + " usuários");
        
        // Cadastrar cada usuário da lista
        for (Usuarios usuario : usuariosTeste) {
         //   usuarioController.cadastrarUsuario(usuario);
            System.out.println("Usuário cadastrado: " + usuario.getNomeUsuario());
        }
        
        // Verificar se foram cadastrados através da autenticação
        for (Usuarios usuario : usuariosTeste) {
         //   int id = usuarioController.autenticarUsuario(
          //          usuario.getNomeUsuario(),
         //           usuario.getSenha());
            
            // Verificar se o ID é válido
  //          assertTrue(id > 0, "Usuário " + usuario.getNomeUsuario() + " deveria ser cadastrado e autenticado com sucesso");
            
            // Guardar o ID para uso em outros testes
            idsUsuarios.put(usuario.getNomeUsuario(), id);
            usuario.setId(id);
            
            System.out.println("Usuário " + usuario.getNomeUsuario() + " autenticado com ID: " + id);
        }
    }
    
 
  
    
   // * Teste 2: Autenticar todos os usuários
     
    @Test
    @Order(2)
    public void testAutenticarUsuarios() {
        System.out.println("\nTeste 2: Autenticando " + usuariosTeste.size() + " usuários");
        
        try {
            // Para cada usuário, testar autenticação com senha correta
            for (Usuarios usuario : usuariosTeste) {
   //             int id = MySqlUsuarios.autenticarUsuario(
   //                     usuario.getNomeUsuario(), 
   //                     usuario.getSenha());
                
//                assertEquals(idsUsuarios.get(usuario.getNomeUsuario()), id, 
  //                      "Autenticação do usuário " + usuario.getNomeUsuario() + " falhou");
                
                System.out.println("Autenticação bem-sucedida: " + usuario.getNomeUsuario());
                
                // Testar também com senha incorreta
  //              int authFalha = MySqlUsuarios.autenticarUsuario(
  //                      usuario.getNomeUsuario(), 
  //                      "senhaerrada_" + usuario.getNomeUsuario());
                
  //              assertEquals(0, authFalha, 
  //                      "Autenticação com senha errada para " + usuario.getNomeUsuario() + " deveria falhar");
            }
            
            // Testar com usuário inexistente
  //          int authFalha = MySqlUsuarios.autenticarUsuario("usuario_que_nao_existe", "qualquersenha");
  //          assertEquals(-1, authFalha, "Autenticação com usuário inexistente deveria retornar -1");
            
        } catch (SQLException e) {
            fail("Exceção ao tentar autenticar usuários: " + e.getMessage());
        }
    }
    
    
   //  * Teste 3: Obter nome completo de todos os usuários
    
    @Test
    @Order(3)
    public void testObterNomeCompletoUsuarios() {
        System.out.println("\nTeste 3: Obtendo nome completo de " + usuariosTeste.size() + " usuários");
        
        try {
            // Para cada usuário, verificar se o nome completo é retornado corretamente
            for (Usuarios usuario : usuariosTeste) {
                int id = idsUsuarios.get(usuario.getNomeUsuario());
     //           String nomeObtido = MySqlUsuarios.obterNomeCompleto(id);
                
     //           assertEquals(usuario.getNomeCompleto(), nomeObtido, 
     //                   "Nome completo de " + usuario.getNomeUsuario() + " incorreto");
                
     //           System.out.println("Nome completo obtido para " + usuario.getNomeUsuario() + ": " + nomeObtido);
            }
            
            // Testar com ID inexistente
     //       String nomeInexistente = MySqlUsuarios.obterNomeCompleto(99999);
     //       assertNull(nomeInexistente, "Nome de usuário inexistente deveria retornar null");
            
        } catch (SQLException e) {
            fail("Exceção ao obter nome completo: " + e.getMessage());
        }
    }
    
   
    // * Teste 4: Obter lista de usuários
   
    @Test
    @Order(4)
    public void testObterListaUsuarios() {
        System.out.println("\nTeste 4: Obtendo lista de usuários");
        
        try {
            // Obter lista de usuários
    //        List<String> listaUsuarios = MySqlUsuarios.obterUsuarios();
            
            // Verificar se a lista não é nula
    //        assertNotNull(listaUsuarios, "A lista de usuários não deve ser nula");
            
            // Verificar se cada usuário de teste está na lista
            for (Usuarios usuario : usuariosTeste) {
   //             boolean usuarioEncontrado = listaUsuarios.contains(usuario.getNomeUsuario());
   //             assertTrue(usuarioEncontrado, 
   //                     "Usuário '" + usuario.getNomeUsuario() + "' deveria estar na lista");
                
   //            if (usuarioEncontrado) {
   //                 System.out.println("Usuário encontrado na lista: " + usuario.getNomeUsuario());
   //             }
            }
            
        } catch (SQLException e) {
            fail("Exceção ao obter lista de usuários: " + e.getMessage());
        }
    }
    
    
    // * Teste 5: Obter todas as colunas dos usuários
   
    @Test
    @Order(5)
    public void testObterTodasColunas() {
        System.out.println("\nTeste 5: Obtendo todas as colunas dos usuários");
        
        try {
            // Obter todos os dados dos usuários
     //       List<Usuarios> todosUsuarios = MySqlUsuarios.getAllColuns();
            
            // Verificar se a lista não é nula
     //       assertNotNull(todosUsuarios, "A lista de usuários não deve ser nula");
            
            // Para cada usuário de teste, verificar se seus dados estão corretos
            for (Usuarios usuarioTeste : usuariosTeste) {
                int idUsuario = idsUsuarios.get(usuarioTeste.getNomeUsuario());
                
                // Procurar o usuário na lista completa
                Usuarios usuarioEncontrado = null;
                for (Usuarios u : todosUsuarios) {
                    if (u.getId() == idUsuario) {
                        usuarioEncontrado = u;
                        break;
                    }
                }
                
                // Verificar se o usuário foi encontrado
                assertNotNull(usuarioEncontrado, 
                        "Usuário " + usuarioTeste.getNomeUsuario() + " não encontrado na lista completa");
                
                if (usuarioEncontrado != null) {
                    // Verificar dados do usuário
                    assertEquals(usuarioTeste.getNomeCompleto(), usuarioEncontrado.getNomeCompleto());
                    assertEquals(usuarioTeste.getNomeUsuario(), usuarioEncontrado.getNomeUsuario());
                    assertEquals(usuarioTeste.getSenha(), usuarioEncontrado.getSenha());
                    assertEquals(usuarioTeste.getEmail(), usuarioEncontrado.getEmail());
                    assertEquals(usuarioTeste.getTelefone(), usuarioEncontrado.getTelefone());
                    
                    System.out.println("Verificado dados completos de: " + usuarioTeste.getNomeUsuario());
                }
            }
            
        } catch (SQLException e) {
            fail("Exceção ao obter todas colunas: " + e.getMessage());
        }
    }
    
    
    // * Teste 6: Atualizar todos os usuários
    
    @Test
    @Order(6)
    public void testAtualizarUsuarios() {
        System.out.println("\nTeste 6: Atualizando " + usuariosTeste.size() + " usuários");
        
        try {
            // Para cada usuário, modificar seus dados e atualizar
            for (Usuarios usuario : usuariosTeste) {
                // Obter ID do usuário
                int id = idsUsuarios.get(usuario.getNomeUsuario());
                usuario.setId(id);
                
                // Modificar dados
                String novoNome = usuario.getNomeCompleto() + " Atualizado";
                String novoEmail = usuario.getNomeUsuario() + "_atualizado@teste.com";
                
                usuario.setNomeCompleto(novoNome);
                usuario.setEmail(novoEmail);
                
                // Atualizar no banco
   //             MySqlUsuarios.atualizarUsuario(usuario);
                System.out.println("Atualizando " + usuario.getNomeUsuario() + " com novo nome: " + novoNome);
            }
            
            // Verificar se as atualizações foram feitas
            for (Usuarios usuario : usuariosTeste) {
                int id = idsUsuarios.get(usuario.getNomeUsuario());
                
                // Verificar nome atualizado
   //             String nomeObtido = MySqlUsuarios.obterNomeCompleto(id);
   //             assertEquals(usuario.getNomeCompleto(), nomeObtido, 
    //                    "Nome do usuário " + usuario.getNomeUsuario() + " não foi atualizado");
                
   //             System.out.println("Nome atualizado verificado para " + usuario.getNomeUsuario() + ": " + nomeObtido);
            }
            
            // Verificar outros campos atualizados usando getAllColuns
   //         List<Usuarios> todosUsuarios = MySqlUsuarios.getAllColuns();
            
            for (Usuarios usuarioTeste : usuariosTeste) {
                int id = idsUsuarios.get(usuarioTeste.getNomeUsuario());
                
                // Procurar o usuário atualizado
   //             for (Usuarios u : todosUsuarios) {
     //               if (u.getId() == id) {
     //                   assertEquals(usuarioTeste.getEmail(), u.getEmail(), 
     //                           "Email do usuário " + usuarioTeste.getNomeUsuario() + " não foi atualizado");
     //                   break;
     //               }
     //           }

            }
            
        } catch (SQLException e) {
            fail("Exceção ao atualizar usuários: " + e.getMessage());
        }
    }
}

*/
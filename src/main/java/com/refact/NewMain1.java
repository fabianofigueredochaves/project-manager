package com.refact;

//import com.projectmanager.models.*;
import com.refact.frms.FrmLogin1;
import com.refact.controller.UsuarioController;
import com.refact.models.Usuarios;

//import com.projectmanager.mysql.MySqlProjetos;
//import com.projectmanager.mysql.MySqlRequisitos;
//import com.projectmanager.mysql.MySqlUsuarios;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.SwingUtilities;


public class NewMain1 {

   
    public static FrmLogin1 login;
    
    /**
     *
     * @param args
     * @throws java.sql.SQLException */
    //public static UsuarioService start;
    
    
    
    public static void main(String[] args) throws SQLException, Exception {                   
       
        System.out.println("Conexão com MySql");
      
    //    MySqlUsuarios.criarTabUsuario();
    
        UsuarioController usuarioController = new UsuarioController();
        
        usuarioController.inicializarBanco();
        
        demonstrarOperacoesUsuario(usuarioController);
        
  //      SwingUtilities.invokeLater(() -> {
  //      login = new FrmLogin1(usuarioController);
  //      login.setVisible(true);   });
    
    //    start.inicializarBancoDados();
        
      //  MySqlProjetos.criarTabProjetos();
      
      //  MySqlRequisitos.criarTabRequisitos();
     
     //   login = new FrmLogin();       
      
     //   login.setVisible(true);        
             
    }
    


private static void demonstrarOperacoesUsuario(UsuarioController usuarioController) {
        try {
            Scanner scanner = new Scanner(System.in);
            
            // Menu simples para demonstração
            boolean sair = false;
            while (!sair) {
                System.out.println("\n==== SISTEMA DE GERENCIAMENTO DE PROJETOS ====");
                System.out.println("1. Cadastrar novo usuário");
                System.out.println("2. Listar todos usuários");
                System.out.println("3. Atualizar usuário");
                System.out.println("4. Excluir usuário");
                System.out.println("5. Autenticar usuário");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha
                
                switch (opcao) {
                    case 1:
                        cadastrarNovoUsuario(scanner, usuarioController);
                        break;
                    case 2:
                        listarUsuarios(usuarioController);
                        break;
                    case 3:
                //        atualizarUsuario(scanner, usuarioController);
                        break;
                    case 4:
                //        excluirUsuario(scanner, usuarioController);
                        break;
                    case 5:
                //        autenticarUsuario(scanner, usuarioController);
                        break;
                    case 0:
                        sair = true;
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            
            scanner.close();
            
        } catch (Exception e) {
           // LOGGER.log(Level.SEVERE, "Erro nas operações de demonstração", e);
            System.err.println("Erro nas operações de demonstração: " + e.getMessage());
        }
    }


private static void listarUsuarios(UsuarioController usuarioController) {
        System.out.println("\n== LISTAGEM DE USUÁRIOS ==");
        
        List<Usuarios> usuarios = usuarioController.listarTodosUsuarios();
        
        if (usuarios != null && !usuarios.isEmpty()) {
            System.out.println("ID | Nome Completo | Nome Usuário | Email | Telefone");
            System.out.println("--------------------------------------------------");
            
            for (Usuarios usuario : usuarios) {
                System.out.printf("%d | %s | %s | %s | %s%n", 
                        usuario.getId(), 
                        usuario.getNomeCompleto(), 
                        usuario.getNomeUsuario(),
                        usuario.getEmail(),
                        usuario.getTelefone());
            }
        } else {
            System.out.println("Nenhum usuário encontrado.");
        }
    }

private static void cadastrarNovoUsuario(Scanner scanner, UsuarioController usuarioController) {
        System.out.println("\n== CADASTRO DE NOVO USUÁRIO ==");
        
        System.out.print("Nome completo: ");
        String nomeCompleto = scanner.nextLine();
        
        System.out.print("Nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
//        usuarioController.cadastrarUsuario(nomeCompleto, nomeUsuario, senha, email, telefone);
    }


}


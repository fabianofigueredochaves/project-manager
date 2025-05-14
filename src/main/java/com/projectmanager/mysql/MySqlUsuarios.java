package com.projectmanager.mysql;

import com.projectmanager.models.Usuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MySqlUsuarios {       
   
      
    public static void criarTabUsuario() throws SQLException {
       
        Connection cnx = null;
        
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
                                    
            System.out.println("Verificando e criando a tabela Usuarios ...");
            
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Usuarios("
                    + "Id INT NOT NULL AUTO_INCREMENT,"
                    + "NomeCompleto VARCHAR(64) NOT NULL,"
                    + "NomeUsuario VARCHAR(16) UNIQUE NOT NULL,"
                    + "Senha VARCHAR(8) NOT NULL,"                    
                    + "Email VARCHAR(64),"
                    + "Telefone VARCHAR(16),"
                    + "PRIMARY KEY(Id));");
            
            System.out.println("Tabela Usuários criada!");           
            
            cnx.close();
        
        }
        
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
        }
               
    }  
    
    public static String obterNomeCompleto(int Id) throws SQLException {
        
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();
        
        String nc = null;
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cosultando nome do usuario ...");
            
            ResultSet rs = st.executeQuery("SELECT * FROM Usuarios WHERE Id = '" + Id +"'");                       
            
            System.out.println("Ok! +");   
            
            if(rs != null && rs.next()){
               nc = rs.getString("NomeCompleto");
            }
            
            cnx.close();
            
            return nc;
            
        }
        
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
        }

        return null;             
        
    }   
     
    public static List<String> obterUsuarios() throws SQLException {     
         
        List<String> usuariosObtidos = new ArrayList<>();
             
        String user = null;
               
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cosultando usuario ...");
            
            ResultSet rs = st.executeQuery("SELECT Id, NomeCompleto, NomeUsuario FROM Usuarios ORDER BY NomeUsuario ASC;");
            
            System.out.println("Ok! +");         
            
            while (rs.next())
            {  
                
                user = rs.getString("NomeUsuario");                
                usuariosObtidos.add(user); 
                
            }
            
            cnx.close();          
            
            return usuariosObtidos;
            
        }
        
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
        }        
        
        return null;
        
    }
        
    public static List<Usuarios> getAllColuns() throws SQLException {
         
        List<Usuarios> allColumnsUsuarios = new ArrayList<>();
       
        Usuarios user=null;
        
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cosultando usuario ...");
            
            ResultSet rs = st.executeQuery("SELECT * FROM Usuarios ORDER BY NomeUsuario ASC;");
            
            System.out.println("Ok! +");
                                                            
            while (rs.next())
            {
              
                user = new Usuarios();
                
                user.setId(rs.getInt("Id"));
                user.setNomeCompleto(rs.getString("NomeCompleto"));
                user.setNomeUsuario(rs.getString("NomeUsuario"));
                user.setSenha(rs.getString("Senha"));
                user.setEmail(rs.getString("Email"));
                user.setTelefone(rs.getString("Telefone"));                                            
                
                allColumnsUsuarios.add(user);
                
            }           
            
            cnx.close();
            
            return allColumnsUsuarios;
            
        }
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
        }
        
        return null;
        
    }
        
    public static int autenticarUsuario(String usuario, String senha) throws SQLException{
         
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();     
        
        System.out.println("Autenticando usuario ...");
       
        try
        {
            
            Statement st = cnx.createStatement(); 
                       
            ResultSet rs = st.executeQuery("SELECT * FROM Usuarios WHERE NomeUsuario = '" + usuario +"'");
            
            if(rs != null && rs.next()){
            
                int Id = rs.getInt("Id");
                
                String passwd = rs.getString("Senha");
                                
                cnx.close();              
                           
                if(senha.equals(passwd))
                    
                    return Id;
                
                else 
                   
                    return 0;
                
            }
                          
        }
        
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());            
        }       
        
        return -1;
               
    }
    
    public static void cadastrarUsuario(Usuarios usuario){
    
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();
                    
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cadastrando usuario ...");
            
            st.executeUpdate("INSERT INTO Usuarios (NomeCompleto, NomeUsuario, Senha, Email, Telefone) "
                     +"VALUES ('"+ usuario.getNomeCompleto() +"', '"+ usuario.getNomeUsuario() +"', '"+ usuario.getSenha()
                     +"', '"+ usuario.getEmail() +"', '"+ usuario.getTelefone() +"')");
            
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
            
            cnx.close();
           
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar usuário!");
            
            System.err.println(e.getMessage());
        }
    
    }
    
    public static void atualizarUsuario(Usuarios usuario){
       
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();      
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Atualizando cadastro do usuario ...");
            
            st.executeUpdate("UPDATE Usuarios SET NomeCompleto = '" + usuario.getNomeCompleto() +
                                                "', NomeUsuario = '" + usuario.getNomeUsuario() +
                                                "', Senha = '" + usuario.getSenha() +                                                
                                                "', Email = '" + usuario.getEmail() +
                                                "', Telefone = '" + usuario.getTelefone() +
                                                "' WHERE Id = '" + usuario.getId() + "'");
                                        
            JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
            
            cnx.close();
           
        }
        
        catch (SQLException e)
        {                  
            JOptionPane.showMessageDialog(null, "Falha ao atualizar usuário!");
             
            System.err.println(e.getMessage());
        }
        
    }
    
    public static void excluirUsuario(int Id) throws SQLException{
        
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();
                    
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Excluindo cadastro do usuario ...");
            
            st.executeUpdate("DELETE FROM Usuarios WHERE Id = " + Id);
                                        
            System.out.println("Usuario excluido!");
            
            JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");
            
            cnx.close();
            
        }    
        catch (SQLException e)
        {                          
             JOptionPane.showMessageDialog(null, "Falha ao excluir usuário!");
             
             System.err.println(e.getMessage());
        }    
                   
    }
        
}
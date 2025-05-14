package com.projectmanager.mysql;

import com.projectmanager.models.Projetos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MySqlProjetos {
          
    public static void criarTabProjetos() throws SQLException {
       
        Connection cnx = null;
        
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
                                    
            System.out.println("Verificando e criando a tabela Projetos ...");
            
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Projetos("
                    + "IdProj INT NOT NULL AUTO_INCREMENT,"
                    + "Nome VARCHAR(64) NOT NULL,"
                    + "Descriçao VARCHAR(64) NOT NULL,"
                    + "Proprietario INT NOT NULL,"          
                    + "Versao VARCHAR(16) NOT NULL,"
                    + "Status VARCHAR(16) NOT NULL,"
                    + "PRIMARY KEY(IdProj))");                        
                    
            System.out.println("Tabela Projetos criada!");
            
            cnx.close();
        
        }
        
        catch (Exception e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
        }
               
    }
    
    public static void cadastrarProjeto(Projetos projeto){
    
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();
                    
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cadastrando projeto ...");
            
            st.executeUpdate("INSERT INTO Projetos (Nome, Descriçao, Proprietario, Versao, Status) "
                    +"VALUES ('"+ projeto.getNomeProj()
                                + "', '"+ projeto.getDescriçao()
                                +"', '"+ projeto.getProprietario() 
                                +"', '"+ projeto.getVersao()
                                +"', '"+ projeto.getStatus() +"')");
            
            JOptionPane.showMessageDialog(null, "Projeto cadastrado com sucesso!");
            
            cnx.close();
           
        }
        
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, "Falha ao cadastrar projeto!");
             System.err.println(e.getMessage());
        }
            
    } 
    
    public static void atualizarProjeto(Projetos projeto){
       
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();        
               
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Atualizando cadastro de projetos ...");
            
            st.executeUpdate("UPDATE Projetos SET Nome = '" + projeto.getNomeProj() +
                                                "', Descriçao = '" + projeto.getDescriçao() +
                                                "', Proprietario = '" + projeto.getProprietario() +                                                
                                                "', Versao = '" + projeto.getVersao() + 
                                                "', Status = '" + projeto.getStatus() + 
                                                "' WHERE IdProj = '" + projeto.getId() + "'");
                                        
            JOptionPane.showMessageDialog(null, "Projeto atualizado com sucesso!");
            
            cnx.close();
           
        }
        
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, "Falha ao atualizar projeto!");
             System.err.println(e.getMessage());
        }
        
    }
    
    public static void excluirProjeto(int Id) throws SQLException{
        
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();
                    
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Excluindo cadastro do projeto ...");
            
            st.executeUpdate("DELETE FROM Projetos WHERE IdProj = " + Id);
                                        
            JOptionPane.showMessageDialog(null, "Projeto excluido com sucesso!");
            
            cnx.close();
            
        }  
        
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, "Falha ao excluir projeto!");
             System.err.println(e.getMessage());
        }    
                   
    }
    
    public static List<Projetos> listProjByUser(int idUser) throws SQLException {
      
        List<Projetos> listaProjetos = new ArrayList<>();
         
        Projetos proj=null;
        
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cosultando projeto ...");
                        
            ResultSet rs = st.executeQuery("SELECT * FROM Projetos WHERE Proprietario = " + idUser + ";");
                       
            System.out.println("listProjByUser: Ok! +");
                                
            while (rs.next())
            {
                proj = new Projetos();
                
                proj.setId(rs.getInt("IdProj"));
                proj.setNomeProj(rs.getString("Nome"));
                proj.setDescriçao(rs.getString("Descriçao"));
                proj.setProprietario(rs.getInt("Proprietario"));
                proj.setVersao(rs.getString("Versao"));
                proj.setStatus(rs.getString("Status"));
                             
                boolean add = listaProjetos.add(proj);                  
            }
                  
            cnx.close();
            
            return listaProjetos;
            
        }
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
             return null;
        }               
        
    }
        
    public static Projetos projByIdUserAndIdProj(int idUser, int idProj) throws SQLException {  
               
        Projetos proj=null;
        
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cosultando projetos ...");
                      
            ResultSet rs = st.executeQuery("SELECT * FROM Projetos WHERE Proprietario = " + idUser + " AND IdProj = " + idProj + ";");         
                        
            System.out.println("listProjByUserAndProj: Ok! +");         
                     
            if(rs != null && rs.next())
            {
              
                proj = new Projetos();
                
                proj.setId(rs.getInt("IdProj"));
                proj.setNomeProj(rs.getString("Nome"));
                proj.setDescriçao(rs.getString("Descriçao"));
                proj.setProprietario(rs.getInt("Proprietario"));
                proj.setVersao(rs.getString("Versao"));
                proj.setStatus(rs.getString("Status"));        
            
            }    
            
            cnx.close();
            
            return proj; 
                                      
        }
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
             return null;
        }             
    
    }       
    
    public static List<Projetos> pesquisaProjetos(String busca) throws SQLException {  
               
        List<Projetos> listaProjetos = new ArrayList<>();
        
        Projetos proj=null;
        
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cosultando projetos ...");
                      
            ResultSet rs = st.executeQuery("SELECT * FROM Projetos WHERE Nome LIKE '%" + busca +
                                                             "%' OR Descriçao LIKE '%" + busca + "%';");
                                                             
            System.out.println("listarProjetos: Ok! +");         
                     
            while (rs.next())
            {
                proj = new Projetos();
                
                proj.setId(rs.getInt("IdProj"));
                proj.setNomeProj(rs.getString("Nome"));
                proj.setProprietario(rs.getInt("Proprietario"));
                proj.setVersao(rs.getString("Versao"));
                proj.setStatus(rs.getString("Status"));
                proj.setDescriçao(rs.getString("Descriçao"));       
                
                             
                boolean add = listaProjetos.add(proj);                  
            }
                  
            cnx.close();
            
            return listaProjetos;
                                      
        }
        catch (SQLException e)
        {
             JOptionPane.showMessageDialog(null, "Falha ao listar projetos!");
             System.err.println(e.getMessage());
             return null;
        }             
    
    }  
    
}
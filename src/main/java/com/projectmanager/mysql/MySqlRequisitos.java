package com.projectmanager.mysql;

import com.projectmanager.models.Requisitos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MySqlRequisitos {
    
    
    public static void criarTabRequisitos(){
        
        Connection cnx = null;
        
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
                                    
            System.out.println("Verificando e criando a tabela Requisitos ...");
            
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Requisitos("
                    + "IdReq INT NOT NULL AUTO_INCREMENT,"                              // 01
                    + "NomeReq VARCHAR(64) NOT NULL,"                                   // 02
                    + "IdProj INT NOT NULL,"                                            // 03                   
                    + "Modulo VARCHAR(64) NOT NULL,"                                    // 04
                    + "Funcionalidades VARCHAR(128) NOT NULL,"                          // 05
                    + "DtCriacao VARCHAR(16) NOT NULL,"                                 // 06
                    + "Autor INT NOT NULL,"                                             // 07
                    + "DtUltAlteracao VARCHAR(16) NOT NULL,"                            // 08
                    + "AutorUltimaMod INT NOT NULL,"                                    // 09
                    + "Versao VARCHAR(16) NOT NULL,"                                    // 10
                    + "Prioridade VARCHAR(16) NOT NULL,"                                // 11
                    + "Complexidade VARCHAR(16) NOT NULL,"                              // 12
                    + "TmpEsforcoEstimado INT NOT NULL,"                                // 13
                    + "Estado VARCHAR(16) NOT NULL,"                                    // 14
                    + "Fase VARCHAR(16) NOT NULL,"                                      // 15
                    + "Descricao VARCHAR(4096),"                                        // 16
                    + "PRIMARY KEY(IdReq));");                                          
            
            System.out.println("Tabela Requisitos criada!");
            
            cnx.close();
        
        }
        
        catch (Exception e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
        }        
        
    }
    
    public static void cadastrarRequisito(Requisitos requisito){
    
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();
                    
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cadastrando projeto ...");
            st.executeUpdate("INSERT INTO Requisitos (NomeReq, "+               // 02
                                                     "IdProj, "+                // 03
                                                     "Modulo, "+                // 04
                                                     "Funcionalidades, "+       // 05                                                     
                                                     "DtCriacao, "+             // 06
                                                     "Autor, "+                 // 07                                                     
                                                     "DtUltAlteracao, "+        // 08
                                                     "AutorUltimaMod, "+        // 09
                                                     "Versao, "+                // 10
                                                     "Prioridade, "+            // 11
                                                     "Complexidade, "+          // 12
                                                     "TmpEsforcoEstimado, "+    // 13
                                                     "Estado, "+                // 14
                                                     "Fase, "+                  // 15
                                                     "Descricao) "+             // 16
                                    "VALUES ('"+ requisito.getNomeReq()
                                        +"', '"+ requisito.getIdProj()
                                        +"', '"+ requisito.getModulo()
                                        +"', '"+ requisito.getFuncionalidades()
                                        +"', '"+ requisito.getDtCriacao()
                                        +"', '"+ requisito.getIdAutor()
                                        +"', '"+ requisito.getDtUltAlteracao()
                                        +"', '"+ requisito.getAutorUltimaMod()
                                        +"', '"+ requisito.getVersao()
                                        +"', '"+ requisito.getPrioridade()
                                        +"', '"+ requisito.getComplexidade()
                                        +"', '"+ requisito.getTmpEsforcoEstimado()
                                        +"', '"+ requisito.getEstado()
                                        +"', '"+ requisito.getFase()
                                        +"', '"+ requisito.getDescricao()                                        
                                        +"')");
            
            JOptionPane.showMessageDialog(null, "Requisito cadastrado com sucesso!");
            
            cnx.close();
           
        }
        
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, "Falha ao cadastrar requisito!");
             System.err.println(e.getMessage());
        }
        
    }
    
    public static void atualizarRequisito(Requisitos requisito) throws SQLException{
       
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();
                    
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Atualizando os requisitos ...");
            st.executeUpdate("UPDATE Requisitos SET IdProj = '" + requisito.getIdProj() +                               // 03
                                                     "', NomeReq = '" + requisito.getNomeReq() +                        // 02
                                                     "', Modulo = '" + requisito.getModulo() +                          // 04
                                                     "', Funcionalidades = '" + requisito.getFuncionalidades() +        // 05    
                                                     "', DtCriacao = '" + requisito.getDtCriacao() +                    // 06
                                                     "', Autor = '" + requisito.getIdAutor() +                          // 07
                                                     "', DtUltAlteracao = '" + requisito.getDtUltAlteracao() +          // 08 
                                                     "', AutorUltimaMod = '" + requisito.getAutorUltimaMod() +          // 09
                                                     "', Versao = '" + requisito.getVersao() +                          // 10
                                                     "', Prioridade = '" + requisito.getPrioridade() +                  // 11
                                                     "', Complexidade = '" + requisito.getComplexidade() +              // 12
                                                     "', TmpEsforcoEstimado = '" + requisito.getTmpEsforcoEstimado() +  // 13
                                                     "', Estado = '" + requisito.getEstado() +                          // 14
                                                     "', Fase = '" + requisito.getFase() +                              // 15
                                                     "', Descricao = '"+ requisito.getDescricao() +                     // 16
                                                     "' WHERE IdReq = '" + requisito.getIdReq() + "'");                 // 01
          
            JOptionPane.showMessageDialog(null, "Requisito atualizado com sucesso!");
            
            cnx.close();
           
        }
        
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, "Falha ao atualizar requisito!");
             System.err.println(e.getMessage());
        }
        
    }
    
    public static void excluirRequisito(int IdReq){
       
        Connection cnx = null;       
      
        cnx = MySqlConector.conectar();
                    
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Excluindo cadastro de requisito ...");
            
            st.executeUpdate("DELETE FROM Requisitos WHERE IdReq = " + IdReq);
                                        
            JOptionPane.showMessageDialog(null, "Requisito excluido com sucesso!");
            
            cnx.close();
            
        }   
        
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, "Falha ao excluir requisito!");
             System.err.println(e.getMessage());
        }    
        
    }    
    
    public static List<Requisitos> requisitosByIdUserAndIdProj(int idUser, int idProj) throws SQLException {  
        
        List<Requisitos> listaRequisitos = new ArrayList<>();
               
        Requisitos req = null;
        
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cosultando requisitos ...");
                      
            ResultSet rs = st.executeQuery("SELECT * FROM Requisitos WHERE Autor = " + idUser + " AND IdProj = " + idProj + ";");          
                        
            System.out.println("requisitosByIdUserAndIdProj: Ok! +");         
                     
            while (rs.next())
            {
              
                req = new Requisitos(rs.getInt("IdReq"),
                                     rs.getString("NomeReq"),
                                     rs.getInt("IdProj"),
                                     rs.getString("Modulo"),
                                     rs.getString("Funcionalidades"),
                                     rs.getString("DtCriacao"),
                                     rs.getInt("Autor"),
                                     rs.getString("DtUltAlteracao"),
                                     rs.getInt("AutorUltimaMod"),
                                     rs.getString("Versao"),
                                     rs.getString("Prioridade"),
                                     rs.getString("Complexidade"),
                                     rs.getInt("TmpEsforcoEstimado"),
                                     rs.getString("Estado"),   
                                     rs.getString("Fase"),
                                     rs.getString("Descricao"));
                
                boolean add = listaRequisitos.add(req);
            
            }    
            
            cnx.close();
            
            return listaRequisitos;
            
        }
        
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
             return null;
        }
        
    } 
    
    public static List<Requisitos> pesquisaRequisitos(String busca) throws SQLException {  
               
        List<Requisitos> listaRequisitos = new ArrayList<>();
        
        Requisitos req = null;
        
        Connection cnx = null;
      
        cnx = MySqlConector.conectar();
        
        try
        {
            Statement st = cnx.createStatement(); 
            
            System.out.println("Cosultando requisitos ...");
                      
            ResultSet rs = st.executeQuery("SELECT * FROM Requisitos WHERE NomeReq LIKE '%" + busca +
                                                             "%' OR Descricao LIKE '%" + busca + "%';");
                                                             
            System.out.println("listaRequisitos: Ok! +");         
                     
            while (rs.next())
            {
                req = new Requisitos(rs.getInt("IdReq"),
                                     rs.getString("NomeReq"),
                                     rs.getInt("IdProj"),
                                     rs.getString("Modulo"),
                                     rs.getString("Funcionalidades"),
                                     rs.getString("DtCriacao"),
                                     rs.getInt("Autor"),
                                     rs.getString("DtUltAlteracao"),
                                     rs.getInt("AutorUltimaMod"),
                                     rs.getString("Versao"),
                                     rs.getString("Prioridade"),
                                     rs.getString("Complexidade"),
                                     rs.getInt("TmpEsforcoEstimado"),
                                     rs.getString("Estado"),   
                                     rs.getString("Fase"),
                                     rs.getString("Descricao"));
                
                boolean add = listaRequisitos.add(req);                
            }
                  
            cnx.close();
            
            return listaRequisitos;
                                      
        }
        catch (SQLException e)
        {
             System.err.println("Erro!");
             System.err.println(e.getMessage());
             return null;
        }             
    
    }  
    
}


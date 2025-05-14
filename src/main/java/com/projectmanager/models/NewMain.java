package com.projectmanager.models;

import com.projectmanager.frms.FrmLogin;
import com.projectmanager.mysql.MySqlProjetos;
import com.projectmanager.mysql.MySqlRequisitos;
import com.projectmanager.mysql.MySqlUsuarios;
import java.sql.SQLException;


public class NewMain {

  
    public static FrmLogin login;
    
    public static void main(String[] args) throws SQLException {                   
       
        System.out.println("Conexão com remotemysql.com:");
      
        MySqlUsuarios.criarTabUsuario();
      
        MySqlProjetos.criarTabProjetos();
      
        MySqlRequisitos.criarTabRequisitos();
     
        login = new FrmLogin();       
      
        login.setVisible(true);        
             
    }
    
}

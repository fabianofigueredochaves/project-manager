package com.refact;

//import com.projectmanager.models.*;
import com.projectmanager.frms.FrmLogin;
import com.refact.controller.UsuarioController;


//import com.projectmanager.mysql.MySqlProjetos;
//import com.projectmanager.mysql.MySqlRequisitos;
//import com.projectmanager.mysql.MySqlUsuarios;
import java.sql.SQLException;


public class NewMain1 {

  
    public FrmLogin login;
    
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
    
    //    start.inicializarBancoDados();
        
      //  MySqlProjetos.criarTabProjetos();
      
      //  MySqlRequisitos.criarTabRequisitos();
     
     //   login = new FrmLogin();       
      
     //   login.setVisible(true);        
             
    }
    
}

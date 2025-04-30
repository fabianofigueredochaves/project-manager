package com.projectmanager.mysql;
import java.sql.*;

public class MySqlConector {

    static private Connection conexao = null;
        
    static private final String HOSTNAME = "jdbc:mysql://localhost";
    static private final String DB_NAME = "projectmanager";
    static private final String USER_NAME = "ffc";
    static private final String USER_PASS = "ffc";
    static private final String PORT_NUMBER = "3306";
       
    public static Connection conectar(){// throws SQLException{
        
        try{
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(HOSTNAME+":"+PORT_NUMBER+"/"+DB_NAME, USER_NAME, USER_PASS);         
            System.out.println("Conexão criada!");
        
        }
        
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.toString());  
        }
        
        return conexao;
    }   
}

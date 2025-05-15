
package com.refact.factory;

// 7. Exemplo de Factory para criação de DAO

import com.refact.dao.UsuarioDAO;
import com.refact.dao.MySqlUsuarioDAO;
import com.refact.util.DatabaseConfig;
import com.refact.util.DatabaseConnection;

public class DAOFactory {

    public static UsuarioDAO createUsuarioDAO() {
        // Configuração do banco usando o Builder Pattern
        DatabaseConfig config = new DatabaseConfig.Builder()
                .dbName("projectmanager")
                .username("ffc")
                .password("ffc")
                .build();

        // Obtém a instância da conexão usando Singleton
        DatabaseConnection connection = DatabaseConnection.getInstance(config);

        // Cria e retorna o DAO com a conexão injetada
        return new MySqlUsuarioDAO(connection);
    }
}

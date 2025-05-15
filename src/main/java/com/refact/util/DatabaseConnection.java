
package com.refact.util;

// 4. Conexão com o banco de dados (Singleton)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

    private static DatabaseConnection instance;

    // Configurações de conexão encapsuladas
    private final DatabaseConfig config;

    private DatabaseConnection(DatabaseConfig config) {
        this.config = config;
    }

    public static synchronized DatabaseConnection getInstance(DatabaseConfig config) {
        if (instance == null) {
            instance = new DatabaseConnection(config);
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(config.getDriverClass());
            String url = config.getUrl() + ":" + config.getPort() + "/" + config.getDbName();
            return DriverManager.getConnection(url, config.getUsername(), config.getPassword());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Driver JDBC não encontrado", e);
            throw new SQLException("Driver JDBC não encontrado", e);
        }
    }
}

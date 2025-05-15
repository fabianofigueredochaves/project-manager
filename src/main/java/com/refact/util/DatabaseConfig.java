
package com.refact.util;

// 5. Configuração do banco de dados


public class DatabaseConfig {
    private final String driverClass;
    private final String url;
    private final String dbName;
    private final String username;
    private final String password;
    private final String port;

    // Construtor privado usado pelo Builder
    private DatabaseConfig(Builder builder) {
        this.driverClass = builder.driverClass;
        this.url = builder.url;
        this.dbName = builder.dbName;
        this.username = builder.username;
        this.password = builder.password;
        this.port = builder.port;
    }

    // Getters
    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    // Builder Pattern
    public static class Builder {
        private String driverClass = "com.mysql.cj.jdbc.Driver";
        private String url = "jdbc:mysql://localhost";
        private String dbName;
        private String username;
        private String password;
        private String port = "3306";

        public Builder dbName(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder driverClass(String driverClass) {
            this.driverClass = driverClass;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder port(String port) {
            this.port = port;
            return this;
        }

        public DatabaseConfig build() {
            return new DatabaseConfig(this);
        }
    }
}
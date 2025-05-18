package com.refact.dao;


import com.refact.models.Usuarios;
import com.refact.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlUsuarioDAO implements UsuarioDAO {

    private static final Logger LOGGER = Logger.getLogger(MySqlUsuarioDAO.class.getName());
    private final DatabaseConnection databaseConnection;

    // Injeção de dependência via construtor
    public MySqlUsuarioDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public MySqlUsuarioDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void criarTabela() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Usuarios(" +
                "Id INT NOT NULL AUTO_INCREMENT," +
                "NomeCompleto VARCHAR(64) NOT NULL," +
                "NomeUsuario VARCHAR(16) UNIQUE NOT NULL," +
                "Senha VARCHAR(8) NOT NULL," +
                "Email VARCHAR(64)," +
                "Telefone VARCHAR(16)," +
                "PRIMARY KEY(Id));";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            LOGGER.info("Verificando e criando a tabela Usuarios...");
            stmt.executeUpdate();
            LOGGER.info("Tabela Usuários criada com sucesso!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao criar tabela de usuários", e);
            throw e;
        }
    }

    @Override
    public String obterNomeCompleto(int id) throws SQLException {
        String sql = "SELECT NomeCompleto FROM Usuarios WHERE Id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("NomeCompleto");
                }
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao obter nome completo do usuário", e);
            throw e;
        }
    }

    @Override
    public List<String> obterUsuarios() throws SQLException {
        List<String> usuarios = new ArrayList<>();
        String sql = "SELECT NomeUsuario FROM Usuarios ORDER BY NomeUsuario ASC";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(rs.getString("NomeUsuario"));
            }
            return usuarios;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao obter lista de usuários", e);
            throw e;
        }
    }

    @Override
    public List<Usuarios> obterTodosUsuarios() throws SQLException {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios ORDER BY NomeUsuario ASC";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId(rs.getInt("Id"));
                usuario.setNomeCompleto(rs.getString("NomeCompleto"));
                usuario.setNomeUsuario(rs.getString("NomeUsuario"));
                usuario.setSenha(rs.getString("Senha"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setTelefone(rs.getString("Telefone"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao obter todos os usuários", e);
            throw e;
        }
    }

    @Override
    public int autenticarUsuario(String usuario, String senha) throws SQLException {
        String sql = "SELECT Id, Senha FROM Usuarios WHERE NomeUsuario = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("Id");
                    String storedPassword = rs.getString("Senha");

                    // Em uma implementação real, usar biblioteca de hash de senhas
                    if (senha.equals(storedPassword)) {
                        return id;
                    } else {
                        return 0; // Senha incorreta
                    }
                }
                return -1; // Usuário não encontrado
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao autenticar usuário", e);
            throw e;
        }
    }

    @Override
    public void cadastrarUsuario(Usuarios usuario) throws SQLException {
        String sql = "INSERT INTO Usuarios (NomeCompleto, NomeUsuario, Senha, Email, Telefone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefone());

            stmt.executeUpdate();
            LOGGER.info("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar usuário", e);
            throw e;
        }
    }

    @Override
    public void atualizarUsuario(Usuarios usuario) throws SQLException {
        String sql = "UPDATE Usuarios SET NomeCompleto = ?, NomeUsuario = ?, Senha = ?, Email = ?, Telefone = ? WHERE Id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefone());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();
            LOGGER.info("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar usuário", e);
            throw e;
        }
    }

    @Override
    public void excluirUsuario(int id) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE Id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            LOGGER.info("Usuário excluído com sucesso!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir usuário", e);
            throw e;
        }
    }
}
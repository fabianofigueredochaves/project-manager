// 1. Model (Entidade)
package com.refact.models;

public class Usuario {
    private int id;
    private String nomeCompleto;
    private String nomeUsuario;
    private String senha;
    private String email;
    private String telefone;

    // Construtor vazio
    public Usuario() {
    }

    // Construtor para criação de novo usuário
    public Usuario(String nomeCompleto, String nomeUsuario, String senha, String email, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }

    // Construtor para usuário existente
    public Usuario(int id, String nomeCompleto, String nomeUsuario, String senha, String email, String telefone) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
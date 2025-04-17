package com.projectmanager.models;

public class Projetos {

    private int id;
    private String nome;
    private String descriçao;
    private int proprietario;
    private String versao;
    private String status;
    
    public Projetos(){        
    }
    
    public Projetos(String nome, String descriçao, int proprietario, String versao, String status){
        this.nome = nome;
        this.descriçao = descriçao;
        this.proprietario = proprietario;
        this.versao = versao;
        this.status = status;
    }
    
    public Projetos(int id, String nome, String descriçao, int proprietario, String versao, String status){
        this.id = id;
        this.nome = nome;
        this.descriçao = descriçao;
        this.proprietario = proprietario;
        this.versao = versao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNomeProj() {
        return nome;
    }

    public void setNomeProj(String nome) {
        this.nome = nome;
    }

    public String getDescriçao() {
        return descriçao;
    }

    public void setDescriçao(String descriçao) {
        this.descriçao = descriçao;
    }

    public int getProprietario() {
        return proprietario;
    }

    public void setProprietario(int proprietario) {
        this.proprietario = proprietario;
    }
    
    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
    
}

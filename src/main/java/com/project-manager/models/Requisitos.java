/*  Descrição das variáveis na classe:

1. Identificador;
2. Nome;
3. Id Projeto;          // Id do projeto que possui os requisitos
4. Módulo; 
5. Funcionalidades;
6. Data de criação;
7. Autor;               // Id do usuario do projeto / requisito
8. Data da última alteração;
9. Autor da última modificação; // Id do usuario que realizou a ultima alteracao
10. Versão;
11. Prioridade;
12. Complexidade;
13. Esforço estimado em horas;
14. Estado (Especificado, Em andamento, Finalizado, ...);
15. Fase;
16. Descrição.

*/
package com.projectmanager.models;

public class Requisitos{
    
    private int idReq, idProjeto, tmpEsforcoEstimado, idAutor, autorUltimaMod;
    private String nomeReq, modulo, funcionalidades,  
             versao, prioridade, complexidade,
             estado, fase, descricao, dtCriacao, dtUltAlteracao; 
    
    public Requisitos(  //int idReq,                  // 01                           
                        String nomeReq,             // 02
                        int idProjeto,              // 03  
                        String modulo,              // 04  
                        String funcionalidades,     // 05
                        String dtCriacao,           // 06
                        int idAutor,                // 07
                        String dtUltAlteracao,      // 08
                        int autorUltimaMod,         // 09
                        String versao,              // 10
                        String prioridade,          // 11
                        String complexidade,        // 12
                        int tmpEsforcoEstimado,     // 13
                        String estado,              // 14
                        String fase,                // 15
                        String descricao){          // 16
        
        //this.idReq = idReq;                         // 01
        this.nomeReq = nomeReq;                       // 02
        this.idProjeto = idProjeto;                   // 03
        this.modulo = modulo;                         // 04
        this.funcionalidades = funcionalidades;       // 05
        this.dtCriacao = dtCriacao;                   // 06
        this.idAutor = idAutor;                       // 07
        this.dtUltAlteracao = dtUltAlteracao;         // 08
        this.autorUltimaMod = autorUltimaMod;         // 09
        this.versao = versao;                         // 10
        this.prioridade = prioridade;                 // 11
        this.complexidade = complexidade;             // 12
        this.tmpEsforcoEstimado = tmpEsforcoEstimado; // 13
        this.estado = estado;                         // 14  
        this.fase = fase;                             // 15
        this.descricao = descricao;                   // 16  
        
    }
    
    public Requisitos(  int idReq,                  // 01                           
                        String nomeReq,             // 02
                        int idProjeto,              // 03  
                        String modulo,              // 04  
                        String funcionalidades,     // 05
                        String dtCriacao,           // 06
                        int idAutor,                // 07
                        String dtUltAlteracao,      // 08
                        int autorUltimaMod,         // 09
                        String versao,              // 10
                        String prioridade,          // 11
                        String complexidade,        // 12
                        int tmpEsforcoEstimado,     // 13
                        String estado,              // 14
                        String fase,                // 15
                        String descricao){          // 16
        
        this.idReq = idReq;                           // 01
        this.nomeReq = nomeReq;                       // 02
        this.idProjeto = idProjeto;                   // 03
        this.modulo = modulo;                         // 04
        this.funcionalidades = funcionalidades;       // 05
        this.dtCriacao = dtCriacao;                   // 06
        this.idAutor = idAutor;                       // 07
        this.dtUltAlteracao = dtUltAlteracao;         // 08
        this.autorUltimaMod = autorUltimaMod;         // 09
        this.versao = versao;                         // 10
        this.prioridade = prioridade;                 // 11
        this.complexidade = complexidade;             // 12
        this.tmpEsforcoEstimado = tmpEsforcoEstimado; // 13
        this.estado = estado;                         // 14  
        this.fase = fase;                             // 15
        this.descricao = descricao;                   // 16  
        
    }
    
    public int getIdReq() {
        return idReq;
    }

    public void setIdReq(int idReq) {
        this.idReq = idReq;
    }
    
    public int getIdProj() {
        return idProjeto;
    }

    public void setIdProj(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeReq() {
        return nomeReq;
    }

    public void setNomeReq(String nomeReq) {
        this.nomeReq = nomeReq;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(String funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getAutorUltimaMod() {
        return autorUltimaMod;
    }

    public void setAutorUltimaMod(int autorUltimaMod) {
        this.autorUltimaMod = autorUltimaMod;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(String complexidade) {
        this.complexidade = complexidade;
    }

    public int getTmpEsforcoEstimado() {
        return tmpEsforcoEstimado;
    }

    public void setTmpEsforcoEstimado(int tmpEsforcoEstimado) {
        this.tmpEsforcoEstimado = tmpEsforcoEstimado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(String dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public String getDtUltAlteracao() {
        return dtUltAlteracao;
    }

    public void setDtUltAlteracao(String dtUltAlteracao) {
        this.dtUltAlteracao = dtUltAlteracao;
    }    
      
}

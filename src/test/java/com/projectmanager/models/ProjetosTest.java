package com.projectmanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProjetosTest {
    private Projetos projeto;

    @BeforeEach
    void setUp() {
        projeto = new Projetos();
    }

    @Test
    void testCriarProjeto() {
        projeto.setNomeProj("Projeto Teste");
        projeto.setDescriçao("Descrição do projeto teste");
        projeto.setStatus("Em andamento");
        
        assertNotNull(projeto);
        assertEquals("Projeto Teste", projeto.getNomeProj());
        assertEquals("Descrição do projeto teste", projeto.getDescriçao());
        assertEquals("Em andamento", projeto.getStatus());
    }

    @Test
    void testAtualizarProjeto() {
        projeto.setNomeProj("Projeto Original");
        projeto.setDescriçao("Descrição original");
        
        projeto.setNomeProj("Projeto Atualizado");
        projeto.setDescriçao("Descrição atualizada");
        
        assertEquals("Projeto Atualizado", projeto.getNomeProj());
        assertEquals("Descrição atualizada", projeto.getDescriçao());
    }

    @Test
    void testExcluirProjeto() {
        projeto.setId(1);
        assertNotNull(projeto.getId());
        
        projeto.setId(0);
        assertEquals(0, projeto.getId());
    }

    @Test
    void testLerProjeto() {
        projeto.setId(1);
        projeto.setNomeProj("Projeto de Leitura");
        projeto.setDescriçao("Projeto para teste de leitura");
        
        assertNotNull(projeto.getId());
        assertNotNull(projeto.getNomeProj());
        assertNotNull(projeto.getDescriçao());
    }
} 
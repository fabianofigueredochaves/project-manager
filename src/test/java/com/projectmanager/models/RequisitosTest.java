package com.projectmanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequisitosTest {
    private Requisitos requisito;

    @BeforeEach
    void setUp() {
        requisito = new Requisitos(
            "Requisito Teste",
            1,
            "Módulo Teste",
            "Funcionalidade Teste",
            "2024-03-20",
            1,
            "2024-03-20",
            1,
            "1.0",
            "Alta",
            "Média",
            8,
            "Especificado",
            "Análise",
            "Descrição do requisito teste"
        );
    }

    @Test
    void testCriarRequisito() {
        assertNotNull(requisito);
        assertEquals("Requisito Teste", requisito.getNomeReq());
        assertEquals(1, requisito.getIdProj());
        assertEquals("Módulo Teste", requisito.getModulo());
        assertEquals("Funcionalidade Teste", requisito.getFuncionalidades());
        assertEquals("Alta", requisito.getPrioridade());
        assertEquals("Média", requisito.getComplexidade());
        assertEquals(8, requisito.getTmpEsforcoEstimado());
    }

    @Test
    void testAtualizarRequisito() {
        requisito.setNomeReq("Requisito Atualizado");
        requisito.setPrioridade("Baixa");
        requisito.setComplexidade("Alta");
        requisito.setTmpEsforcoEstimado(16);
        
        assertEquals("Requisito Atualizado", requisito.getNomeReq());
        assertEquals("Baixa", requisito.getPrioridade());
        assertEquals("Alta", requisito.getComplexidade());
        assertEquals(16, requisito.getTmpEsforcoEstimado());
    }

    @Test
    void testExcluirRequisito() {
        requisito.setIdReq(1);
        assertNotNull(requisito.getIdReq());
        
        requisito.setIdReq(0);
        assertEquals(0, requisito.getIdReq());
    }

    @Test
    void testLerRequisito() {
        requisito.setIdReq(1);
        requisito.setNomeReq("Requisito de Leitura");
        requisito.setDescricao("Descrição do requisito para leitura");
        
        assertNotNull(requisito.getIdReq());
        assertNotNull(requisito.getNomeReq());
        assertNotNull(requisito.getDescricao());
        assertNotNull(requisito.getDtCriacao());
        assertNotNull(requisito.getDtUltAlteracao());
    }
} 
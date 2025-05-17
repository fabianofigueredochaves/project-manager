package com.projectmanager.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuariosTest {
    private Usuarios usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuarios();
    }

    @Test
    void testCriarUsuario() {
        usuario.setNomeCompleto("João Silva");
        usuario.setNomeUsuario("joao.silva");
        usuario.setSenha("senha123");
        usuario.setEmail("joao@email.com");
        usuario.setTelefone("(11) 99999-9999");
        
        assertNotNull(usuario);
        assertEquals("João Silva", usuario.getNomeCompleto());
        assertEquals("joao.silva", usuario.getNomeUsuario());
        assertEquals("senha123", usuario.getSenha());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("(11) 99999-9999", usuario.getTelefone());
    }

    @Test
    void testAtualizarUsuario() {
        usuario.setNomeCompleto("Nome Original");
        usuario.setEmail("email@original.com");
        
        usuario.setNomeCompleto("Nome Atualizado");
        usuario.setEmail("email@atualizado.com");
        
        assertEquals("Nome Atualizado", usuario.getNomeCompleto());
        assertEquals("email@atualizado.com", usuario.getEmail());
    }

    @Test
    void testExcluirUsuario() {
        usuario.setId(1);
        assertNotNull(usuario.getId());
        
        usuario.setId(0);
        assertEquals(0, usuario.getId());
    }

    @Test
    void testLerUsuario() {
        usuario.setId(1);
        usuario.setNomeCompleto("Usuário Teste");
        usuario.setNomeUsuario("usuario.teste");
        usuario.setEmail("teste@email.com");
        
        assertNotNull(usuario.getId());
        assertNotNull(usuario.getNomeCompleto());
        assertNotNull(usuario.getNomeUsuario());
        assertNotNull(usuario.getEmail());
    }
} 
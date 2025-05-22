package com.backend;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.Test;

public class BotonTest {

    /**
     * apiNote verifica que se crea un boton de reiniciar con el tooltip "Reiniciar juego"
     */
    @Test
    public void testCrearBotonReiniciar() {
        JButton boton = Botones.crearBotonReiniciar(8, 8, 10, new JFrame());
        assertNotNull(boton);
        assertEquals("Reiniciar juego", boton.getToolTipText());
    }
}

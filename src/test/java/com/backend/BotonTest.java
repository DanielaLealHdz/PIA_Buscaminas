package com.backend;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.Test;

public class BotonTest {

    //probamos que crearBotonReiniciar no es null y tiene tooltip
    @Test
    public void testCrearBotonReiniciar() {
        JButton boton = Botones.crearBotonReiniciar(8, 8, 10, new JFrame());
        assertNotNull(boton);
        assertEquals("Reiniciar juego", boton.getToolTipText());
    }
}

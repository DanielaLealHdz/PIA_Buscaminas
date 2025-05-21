package com.backend;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.Test;

public class PantallaInicioTest {

    //se encarga de que se construya y enseñe la pantalla correctamente
    @Test
    public void testPantallaInicioSeConstruye() {
        PantallaInicio inicio = new PantallaInicio();
        assertNotNull(inicio);
        assertTrue(inicio.isVisible());
    }
}

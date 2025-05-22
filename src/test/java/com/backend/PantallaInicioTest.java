package com.backend;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.Test;

public class PantallaInicioTest {

    /**
     * @apiNote verifica que se crea la pantalla de inicio y que se muestra correctamente
     */
    @Test
    public void testPantallaInicioSeConstruye() {
        PantallaInicio inicio = new PantallaInicio();
        assertNotNull(inicio);
        assertTrue(inicio.isVisible());
    }
}

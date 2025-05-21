package com.backend;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContadorVictoriasTest {

    @Test
    public void testIncrementar() {
        ContadorVictorias contador = new ContadorVictorias();
        contador.incrementar();
        assertEquals(1, contador.getRachaActual());
    }

    @Test
    public void testReiniciar() {
        ContadorVictorias contador = new ContadorVictorias();
        contador.incrementar();
        contador.reiniciar();
        assertEquals(0, contador.getRachaActual());
    }
}
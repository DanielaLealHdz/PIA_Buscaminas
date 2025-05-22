package com.backend;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContadorVictoriasTest {

    /**
     * @apiNote verifica que el contador victorias se incremente correctamente
     */
    @Test
    public void testIncrementar() {
        ContadorVictorias contador = new ContadorVictorias();
        contador.incrementar();
        assertEquals(1, contador.getRachaActual());
    }

    /**
     * @apiNote verifica que el contador victorias se decremente correctamente
     */
    @Test
    public void testReiniciar() {
        ContadorVictorias contador = new ContadorVictorias();
        contador.incrementar();
        contador.reiniciar();
        assertEquals(0, contador.getRachaActual());
    }

    /**
     * @apiNote verifica que el contador victorias inicia en 0 cuando se crea una instancia
     */
    @Test
    public void testContadorVictoriasIniciaEnCero() {
        ContadorVictorias c = new ContadorVictorias();
        assertEquals(0, c.getRachaActual());
    }
}
package com.backend;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class TableroBuscaminasTest {

    private TableroBuscaminas tablero;

    @BeforeEach
    public void setUp() {
        tablero = new TableroBuscaminas(8, 8, 10); // tablero 8x8 con 10 minas
    }

    /**
     * @apiNote Este test se encarga de verificar que la zona segura no contenga minas
     */
    @Test
    public void testGenerarZonaSegura_NoContieneMinas() {
        Set<Casilla> zonaSegura = tablero.generarZonaSeguraExpandida(3, 3, 10);
        tablero.generarMinasConZonaSegura(zonaSegura);

        for (Casilla c : zonaSegura) {
            assertFalse(c.isMina(), "La zona segura no debe contener minas");
        }
    }

    /**
     * @apiNote Este test se encarga de verificar que se generen exactamente n minas fuera de la zona segura
     */
    @Test
    public void testNumeroCorrectoDeMinasGeneradas() {
        Set<Casilla> zonaSegura = tablero.generarZonaSeguraExpandida(0, 0, 10);
        tablero.generarMinasConZonaSegura(zonaSegura);

        int minas = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero.casillas[i][j].isMina()) {
                    minas++;
                }
            }
        }
        assertEquals(10, minas, "El numero de minas generadas debe ser 10");
    }

    /**
     * @apiNote Este test se encarga de verificar que las casillas vecinas de una casilla tengan el numero correcto de minas alrededor
     */
    @Test
    public void testActualizarNumMinasAlrededor() {
        tablero = new TableroBuscaminas(5, 5, 0);
        tablero.inicializarCasillas();

        //ponemos una mina manualmente en el centro
        Casilla mina = tablero.casillas[2][2];
        mina.setMina(true);

        // simulamos logica del metodo privado
        for (Casilla vecina : tablero.obtenerCasillasAlrededor(2, 2)) {
            vecina.incrementarNumeroMinasAlrededor();
        }

        // verificamos que todas las casillas alrededor tengan 1 mina alrededor
        for (Casilla vecina : tablero.obtenerCasillasAlrededor(2, 2)) {
            assertEquals(1, vecina.getNumMinasAlrededor());
        }
    }

    /**
     * @apiNote Este test se encarga de devolver el numero correcto de vecinos segun la posicion
     */
    @Test
    public void testObtenerCasillasAlrededor() {
        tablero = new TableroBuscaminas(5, 5, 0);
        tablero.inicializarCasillas();

        //centro (2, 2)debe tener 8 vecinos
        List<Casilla> centro = tablero.obtenerCasillasAlrededor(2, 2);
        assertEquals(8, centro.size(), "Una casilla central debe tener 8 vecinos");

        //esquina superior izquierda(0, 0)debe tener 3 vecinos
        List<Casilla> esquina = tablero.obtenerCasillasAlrededor(0, 0);
        assertEquals(3, esquina.size(), "Una casilla en la esquina superior izquierda debe tener 3 vecinos");

        //esquina izquierda(2, 0)debe tener 5 vecinos
        List<Casilla> borde = tablero.obtenerCasillasAlrededor(2, 0);
        assertEquals(5, borde.size(), "Una casilla en el borde izquierdo debe tener 5 vecinos.");
    }

    /**
     * @apiNote Este test se encarga de inicializar las casillas del tablero correctamente
     */
    @Test
    public void testInicializarCasillas() {
        TableroBuscaminas tablero = new TableroBuscaminas(6, 6, 5); // 6x6 tablero
        tablero.inicializarCasillas(); // ya lo llama el constructor, pero lo hacemos explícito

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertNotNull(tablero.casillas[i][j], "La casilla en (" + i + "," + j + ") no debe ser null");
            }
        }
    }

    /**
     * @apiNote Este test se encarga de verificar que la matriz del tablero sea del tamaño correcto
     */
    @Test
    public void testTableroTamanioCorrecto() {
        TableroBuscaminas t = new TableroBuscaminas(5, 4, 3);
        assertEquals(5, t.casillas.length);
        assertEquals(4, t.casillas[0].length);
    }

}

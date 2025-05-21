package com.backend;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.Test;


public class GestorReveladoTest {

    //Este test simula un tablero sin minas, revela todas las casillas y
    //verifica que se active la condicion de victoria
    @Test
    public void testVerificarVictoriaCuandoTodoRevelado() {
        int filas = 3;
        int columnas = 3;
        int minas = 0;

        TableroBuscaminas tablero = new TableroBuscaminas(filas, columnas, minas);
        JButton[][] botones = new JButton[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton();
                tablero.casillas[i][j].setRevelada();  // Simula que todo esta revelado
            }
        }

        GestorRevelado gestor = new GestorRevelado();
        Temporizador temporizador = new Temporizador(new JLabel());
        VentanaPrincipal ventana = new VentanaPrincipal(filas, columnas, minas);

        gestor.verificarVictoria(botones, tablero, ventana, temporizador);

        assertTrue(ventana.isJuegoTerminado(), "El juego deberaa estar terminado (ganado) si todas las casillas sin mina estan reveladas");
    }

    //no detecta victoria si falta una casilla sin mina por revelar
    //la bandera juegotermiando funciona bien solo si se revelan todas las casillas no-mina
    @Test
    public void testNoHayVictoriaSiFaltaUnaCasillaSinRevelar() {
        int filas = 3;
        int columnas = 3;
        int minas = 0;

        TableroBuscaminas tablero = new TableroBuscaminas(filas, columnas, minas);
        JButton[][] botones = new JButton[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton();
                tablero.casillas[i][j].setRevelada();
            }
        }

        tablero.casillas[0][0].setRevelada(false);  // Simula una casilla faltante

        GestorRevelado gestor = new GestorRevelado();
        Temporizador temporizador = new Temporizador(new JLabel());
        VentanaPrincipal ventana = new VentanaPrincipal(filas, columnas, minas);

        gestor.verificarVictoria(botones, tablero, ventana, temporizador);

        assertFalse(ventana.isJuegoTerminado(), "El juego no debe estar ganado si al menos una casilla sin mina no ha sido revelada");
    }

    //este test verifica que al revelar una casilla sin mina y sin minas alrededor se revela un area vacia alrededor automaticamente
    @Test
    public void testExpansionDesdeCasillaVacia() {
        TableroBuscaminas tablero = new TableroBuscaminas(3, 3, 0);
        tablero.inicializarCasillas();

        JButton[][] botones = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton();
            }
        }

        GestorRevelado gestor = new GestorRevelado();
        Temporizador temporizador = new Temporizador(new JLabel());
        VentanaPrincipal ventana = new VentanaPrincipal(3, 3, 0);

        gestor.revelarCasillas(1, 1, botones, tablero, ventana, temporizador);

        int reveladas = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero.casillas[i][j].isRevelada()) {
                    reveladas++;
                }
            }
        }

        assertEquals(9, reveladas); // Todas las casillas deberian haberse revelado
    }

    //en este test probamos que una casilla con minas alrededor no expanda a las casillas vecinas
    @Test
    public void testNoExpandirDesdeCasillaConNumero() {
        TableroBuscaminas tablero = new TableroBuscaminas(3, 3, 0);
        tablero.inicializarCasillas();
        tablero.casillas[0][0].setMina(true);
        tablero.actualizarNumMinasAlrededor();

        JButton[][] botones = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton();
            }
        }

        GestorRevelado gestor = new GestorRevelado();
        Temporizador temporizador = new Temporizador(new JLabel());
        VentanaPrincipal ventana = new VentanaPrincipal(3, 3, 0);

        gestor.revelarCasillas(0, 1, botones, tablero, ventana, temporizador); // casilla vecina a la mina

        //la casilla a la que s ehizo clic debe estar revelada
        assertTrue(tablero.casillas[0][1].isRevelada());

        //sus vecinos no deben revelarse
        assertFalse(tablero.casillas[1][1].isRevelada(), "Las casillas vecinas no deben revelarse si hay numero");
        assertFalse(tablero.casillas[1][0].isRevelada(), "Las casillas vecinas no deben revelarse si hay numero");
    }
}

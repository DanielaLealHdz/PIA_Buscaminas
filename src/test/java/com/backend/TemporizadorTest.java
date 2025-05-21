package com.backend;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JLabel;

public class TemporizadorTest {

    //este test verifica que el temporizador incrementa segundos correctamente mientras corre
    @Test
    public void testTemporizadorCuentaCorrectamente() throws InterruptedException {
        JLabel etiqueta = new JLabel();
        Temporizador temporizador = new Temporizador(etiqueta);

        temporizador.start(); // inicia el hilo
        Thread.sleep(1200);   // esperamos un poco más de 1 segundo
        temporizador.detener(); // detenemos el hilo

        int segundos = temporizador.getSegundos();
        assertTrue(segundos >= 1, "El temporizador debe haber contado al menos 1 segundo");
    }

    @Test
    public void testTemporizadorReinicia() {
        JLabel etiqueta = new JLabel();
        Temporizador temporizador = new Temporizador(etiqueta);

        temporizador.reiniciar();
        assertEquals(0, temporizador.getSegundos());
        assertEquals("⏱ Tiempo: 0s", etiqueta.getText());
    }

    //temporizador devuelve 0 seg al iniciar
    @Test
    public void testTemporizadorIniciaEnCero() {
        Temporizador t = new Temporizador(new JLabel());
        assertEquals(0, t.getSegundos());
    }
}

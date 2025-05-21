package com.backend;

import javax.swing.*;
import java.awt.*;

public class MensajesJuego {


    public static void mostrarMensajeDerrota(JFrame ventanaActual, int filas, int columnas, int numMinas) {
        // Reproducir sonido de derrota
        ImagenesYAudios.reproducirSonidoDerrota();

        String[] opciones = { "🏠 Inicio", "🔁 Siguiente"};

        String mensaje = "<html><div style='text-align: center; width: 300px;'>"
                + "<h2 style='margin:0;'>💥 NIVEL FALLIDO</h2>"
                + "<p>Pisaste una mina.</p>"
                + "</div></html>";

        int resultado = JOptionPane.showOptionDialog(
                ventanaActual,
                mensaje,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[1]);

        if (resultado == 0) {
            ventanaActual.dispose();
            new PantallaInicio(); // Asegúrate de tener esta clase
        } else if (resultado == 1) {
            ventanaActual.dispose();
            new VentanaPrincipal(filas, columnas, numMinas);
        }
    }

    public static void mostrarMensajeVictoria(JFrame ventanaActual, int filas, int columnas, int numMinas) {
        // Reproducir sonido de victoria
        ImagenesYAudios.reproducirSonidoVictoria();

        String[] opciones = { "🏠 Inicio", "🔁 Siguiente" };

        String mensaje = "<html><div style='text-align: center; width: 300px;'>"
                + "🏆 <b>¡Felicidades!</b><br>"
                + "Lo lograste. Has completado el nivel."
                + "</div></html>";

        int resultado = JOptionPane.showOptionDialog(
                ventanaActual,
                mensaje,
                "¡Victoria!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[1]);

        if (resultado == 0) {
            ventanaActual.dispose();
            new PantallaInicio(); // Regresa al menú principal
        } else if (resultado == 1) {
            ventanaActual.dispose();
            new VentanaPrincipal(filas, columnas, numMinas); // Nueva partida
        }
    }
}

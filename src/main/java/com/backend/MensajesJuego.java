package com.backend;

import javax.swing.*;

/**
 * @apiNote esta es una clase cuya unica responsabilidad es mostrar mensajes emergentes cuando el
 * jugador gana o pierde y ejecutar las cciones correspondientes segun la opcion elegida
 */
public class MensajesJuego {


    /**
     * @param ventanaActual //ventana que se esta mostrando para cerrarla
     * @param filas //se usa si el jugador quiere reiniciar el mismo nivel
     * @param columnas
     * @param numMinas
     * @apiNote Se escucha un sonido al perder y muestra panel con opciones
     * se llama en TableroGrafico cuando se hace clic en una mina
     */
    public static void mostrarMensajeDerrota(JFrame ventanaActual, int filas, int columnas, int numMinas) {
        // Reproducir sonido de derrota
        ImagenesYAudios.reproducirSonidoDerrota();

        //Define las opciones del cuadro
        String[] opciones = { "🏠 Inicio", "🔁 Siguiente"};

        String mensaje = "<html><div style='text-align: center; width: 300px;'>"
                + "<h2 style='margin:0;'>💥 NIVEL FALLIDO</h2>"
                + "<p>Pisaste una mina.</p>"
                + "</div></html>";

        //Muestra un cuadro de dialogo con esas opciones y captura les respuesta
        int resultado = JOptionPane.showOptionDialog(
                ventanaActual,
                mensaje,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[1]);

        if (resultado == 0) { //pantalla de inicio
            ventanaActual.dispose();
            new PantallaInicio();
        } else if (resultado == 1) { //nueva partida con los mismos parametros
            ventanaActual.dispose();
            new VentanaPrincipal(filas, columnas, numMinas);
        }
    }


    /**
     * @param ventanaActual //ventana que se esta mostrando para cerrarla
     * @param filas
     * @param columnas
     * @param numMinas
     * @apiNote Funciona practicamente igual que derrota pero con victoria
     * se llama en GestorRevelado.verificarVictoria
     */
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

        if (resultado == 0) { //regresa al inicio
            ventanaActual.dispose();
            new PantallaInicio(); // Regresa al menú principal
        } else if (resultado == 1) { //otro juego
            ventanaActual.dispose();
            new VentanaPrincipal(filas, columnas, numMinas); // Nueva partida
        }
    }
}

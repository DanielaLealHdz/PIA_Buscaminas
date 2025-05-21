package com.backend;

import javax.swing.*;

/**
 * @apiNote Es una clase que extiende Thread, lo que significa que se ejecuta
 * en un hilo paralelo al resto del programa. Su funcion es
 * contar segundos, mostrar ese tiempo en una JLabel y detenerse
 */
public class Temporizador extends Thread {
    private int segundos = 0; //guarda cuantos segundos han pasado desde que inicio el hilo
    private boolean activo = true; //controla si el hilo debe seguir ejecutandose
    private JLabel etiqueta; //referencia a la etiqueta donde se muestra el tiempo

    /**
     * @param etiqueta //etiqueta donde se muestra el tiempo
     * @apiNote no se empieza a correr hasta que se llama .start() en VentanaPrincipal
     */
    public Temporizador(JLabel etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @apiNote entra en un bucle que se repite mientras activo sea true
     * pausa 1 segundo y aumenta segundos en 1
     */
    @Override
    public void run() {
        while (activo) {
            try {
                Thread.sleep(1000);
                segundos++;
                //Actualiza la etiqueta desde el hilo de la interfaz grafica, swing requiere que todas las actualizaciones visuales se hagan en el hilo principal
                SwingUtilities.invokeLater(() -> etiqueta.setText("⏱ Tiempo: " + segundos + "s"));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * @apiNote cambia la variable activo a false, loq ue hace que el bucle run se detenga
     * se llama al ganar o perder el juego
     */
    public void detener() {
        activo = false;
    }

}


package com.backend;

import javax.swing.*;

public class Temporizador extends Thread {
    private int segundos = 0;
    private boolean activo = true;
    private JLabel etiqueta;

    public Temporizador(JLabel etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public void run() {
        while (activo) {
            try {
                Thread.sleep(1000);
                segundos++;
                SwingUtilities.invokeLater(() -> etiqueta.setText("⏱ Tiempo: " + segundos + "s"));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void detener() {
        activo = false;
    }

    public void reiniciar() {
        segundos = 0;
        etiqueta.setText("⏱ Tiempo: 0s");
    }

    public int getSegundos() {
        return segundos;
    }
}


package com.backend;

import javax.swing.*;

public class ContadorVictorias {

    private int rachaActual;
    private JLabel etiquetaVisual;

    public static final ContadorVictorias instancia = new ContadorVictorias();

    public ContadorVictorias() {
        this.rachaActual = 0;
    }

    public void setEtiquetaVisual(JLabel etiqueta) {
        this.etiquetaVisual = etiqueta;
        actualizarEtiqueta();
    }

    public void incrementar() {
        rachaActual++;
    }

    public void reiniciar() {
        rachaActual = 0;
    }

    public int getRachaActual() {
        return rachaActual;
    }

    private void actualizarEtiqueta() {
        if (etiquetaVisual != null) {
            etiquetaVisual.setText("🔥 Racha: " + rachaActual);
        }
    }
}

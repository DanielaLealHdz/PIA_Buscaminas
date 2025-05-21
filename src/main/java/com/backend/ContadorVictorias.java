package com.backend;

import javax.swing.*;

/**
 * @apiNote esta clase es basicamente un contador global de la racha de victorias del jugador
 * hay una sola instancia compartida en todo el programa
 */
public class ContadorVictorias {

    private int rachaActual; //num actual de victorias seguidas
    private JLabel etiquetaVisual; //etiqueta que muestra el valor de la racha en pantalla

    //una unica instancia global que se puede usae desde cualquier parte
    public static final ContadorVictorias instancia = new ContadorVictorias();

    /**
     * @apiNote inicializa la racha en 0 y se llama una sola vez cuando se
     * crea instancia
     */
    public ContadorVictorias() {
        this.rachaActual = 0;
    }

    /**
     * @param etiqueta //la etiqueta visual que debe mostrar la racha
     * @apiNote se encarga de guaradr la etiqueta y actualizarla con el valor de recha actual
     * se llama desde VentanaPrincipal
     */
    public void setEtiquetaVisual(JLabel etiqueta) {
        this.etiquetaVisual = etiqueta;
        actualizarEtiqueta();
    }

    /**
     * @apiNote incrementa la racha en 1
     * se llama desde GestorRevelado cuando ganas una partida
     */
    public void incrementar() {
        rachaActual++;
    }

    /**
     * @apiNote se reinicia la racha desde 0
     * se llama cuando el jugador pisa una mina o reinicia la partida
     */
    public void reiniciar() {
        rachaActual = 0;
    }

    public int getRachaActual() {
        return rachaActual;
    }

    /**
     * @apiNote se encarga de actualizar la etiqueta
     */
    private void actualizarEtiqueta() {
        if (etiquetaVisual != null) {
            etiquetaVisual.setText("🔥 Racha: " + rachaActual);
        }
    }
}

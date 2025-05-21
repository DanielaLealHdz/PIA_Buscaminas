package com.backend;

import javax.swing.*;
import java.awt.*;

public class ContadorBanderas {
    private int banderasRestantes; //numero actual de banderas que le quedan al jugador
    private JLabel etiqueta; //etiqueta visual que se mostrara en la GUI

    /**
     * @param numInicialBanderas
     * @apiNote sirve para gestionar y mostrar cunatas banderas le quedan al jugador
     * y actualiza la etiqueta que se ve en la interfaz
     */
    public ContadorBanderas(int numInicialBanderas) {
        this.banderasRestantes = numInicialBanderas;
        this.etiqueta = new JLabel(); //creamos la etiqueta
        etiqueta.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        etiqueta.setForeground(Color.BLACK);
        actualizarEtiqueta(); //mostrar el numero inicial
    }

    public void incrementar() {
        banderasRestantes++;
        actualizarEtiqueta();
    }

    public void decrementar() {
        if (banderasRestantes > 0) {
            banderasRestantes--;
            actualizarEtiqueta();
        }
    }

    public int getBanderasRestantes() {
        return banderasRestantes;
    }

    public JLabel getEtiqueta() {
        return etiqueta;
    }

    public void actualizarEtiqueta() {
        etiqueta.setText("\uD83D\uDEA9 Banderas: " + banderasRestantes);
    }
}

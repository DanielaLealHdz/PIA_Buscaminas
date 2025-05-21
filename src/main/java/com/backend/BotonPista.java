package com.backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class BotonPista {

    public static JButton crearBotonPista(JButton[][] botones, TableroBuscaminas tablero, JLabel etiquetaBanderas, VentanaPrincipal ventana) {
        JButton btnPista = new JButton("💡 Pista (3)");
        btnPista.setFocusPainted(false);
        btnPista.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        btnPista.setBackground(new Color(210, 240, 255));

        btnPista.addActionListener((ActionEvent e) -> {
            if (ventana.getPistasDisponibles() <= 0) {
                JOptionPane.showMessageDialog(null, "Ya no tienes pistas disponibles.");
                return;
            }

            List<Casilla> reveladas = ventana.getCasillasReveladas();
            List<Casilla> candidatas = new ArrayList<>();

            for (Casilla r : reveladas) {
                List<Casilla> vecinas = tablero.obtenerCasillasAlrededor(r.getPosFila(), r.getPosColumna());
                for (Casilla vecina : vecinas) {
                    if (!vecina.isMina() && !vecina.isRevelada()) {
                        int f = vecina.getPosFila();
                        int c = vecina.getPosColumna();
                        if (botones[f][c].getIcon() == null && !candidatas.contains(vecina)) {
                            candidatas.add(vecina);
                        }
                    }
                }
            }


            if (candidatas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay pistas disponibles cerca de las casillas reveladas.");
                return;
            }

            Collections.shuffle(candidatas);
            Casilla pista = candidatas.get(0);
            int f = pista.getPosFila();
            int c = pista.getPosColumna();
            pista.setRevelada();
            ventana.getBotones()[f][c].setEnabled(false);
            ventana.getBotones()[f][c].setText(pista.getNumMinasAlrededor() == 0 ? " " : String.valueOf(pista.getNumMinasAlrededor()));
            ventana.getBotones()[f][c].setFont(new Font("Arial", Font.BOLD, 18));
            ventana.getBotones()[f][c].setForeground(Color.BLACK);
            ventana.getBotones()[f][c].setBackground(Color.LIGHT_GRAY);
            new GestorRevelado().verificarVictoria(ventana.getBotones(), tablero, ventana, ventana.getTemporizador());

            ventana.usarPista();
            btnPista.setText("💡 Pista (" + ventana.getPistasDisponibles() + ")");
        });

        return btnPista;
    }
}
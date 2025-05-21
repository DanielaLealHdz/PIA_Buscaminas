package com.backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

/**
 * @apiNote esta clase tiene un metodo para crear el boton pista que revela automaticamente una casilla segura cerca
 * de las ya reveladas anteriormente
 * se llama en botones
 */
public class BotonPista {

    /**
     * @param botones //para actualizar visualmente la casilla revelada
     * @param tablero //para obtener la logica del tablero y las casillas
     * @param etiquetaBanderas
     * @param ventana //para acceder a las pistas disponibles, temporizador, botones
     * @return
     */
    public static JButton crearBotonPista(JButton[][] botones, TableroBuscaminas tablero, JLabel etiquetaBanderas, VentanaPrincipal ventana) {
        JButton btnPista = new JButton("💡 Pista (3)");
        btnPista.setFocusPainted(false);
        btnPista.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        btnPista.setBackground(new Color(210, 240, 255));

        //Al hacer clic ejecuta una busqueda de una casilla segura no revelada cerca de las ya abiertas
        btnPista.addActionListener((ActionEvent e) -> {
            if (ventana.getPistasDisponibles() <= 0) {
                JOptionPane.showMessageDialog(null, "Ya no tienes pistas disponibles.");
                return;
            }

            //esto esta en ventana principal y devuelve una lista de las casillas que ya fueron reveladas
            List<Casilla> reveladas = ventana.getCasillasReveladas();
            List<Casilla> candidatas = new ArrayList<>();

            //para cada casilla revelada buscamos sus vecinas y si su vecina no es mina y no ha sido revelada
            for (Casilla r : reveladas) {
                List<Casilla> vecinas = tablero.obtenerCasillasAlrededor(r.getPosFila(), r.getPosColumna());
                for (Casilla vecina : vecinas) {
                    if (!vecina.isMina() && !vecina.isRevelada()) {
                        int f = vecina.getPosFila();
                        int c = vecina.getPosColumna();
                        //si no tiene una bandera y no esta ya en la lista de reveladas la agregamos a la lista de candidatas
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

            //entre esas candidatas escogemos al azar
            Collections.shuffle(candidatas);
            Casilla pista = candidatas.get(0);
            int f = pista.getPosFila();
            int c = pista.getPosColumna();
            pista.setRevelada(); //revelamos la pista
            ventana.getBotones()[f][c].setEnabled(false);
            ventana.getBotones()[f][c].setText(pista.getNumMinasAlrededor() == 0 ? " " : String.valueOf(pista.getNumMinasAlrededor()));
            ventana.getBotones()[f][c].setFont(new Font("Arial", Font.BOLD, 18));
            ventana.getBotones()[f][c].setForeground(Color.BLACK);
            ventana.getBotones()[f][c].setBackground(Color.LIGHT_GRAY);
            //verificamos si destapando la pista ganamos
            new GestorRevelado().verificarVictoria(ventana.getBotones(), tablero, ventana, ventana.getTemporizador());

            ventana.usarPista(); //decrementamos numero de pistas
            btnPista.setText("💡 Pista (" + ventana.getPistasDisponibles() + ")");
        });

        return btnPista;
    }
}
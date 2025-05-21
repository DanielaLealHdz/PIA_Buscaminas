package com.backend;

import javax.swing.*;
import java.awt.*;

/**
 * @apiNote crea los botones de control de juego, crea la etiqueta visual,
 * construye y devuelve un panel ocmpleto superiror e inferiror del huego, junto con el tablero
 * sirve para organizar visualmente los elementos que rodean al tablero de juego
 */
public class Botones {

    /**
     * @param filas
     * @param columnas
     * @param numMinas
     * @param ventanaActual
     * @apiNote Al hacer clic reinicia la racha, cierra la ventana catual y crea una nueva ventana principak
     * con los mismos parametros
     * se usa en crearPanelconbotonesytablero
     * @return
     */
    public static JButton crearBotonReiniciar(int filas, int columnas, int numMinas, JFrame ventanaActual) {
        JButton btnReiniciar = new JButton();

        btnReiniciar.setIcon(ImagenesYAudios.getIconoReiniciar());
        btnReiniciar.setPreferredSize(new Dimension(36, 36));
        btnReiniciar.setContentAreaFilled(false);
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.setToolTipText("Reiniciar juego");

        btnReiniciar.addActionListener(e -> {
            ContadorVictorias.instancia.reiniciar();
            ventanaActual.dispose(); // Cierra la ventana actual
            new VentanaPrincipal(filas, columnas, numMinas); // Reinicia con los mismos parámetros
        });

        return btnReiniciar;
    }

    /**
     * @param ventanaActual
     * @apiNote al hacer clic reinicia la racha, cierra la ventana actual y crea una nueva instancia de PantallaInicio
     * se usa en crearPpanelConBotonesYTablero
     * @return
     */
    public static JButton crearBotonHome(JFrame ventanaActual) {
        JButton btnHome = new JButton();

        btnHome.setIcon(ImagenesYAudios.getIconoHome());
        btnHome.setPreferredSize(new Dimension(36, 36));
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.setFocusPainted(false);
        btnHome.setToolTipText("Ir a pantalla inicial");

        return btnHome;
    }

    /**
     * @param rachaInicial
     * @apiNote crea y retorna un JLabel con texto de las rachas
     * se llama desde VentanaPrincipal y luego se la pasa a ContadorVictorias
     * @return
     */
    public static JLabel crearEtiquetaRacha(int rachaInicial) {
        JLabel etiquetaRacha = new JLabel("🔥 Racha: " + rachaInicial); // *** NUEVO
        etiquetaRacha.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14)); // *** NUEVO
        etiquetaRacha.setForeground(Color.BLACK); // *** NUEVO
        return etiquetaRacha; // *** NUEVO
    }

    /**
     * @param panelTablero
     * @param filas
     * @param columnas
     * @param numMinas
     * @param ventanaActual
     * @param etiquetaTiempo
     * @param etiquetaBanderas
     * @param etiquetaRacha
     * @param botones
     * @param tablero
     * @apiNote este es el metodo mas importante, crea y organiza todos los elementos graficos
     * se combinan con panelTablero y se devuelven como un solo JPanel general
     * se llama desde VentanaPricnipal.inicializarComponentes
     * @return
     */
    public static JPanel crearPanelConBotonesYTablero(JPanel panelTablero, int filas, int columnas, int numMinas, VentanaPrincipal ventanaActual, JLabel etiquetaTiempo, JLabel etiquetaBanderas, JLabel etiquetaRacha,  JButton[][] botones, TableroBuscaminas tablero) {
        // Contenedor vertical que agrupará tablero + botones
        JPanel panelContenedor = new JPanel(new BorderLayout());

        //Panel superiro con el temporizador y reinicio
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        etiquetaTiempo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        etiquetaTiempo.setForeground(Color.BLACK);

        // Botón reiniciar
        JButton btnReiniciar = crearBotonReiniciar(filas, columnas, numMinas, ventanaActual);
        JButton btnHome = crearBotonHome(ventanaActual);
        btnHome.addActionListener(e -> {
            ContadorVictorias.instancia.reiniciar(); // reinicia la racha tambien
            ventanaActual.dispose(); // cerrar la ventana actual
            new PantallaInicio();
        });

        panelSuperior.add(btnHome);
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.add(etiquetaRacha);
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.add(etiquetaTiempo);
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.add(btnReiniciar);

        //Panel inferior principal
        JPanel panelInferior = new JPanel(new BorderLayout());

        //Panel izquierdo banderas
        JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        etiquetaBanderas.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        etiquetaBanderas.setForeground(Color.BLACK);
        etiquetaBanderas.setText("🚩 Banderas: " + numMinas);
        panelIzquierdo.add(etiquetaBanderas);

        //Subpanel derecho pistas
        JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnPista = BotonPista.crearBotonPista(botones, tablero, etiquetaBanderas, ventanaActual);
        panelDerecho.add(btnPista);

        panelInferior.add(panelIzquierdo, BorderLayout.WEST);
        panelInferior.add(panelDerecho, BorderLayout.EAST);

        // Añadir elementos al panel
        panelContenedor.add(panelSuperior, BorderLayout.NORTH);
        panelContenedor.add(panelTablero, BorderLayout.CENTER);
        panelContenedor.add(panelInferior,BorderLayout.SOUTH);

        return panelContenedor;
    }
}

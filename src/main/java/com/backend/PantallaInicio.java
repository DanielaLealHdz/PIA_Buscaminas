package com.backend;

import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame { //hace que esta clase sea una ventana de swing

    public PantallaInicio() {
        super("Buscaminas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        inicializarComponentes(); //botones e instrucciones
        this.setVisible(true);
    }

    private void inicializarComponentes() {
        //creamos el panel principal y vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(40,40,40));

        //instrucciones
        JLabel instrucciones = new JLabel(
                "<html><center>" +
                        "<h2>Bienvenido al Buscaminas</h2>" +
                        "Selecciona una dificultad para comenzar.<br><br>" +
                        "<b>Instrucciones:</b><br>" +
                        "• Haz clic izquierdo para descubrir casillas<br>" +
                        "• Haz clic derecho para colocar o quitar una bandera<br>" +
                        "• Evita las bombas y revela todas las demás casillas<br>" +
                        "</center></html>"
        );
        instrucciones.setForeground(Color.WHITE);
        instrucciones.setAlignmentX(CENTER_ALIGNMENT);
        instrucciones.setHorizontalAlignment(SwingConstants.CENTER);

        //Separacion visual
        instrucciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //botones dificultad
        JButton botonFacil = new JButton("Facil");
        JButton botonMedio = new JButton("Intermedio");
        JButton botonDificil = new JButton("Dificil");

        //centrar los botones
        botonFacil.setAlignmentX(CENTER_ALIGNMENT);
        botonMedio.setAlignmentX(CENTER_ALIGNMENT);
        botonDificil.setAlignmentX(CENTER_ALIGNMENT);
        configurarBoton(botonFacil, new Color(60,180,75)); //verde
        configurarBoton(botonMedio, new Color(255,165,0)); //naranja
        configurarBoton(botonDificil, new Color(255,80,80)); //rojo

        //agregamos acciones
        botonFacil.addActionListener(e -> iniciarJuego(8,8,10));
        botonMedio.addActionListener(e -> iniciarJuego(12,12,20));
        botonDificil.addActionListener(e -> iniciarJuego(16,16,50));

        //agregamos los botones al panel
        panel.add(instrucciones);
        panel.add(Box.createVerticalStrut(10));
        panel.add(botonFacil);
        panel.add(Box.createVerticalStrut(5));
        panel.add(botonMedio);
        panel.add(Box.createVerticalStrut(5));
        panel.add(botonDificil);
        this.add(panel); //agregar panel a la ventana
        this.pack(); //se ajusta automaticamente el tamaño de la ventana para que se ajuste a los componentes que se añaden.
        this.setLocationRelativeTo(null); // Centrar la ventana
        this.setVisible(true);

    }

    private void iniciarJuego(int filas, int columnas, int minas){
        this.dispose();
        new VentanaPrincipal(filas, columnas, minas); //llamamos al constructor de la ventana principal
    }

    private void configurarBoton(JButton boton, Color color){
        boton.setAlignmentX(CENTER_ALIGNMENT);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boton.setFocusable(false);
    }

}


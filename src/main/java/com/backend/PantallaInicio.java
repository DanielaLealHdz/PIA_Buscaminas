package com.backend;

import javax.swing.*;
import java.awt.*;

/**
 * @apiNote Esta clase es la ventana inicial que se muestra al iniciar el programa,
 * su funcion principal es mostrar instrucciones y permitir al usuario seleccionar la dificultad
 */
public class PantallaInicio extends JFrame { //hace que esta clase sea una ventana de swing

    /**
     * apiNote Se crea la ventana inicial y se configura su comportamiento,
     * llama a inicializarComponentes()
     */
    public PantallaInicio() {
        super("Buscaminas"); //llama al constructor de JFrame y pone el título
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cierra la ventana al terminar
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); //centra la ventana en la pantalla
        this.setResizable(false);
        inicializarComponentes(); //botones e instrucciones
        this.setVisible(true);
    }

    /**
     * apiNote inicializa los componentes de la ventana inicial,
     * los botones y sus comportamientos y se asignan sus propiedades
     */
    private void inicializarComponentes() {
        //creamos el panel principal y vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //acomoda elementos de arriba hacia abajo
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

        //usa metodo configurar boton para aplicar estilos personalizados
        configurarBoton(botonFacil, new Color(60,180,75)); //verde
        configurarBoton(botonMedio, new Color(255,165,0)); //naranja
        configurarBoton(botonDificil, new Color(255,80,80)); //rojo

        //asocia eventos con acciones y llamamos iniciarJuego() cuando se hace clic en alguno de los botones
        botonFacil.addActionListener(e -> iniciarJuego(8,8,10));
        botonMedio.addActionListener(e -> iniciarJuego(12,12,20));
        botonDificil.addActionListener(e -> iniciarJuego(16,16,50));

        //agregamos componentes al panel principal y lo mostramos
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

    /**
     * @param filas //num de filas del tablero
     * @param columnas //num de columnas del tablero
     * @param minas //num de minas
     * @apiNote se llama desde los botones de dificultad, cierra la ventana inicial y llama a Ventana Principal con la configuracion deseada
     */
    private void iniciarJuego(int filas, int columnas, int minas){
        this.dispose();
        new VentanaPrincipal(filas, columnas, minas); //llamamos al constructor de la ventana principal
    }

    /**
     * @param boton //boton a configurar
     * @param color //color del boton
     * @apiNote configura los estilos de un boton
     */
    private void configurarBoton(JButton boton, Color color){
        boton.setAlignmentX(CENTER_ALIGNMENT); //centrar boton en la pantalla horizontalmente
        boton.setBackground(color);
        boton.setForeground(Color.WHITE); //color letras
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false); //elimina el borde azul
        boton.setBorderPainted(false); // elimina borde visual del boton
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //cambia el cursor del boton a una mano
        boton.setOpaque(true); //boton debe pintar su color de fondo sin transparente
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //añade espacio interno al boton
        boton.setFocusable(false); //no recibe foco del teclado
    }

}


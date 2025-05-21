package com.backend;

//Es el corazon de la interfaz, se construye la ventana y se conectan todos los elementos visuales con la lógica del juego
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

//Swing es una biblioteca (un conjunto de herramientas) que Java nos ofrece para crear interfaces gráficas de usuario (GUIs).

public class VentanaPrincipal extends JFrame {

    private int filas ;       // Numero de filas del tablero
    private int columnas; //Numero de columnas del tablero
    private int numMinas;// Numero de minas
    private boolean primerClic = true; //para asegurar que el primer clic no toque una mina
    private JButton[][] botones; // Matriz de botones (cada celda es un botón)
    private TableroBuscaminas tablero; // Tablero de juego logico
    private boolean juegoTerminado = false;
    private Temporizador temporizador; //lleva cuenta del tiempo jugado
    private JLabel etiquetaTiempo; //etiquetas que se muestran en pantalla
    private JLabel etiquetaBanderas;
    private int banderasRestantes; //estados de juego para el usuario
    private int pistasDisponibles = 3;
    private JLabel etiquetaRacha; // Muestra la racha en el panel
    private ContadorVictorias contadorVictorias = new ContadorVictorias();

    /**
     * @param filas //num de filas del tablero
     * @param columnas //num de columnas
     * @param numMinas //num de minas
     * apiNote En este metodo se configura la ventana(su tamaño y comportamiento)
     * y llama a inicializarComponentes() para armar el tablero
     */
    public VentanaPrincipal(int filas, int columnas, int numMinas) {
        super("Buscaminas"); // Llama al constructor de JFrame y pone el título
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        this.banderasRestantes = numMinas; // esto es para el contador de las banderas restantes

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Indica que cuando cierres la ventana, el programa también debe cerrarse por completo. Si no lo pones, el programa seguiría corriendo en segundo plano.
        this.setSize(700, 700); // Tamaño de la ventana
        this.setLocationRelativeTo(null); // Centrar la ventana
        this.setResizable(true); // Que se pueda cambiar el tamaño con el mouse
        tablero = new TableroBuscaminas(filas, columnas, numMinas);

        inicializarComponentes();
        this.pack(); //se ajusta automaticamente el tamaño de la ventana para que se ajuste a los componentes que se añaden.
        this.setVisible(true); // Mostrar la ventana
    }

    /**
     * apiNote Se crean las etiquetas para tiempo y banderas, el temporizador,
     * los botones del tablero con ayuda de TableroGrafico y el panel completo que se ve en la ventana(panelFinal)
     */
    private void inicializarComponentes() {

        etiquetaTiempo = new JLabel("⏱ Tiempo: 0s");
        etiquetaBanderas = new JLabel();
        etiquetaRacha = Botones.crearEtiquetaRacha(0);
        ContadorVictorias.instancia.setEtiquetaVisual(etiquetaRacha);
        temporizador = new Temporizador(etiquetaTiempo);
        // Inicializar la matriz de botones
        botones = new JButton[filas][columnas];

        JPanel panelTablero = TableroGrafico.crearTablero(filas, columnas, botones, tablero, this);
        JPanel panelFinal = Botones.crearPanelConBotonesYTablero(panelTablero, filas, columnas, numMinas, this, etiquetaTiempo, etiquetaBanderas,etiquetaRacha, botones, tablero);
        this.add(panelFinal);
    }

    /**
     * apiNote este metodo se llama una vez que pierdes, se muestran todas las bombas
     */
    public void revelarTodasLasBombas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla c = tablero.casillas[i][j];
                if (c.isMina()) {
                    botones[i][j].setIcon(ImagenesYAudios.getIconoBomba());
                    botones[i][j].setText(null);
                }
            }
        }
    }

    /**
     * apiNote todo el tablero se desactiva boton por boton si ganas o pierdes
     */
    public void desactivarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }

    //Getters y setters
    public JButton[][] getBotones() {
        return botones;
    }

    /**
     * @apiNote este metodo lo llamamos en BotonPista para obtener una lista
     * de las casillas que ya fueron reveladas
     */
    public List<Casilla> getCasillasReveladas() {
        List<Casilla> reveladas = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero.casillas[i][j].isRevelada()) {
                    reveladas.add(tablero.casillas[i][j]);
                }
            }
        }
        return reveladas;
    }

    public int getPistasDisponibles() {
        return pistasDisponibles;
    }

    public void usarPista() {
        pistasDisponibles--;
    }

    public void incrementarBanderas() {
        banderasRestantes++;
    }

    public void decrementarBanderas() {
        banderasRestantes--;
    }

    public int getBanderasRestantes() {
        return banderasRestantes;
    }

    public void actualizarEtiquetaBanderas() {
        etiquetaBanderas.setText("🚩 Banderas: " + banderasRestantes);
    }

    /**
     * apiNote este metodo se llama una vez que pierdes o ganas
     */
    public void detenerTemporizador() {
        temporizador.detener();
    }

    public void iniciarTemporizador() {
        temporizador.start();
    }

    public boolean esPrimerClic() {
        return primerClic;
    }

    public void setPrimerClic(boolean valor) {
        this.primerClic = valor;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean terminado) {
        this.juegoTerminado = terminado;
    }

    public Temporizador getTemporizador() {
        return temporizador;
    }
}


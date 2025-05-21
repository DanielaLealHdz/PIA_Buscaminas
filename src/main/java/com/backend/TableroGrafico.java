package com.backend;

// Clase que se encarga de construir y manejar el tablero gráfico (los botones y su comportamiento)
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class TableroGrafico {

    /**
     * @param filas //num de filas del tablero
     * @param columnas //num de columnas del tablero
     * @param botones //matriz de botones que representa visualmente las casillas
     * @param tablero //logica del juego(minas,casillas)
     * @param ventana //ventana principal para actualizar info como tiempo y banderas
     * @return
     */
    public static JPanel crearTablero(int filas, int columnas, JButton[][] botones, TableroBuscaminas tablero, VentanaPrincipal ventana) {

        //crea un panel con formato de cuadricula donde cada celda es un boton
        JPanel panelTablero = new JPanel(new GridLayout(filas, columnas));

        //vamos a necesitar el icono de bandera para ponerlas en el tablero
        ImageIcon iconoBandera = ImagenesYAudios.getIconoBandera();

        GestorRevelado gestor = new GestorRevelado();

        //inicializamos los botones, crea cada boton, les asigna un tamaño y lo añade al panel del tablero
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                final int fila = i;
                final int columna = j;

                botones[i][j] = new JButton();
                botones[i][j].setPreferredSize(new Dimension(50, 50));
                panelTablero.add(botones[i][j]);

                //asignamos comportamiento a los clics
                botones[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) { //se activa cuando el usuario hace clic en un boton
                        Casilla casilla = tablero.casillas[fila][columna];

                        //si es clic derecho vamos a poner o quitar bandera
                        if (SwingUtilities.isRightMouseButton(e)) {
                            //si el boton ya esta desabilitado return
                            if (!botones[fila][columna].isEnabled()) return;

                            //si el boton es una bandera entonces vamos a quitar la bandera e incrementar el num de banderas restantes
                            if (botones[fila][columna].getIcon() == iconoBandera) {
                                botones[fila][columna].setIcon(null);
                                ventana.incrementarBanderas();
                            } else { //si no es una bandera vamos a verificar que aun queden banderas y si si la agregamos a ese boton y quitamos una bandera del contador
                                if (ventana.getBanderasRestantes() > 0) {
                                    botones[fila][columna].setIcon(iconoBandera);
                                    ventana.decrementarBanderas();
                                }
                            }

                            //actualizamos la etiqueta de banderas en la ventana principal para que se actualice la cantidad de banderas restantes
                            ventana.actualizarEtiquetaBanderas();

                            //si es clic izquierdo
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            // si hay una bandera no pasa nada
                            if (botones[fila][columna].getIcon() == iconoBandera) return;

                            //si es el primer clic
                            if (ventana.esPrimerClic()) {
                                Set<Casilla> zonaSegura = tablero.generarZonaSeguraExpandida(fila, columna, 6);
                                tablero.generarMinasConZonaSegura(zonaSegura);
                                ventana.setPrimerClic(false);
                                ventana.iniciarTemporizador();

                                for (Casilla segura : zonaSegura) {
                                    gestor.revelarCasillas(segura.getPosFila(), segura.getPosColumna(), botones, tablero, ventana, ventana.getTemporizador() );
                                }
                            } else {
                                //si no es una mina y es el primer clic
                                if (casilla.isMina()) {
                                    botones[fila][columna].setIcon(ImagenesYAudios.getIconoBomba());
                                    botones[fila][columna].setText(null);
                                    ImagenesYAudios.reproducirSonidoExplosion();
                                    ventana.revelarTodasLasBombas();
                                    ventana.desactivarTablero();
                                    ventana.detenerTemporizador();
                                    ContadorVictorias.instancia.reiniciar();
                                    MensajesJuego.mostrarMensajeDerrota(ventana, filas, columnas, tablero.numMinas);
                                }
                                gestor.revelarCasillas(fila, columna, botones, tablero, ventana, ventana.getTemporizador() );
                            }
                        }
                    }
                });
            }
        }
        return panelTablero;
    }
}

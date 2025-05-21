package com.backend;

import javax.swing.*;
import java.awt.*;

/**
 * @apiNote Esta clase coordina la revelacion de casillas en el tablero cuando haces clic en
 * una casilla que no tiene minas al rededor.
 * Tiene dos funciones principales: RevelarCasillas(efecto domino) y verificarVictoria(evalua si se ha ganado)
 */
public class GestorRevelado {
    //Esta clase no necesita atributos porque simplemente coordina acciones en otras clases

    /**
     * @param fila //fila del boton en la matriz
     * @param columna // columna del boton en la matriz
     * @param botones //matriz de botones que representa el tablero
     * @param tablero //objeto que contiene la logica
     * @param ventana //ventana para acceder a metodos como victoria y temporizador
     * @param temporizador //referencia para detener el tiempo
     * @apiNote este metodo simula el efecto domino del buscaminas, cuando das clic en una casilla que no tiene minas al rededor
     * automaticamente se revelan las vecinas vacias. Este metodo se llama en TableroGrafico cuando haces el primer clic,
     * o cuando descubres una casilla con 0 minas al rededor
     */
    public void revelarCasillas(int fila, int columna, JButton[][] botones, TableroBuscaminas tablero, VentanaPrincipal ventana, Temporizador temporizador){

        //evita errores por intentar acceder a una casilla fuera del tablero
        if (fila < 0 || fila >= botones.length || columna < 0 || columna >= botones[0].length) return;

        //consulta la casilla en esa posicion
        Casilla c = tablero.casillas[fila][columna];

        //si ya fue revelada no hacemos nada
        if (c.isRevelada()) return;

        //marca como revelada en la logica y desactiva el boton en pantalla
        c.setRevelada();
        botones[fila][columna].setEnabled(false);

        //obtiene el num de minas al rededor
        int minas = c.getNumMinasAlrededor();

        //si hay 1 o mas minas cerca se muestra ese numero pero no se revelan vecinos por riesgo
        if(minas > 0){
            JButton boton = botones[fila][columna];
            boton.setText(String.valueOf(minas));
            boton.setFont(new Font("Arial", Font.BOLD, 18));
            boton.setBackground(Color.LIGHT_GRAY);
            verificarVictoria(botones, tablero, ventana, temporizador);
            return;
        }else{
            // si no hay minas al rededor mostramos la casilla vacia y expandir
            botones[fila][columna].setText(" ");
            botones[fila][columna].setBackground(Color.WHITE);
            botones[fila][columna].setEnabled(false);
        }

        //esto va a recorrer las 8 posiciones vecinas
        //si tiene 0 minas al rededor revelamos sus casillas vecinas
        for(int dx = -1; dx <= 1; dx++){
            for(int dy = -1; dy <= 1; dy++){
                if(!(dx == 0 && dy == 0)){ //seria esa casilla
                    revelarCasillas(fila + dx, columna + dy, botones, tablero, ventana, temporizador);
                }
            }
        }
        //despues de terminar la expansion verifica si el jugador ha ganado
        verificarVictoria(botones, tablero, ventana, temporizador);

    }

    /**
     * @param botones //para contar y manipular visuales
     * @param tablero //contiene la logica de minas y revelaciones
     * @param ventana //para verificar si ya termino el juego
     * @param temporizador //para detenerlo al ganar
     * @apiNote evalua si todas las casillas que no son minas ya fueron reveladas lo hace recorriendo
     * todas las casillas y cuenta el total de casillas que no tienen mina
     * y cuantas de esas ya fueron reveladas si ambos valores coinciden se despliega un mensaje de victroia
     * llamando a MostrarMensajeVictoria
     */
    public void verificarVictoria(JButton[][] botones, TableroBuscaminas tablero, VentanaPrincipal ventana, Temporizador temporizador) {
        int filas = botones.length;
        int columnas = botones[0].length;

        //evita la repeticion si ya se gano o perdio
        if(ventana.isJuegoTerminado())return;
        int totalSinMina = 0;
        int reveladaSinMina = 0;

        //cuenta las casillas sin mina y las reveladas y se comparan
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla c = tablero.casillas[i][j];
                if (!c.isMina()) {
                    totalSinMina++;
                    if(c.isRevelada()){
                        reveladaSinMina++;
                    }
                }
            }
        }
        //si todas las necesarias estan reveladas entonces el jugador gano
        if (totalSinMina == reveladaSinMina ) {
            ventana.setJuegoTerminado(true);
            ventana.desactivarTablero();
            temporizador.detener();
            ContadorVictorias.instancia.incrementar();
            MensajesJuego.mostrarMensajeVictoria(ventana, filas, columnas, tablero.getNumMinas());
        }
    }

}


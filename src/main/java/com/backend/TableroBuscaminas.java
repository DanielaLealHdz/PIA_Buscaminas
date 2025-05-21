package com.backend;

import java.util.*;

/**
 * @apiNote Creo que esta es mi clase favorita, se encarga de guardar todas las casillas,
 * asegurar la zona, colocar las minas, calcula cuantas minas hay alrededor de cada casilla y devuelve vecinas*/
public class TableroBuscaminas {
    Casilla[][] casillas; //matriz en donde estan las casillas

    int numFilas; // num de filas de la matriz
    int numColumnas; // num de columnas de la matriz
    int numMinas; // numero de minas que hay

    /**
     *
     * @param numFilas
     * @param numColumnas
     * @param numMinas
     * @apiNote Este simplemente es el constructor y llama al metodo inicializar casillas,
     * necesita saber cuántas casillas crear lógicamente, y dónde poner las minas.
     * Se llama desde VentanaPrincipal
     */
    public TableroBuscaminas(int numFilas, int numColumnas, int numMinas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.numMinas = numMinas;
        inicializarCasillas();
    }

    /**
     * @apiNote En este metodo se crea la matriz (el tablero) y en cada posicion se van creando nuevas casillas con una ubicacion especifica
     */
    public void inicializarCasillas() {
        casillas = new Casilla[this.numFilas][this.numColumnas]; //Aquí estás creando el espacio para el tablero, una matriz bidimensional, por ejemplo de 9x9. Pero las celdas aun estan vacias
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = new Casilla(i,j); //En cada posición, estás creando una nueva celda (Casillas) con su ubicación específica
            }
        }
    }

    /**
     * @param filaInicial //casilla donde el jugador hizo el primer clic
     * @param columnaInicial //casilla donde el jugador hizo el primer clic
     * @param cantidadCasillasSeguras //la cantidad de casillas seguras que queremos destapar
     * @apiNote Cuando el jugador hace su primer clic, queremos asegurarnos de que no caiga en una mina.
     * Pero además, este código crea una zona segura expandida; un grupo de casillas conectadas que no tienen minas,
     * y que pueden ser reveladas sin peligro. Devuelve un Set<Casilla> con todas esas casillas seguras seleccionadas.
     * Se llama desde TableroGrafico en primer clic
     */
    public Set<Casilla> generarZonaSeguraExpandida(int filaInicial, int columnaInicial, int cantidadCasillasSeguras) {
        Set<Casilla> zonaSegura = new HashSet<>(); //casillas que formaran la zona segura sin duplicados
        Queue<Casilla> cola = new LinkedList<>(); //agregamos casillas a la cola para irlas explorando una por una

        //añadimos la casilla que el jugador dio clic a la zona segura y cola
        Casilla inicial = casillas[filaInicial][columnaInicial];
        zonaSegura.add(inicial);
        cola.add(inicial);

        //mientras haya vecinos por revisar y no hayamos llegado al num deseado de casillas seguras
        while (!cola.isEmpty() && zonaSegura.size() < cantidadCasillasSeguras) {
            Casilla actual = cola.poll(); //sacamos una casilla de la cola para procesarla
            List<Casilla> vecinos = obtenerCasillasAlrededor(actual.getPosFila(), actual.getPosColumna()); //obtenemos sus 8 vecinos
            Collections.shuffle(vecinos); // Aleatoriza vecinos para que nunca se expanda igual

            for (Casilla vecina : vecinos) {
                //nos aseguramos de que no este ya en la zona segura y de que no sea una mina
                if (!zonaSegura.contains(vecina) && !vecina.isMina()) {
                    zonaSegura.add(vecina);
                    cola.add(vecina);

                    if (zonaSegura.size() >= cantidadCasillasSeguras) break; //si ya tenemos suficientes casillas terminamos la expansion del ciclo for
                }
            }
        }

        return zonaSegura;
    }

    /**
     * @param zonaSegura //set con las casillas que ya estan seguras
     * @apiNote Este metodo lo que hace es generar las minas ya tomando en cuenta que no pueden estar en la zona segura,
     * y al finalizar de poner las minas llama al metodo para actualizar el numero de las casillas al rededor de las minas.
     * Se llama desde TableroGrafico despues de crear la zona segura en el primer clic
     */
    public void generarMinasConZonaSegura(Set<Casilla> zonaSegura) {
        int minasGeneradas = 0;

        while (minasGeneradas < numMinas) {
            int f = (int)(Math.random() * numFilas);
            int c = (int)(Math.random() * numColumnas);
            Casilla candidata = casillas[f][c];

            if (!candidata.isMina() && !zonaSegura.contains(candidata)) {
                candidata.setMina(true);
                minasGeneradas++;
            }
        }

        actualizarNumMinasAlrededor();
    }

    /**
     * @apiNote Este metodo se encarga de actualizar el numero de minas alrededor de cada casilla, vamos a recorrer la matriz y verificar
     * si la casilla es mina, en ese caso vamos a recorrer todas las casillas alrededor de esa casilla (obtenerCasillasAlrededor)
     * y incrementar el numero de la casilla al rededor(incrementarNumeroMinasAlrededor).
     */
    void actualizarNumMinasAlrededor(){
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j].isMina()){
                    List<Casilla> casillasAlrededor = obtenerCasillasAlrededor(i,j);   //Si hay una mina, se llama al metodo obtenerCasillasAlrededor(i,j) para que regrese las 8 casillas alrededor (arriba, abajo, derecha, izquierda y diagonales)
                    casillasAlrededor.forEach((c)->c.incrementarNumeroMinasAlrededor()); //para cada casilla alrededor, se incrementa su numero de casilla

                }
            }
        }
    }

    /**
     *
     * @param posFila es la posicion en la fila en la que se encuentra la casilla que contiene una bomba
     * @param posColumna es la posicion en la columna en la que se encuentra la casilla que contiene una bomba
     * @apiNote Este metodo devuelve una lista de casillas que rodean la posicion que se paso en los parametros
     * @return
     */
    List<Casilla> obtenerCasillasAlrededor(int posFila, int posColumna){
        List<Casilla> listaCasillas = new LinkedList<Casilla>();
        for(int i=0; i<8;i++){ //8 por que es el numero de casillas que puede haber al rededor de una casilla
            int tmpPosFila = posFila;
            int tmpPosColumna = posColumna;
            switch (i){                                //esto calculara la posicion de cada vecino posible
                case 0: tmpPosFila--;break; //Arriba
                case 1: tmpPosFila--;tmpPosColumna++;break; //Arriba derecha
                case 2: tmpPosColumna++;break; //Derecha
                case 3: tmpPosColumna++; tmpPosFila++;break; //Derecha abajo
                case 4: tmpPosFila++;break; //Abajo
                case 5: tmpPosFila++;tmpPosColumna--;break; //Abajo izquierda
                case 6: tmpPosColumna--;break; //Izquierda
                case 7: tmpPosFila--;tmpPosColumna--;break; //Izquierda Arriba
            }
            //se verifica que la casilla vecina este dentro del tablero y que no sea una posicion como -1
            if (tmpPosFila >=0 && tmpPosFila<this.casillas.length && tmpPosColumna >=0 && tmpPosColumna < this.casillas[0].length){
                listaCasillas.add(casillas[tmpPosFila][tmpPosColumna]); //si esta dentro entonces añadimos esa casilla a la lista de vecinas
            }
        }
        return listaCasillas; //devuelve la lista con las casillas vecinas
    }

    public int getNumMinas() {
        return numMinas;
    }

}


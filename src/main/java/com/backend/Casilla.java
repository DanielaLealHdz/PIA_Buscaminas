package com.backend;

public class Casilla {
    private int posFila;  //posicion de la casilla en la fila
    private int posColumna; //posicion de la casilla en la columna
    private boolean mina; //si la casilla tiene una mina o no
    private int numMinasAlrededor; // cuantas minas tiene al rededor la casilla
    private boolean revelada; //si la casilla ya fue revelada o no

    /**
     * @param posFila
     * @param posColumna
     * @apiNote Esto es el constructor de la casilla que tiene una posicion en una columna y fila
     */
    public Casilla(int posFila, int posColumna) {
        this.posFila = posFila;
        this.posColumna = posColumna;
    }

    //Getters y Setters
    public int getPosFila() {
        return posFila;
    }

    public void setPosFila(int posFila) {
        this.posFila = posFila;
    }

    public int getPosColumna() {
        return posColumna;
    }

    public void setPosColumna(int posColumna) {
        this.posColumna = posColumna;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public int getNumMinasAlrededor() {
        return numMinasAlrededor;
    }

    public void setNumMinasAlrededor(int numMinasAlrededor) {
        this.numMinasAlrededor = numMinasAlrededor;
    }

    //se usa en el metodo actualizarNumMinasAlrededor de TableroBuscaminas
    public void incrementarNumeroMinasAlrededor() {
        this.numMinasAlrededor++;
    }

    public boolean isRevelada() {
        return revelada;
    }

    public void setRevelada() {
        this.revelada = true;
    }
}


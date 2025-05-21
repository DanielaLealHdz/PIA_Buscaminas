package com.backend;

public class ContadorVictorias {
    private int rachaActual;

    public ContadorVictorias() {
        this.rachaActual = 0;
    }

    public void incrementar() {
        rachaActual++;
    }

    public void reiniciar() {
        rachaActual = 0;
    }

    public int getRachaActual() {
        return rachaActual;
    }
}

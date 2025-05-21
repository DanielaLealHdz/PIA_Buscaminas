package com.backend;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class ImagenesYAudios {

    // ----------------------- IMÁGENES -----------------------

    public static ImageIcon cargarImagenEscalada(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    public static ImageIcon getIconoBomba() {
        return cargarImagenEscalada("src/imagenes/bomba.png", 32, 32);
    }

    public static ImageIcon getIconoBandera() {
        return cargarImagenEscalada("src/imagenes/bandera.png", 32, 32);
    }

    public static ImageIcon getIconoReiniciar() {
        return cargarImagenEscalada("src/imagenes/reiniciar.png", 24, 24);
    }

    public static ImageIcon getIconoHome() {
        return cargarImagenEscalada("src/imagenes/home.png", 24, 24);
    }


    // ----------------------- AUDIOS -----------------------

    public static void reproducirSonido(String ruta) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ruta).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir sonido: " + e.getMessage());
        }
    }

    public static void reproducirSonidoExplosion() {
        reproducirSonido("src/sonidos/sonido_bomba.wav");
    }

    public static void reproducirSonidoVictoria() {
        reproducirSonido("src/sonidos/sonido_victoria.wav");
    }

    public static void reproducirSonidoDerrota() {
        reproducirSonido("src/sonidos/sonido_fail.wav");
    }
}


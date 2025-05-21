package com.backend;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

/**
 * @apiNote esta clase se centra en cargar y escalar las imagenes y la reproduccion de sonidos
 */
public class ImagenesYAudios {

    // IMÁGENES

    /**
     * @param ruta //ubicacion de la imagen
     * @param ancho // dimensiones deseadas
     * @param alto //dimensiones deseadas
     * @apiNote Se carga una imagen, la redimensiona y devuelve la imagen en forma de ImageIcon
     * se reutiliza en getIconoBomba, getIconoBandera, getIconoReiniciar, getIconoHome
     * y se usa en TableroGrafico(banderas y bombas) y Botones(iconos de reinicio y home)
     */
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


    // AUDIOS

    /**
     * @param ruta //direccion del archivo .wav
     * @apiNote se encarga de abrir el archivo, crear un clip y lo reproduce
     * lo usan reproducirSonidoExplosion, reproducirSonidoVictoria y reproducirSonidoDerrota
     * se usan en TableroGrafico y MensajesJuego
     */
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


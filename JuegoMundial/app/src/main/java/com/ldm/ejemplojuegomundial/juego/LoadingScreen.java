package com.ldm.ejemplojuegomundial.juego;

import com.ldm.ejemplojuegomundial.Juego;
import com.ldm.ejemplojuegomundial.Graficos;
import com.ldm.ejemplojuegomundial.Pantalla;
import com.ldm.ejemplojuegomundial.Graficos.PixmapFormat;


public class LoadingScreen extends Pantalla{
    public LoadingScreen(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        Graficos g = juego.getGraphics();
        Assets.fondo = g.newPixmap("fondo.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.menuprincipal = g.newPixmap("menuprincipal.png", PixmapFormat.ARGB4444);
        Assets.botones = g.newPixmap("botones.png", PixmapFormat.ARGB4444);
        Assets.ayuda1 = g.newPixmap("ayuda1.png", PixmapFormat.ARGB4444);
        Assets.ayuda2 = g.newPixmap("ayuda2.png", PixmapFormat.ARGB4444);
        Assets.ayuda3 = g.newPixmap("ayuda3.png", PixmapFormat.ARGB4444);
        Assets.numeros = g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
        Assets.preparado = g.newPixmap("preparado.png", PixmapFormat.ARGB4444);
        Assets.menupausa = g.newPixmap("menupausa.png", PixmapFormat.ARGB4444);
        Assets.finjuego = g.newPixmap("finjuego.png", PixmapFormat.ARGB4444);
        Assets.escaloniarriba = g.newPixmap("escaloniarriba.png", PixmapFormat.ARGB4444);
        Assets.escaloniizquierda = g.newPixmap("escaloniizquierda.png", PixmapFormat.ARGB4444);
        Assets.escaloniabajo = g.newPixmap("escaloniabajo.png", PixmapFormat.ARGB4444);
        Assets.escaloniderecha = g.newPixmap("escaloniderecha.png", PixmapFormat.ARGB4444);
        Assets.tripulacion = g.newPixmap("tripulacion.png", PixmapFormat.ARGB4444);
        Assets.botin1 = g.newPixmap("botin1.png", PixmapFormat.ARGB4444);
        Assets.botin2 = g.newPixmap("botin2.png", PixmapFormat.ARGB4444);
        Assets.botin3 = g.newPixmap("botin3.png", PixmapFormat.ARGB4444);
        Assets.botin4 = g.newPixmap("botin4.png", PixmapFormat.ARGB4444);
        Assets.pulsar = juego.getAudio().nuevoSonido("pulsar.ogg");
        Assets.gol = juego.getAudio().nuevoSonido("gol.ogg");
        Assets.ambiente = juego.getAudio().nuevaMusica("argentina.mp3");
        Assets.messiaudio = juego.getAudio().nuevoSonido("messiaudio.ogg");
        Assets.banana = juego.getAudio().nuevoSonido("banana.ogg");
        Configuraciones.cargar();
        juego.setScreen(new MainMenuScreen(juego));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
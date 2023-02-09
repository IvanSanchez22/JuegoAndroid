package com.ldm.ejemplojuegomundial.juego;

import com.ldm.ejemplojuegomundial.Pantalla;
import com.ldm.ejemplojuegomundial.androidimpl.AndroidJuego;

public class JuegoMundial extends AndroidJuego {



    @Override
    public Pantalla getStartScreen() {

        return new LoadingScreen(this);
    }



}

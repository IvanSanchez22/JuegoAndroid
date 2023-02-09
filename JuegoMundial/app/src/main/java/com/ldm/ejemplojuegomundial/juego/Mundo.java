package com.ldm.ejemplojuegomundial.juego;

import java.util.Random;

public class Mundo {
    static final int MUNDO_ANCHO = 10;
    static final int MUNDO_ALTO = 13;
    static final float TICK_INICIAL = 0.5f;
    static final float TICK_DECREMENTO = 0.05f;

    public Argentina jollyroger;
    public Botin botin;
    public Botin botin2;
    public boolean finalJuego = false;
    public int puntuacion = 0;

    boolean[][] campos = new boolean[MUNDO_ANCHO][MUNDO_ALTO];
    Random random = new Random();
    float tiempoTick = 0;
    static float tick = TICK_INICIAL;

    public Mundo() {
        jollyroger = new Argentina();
        colocarBotin();
    }

    private void colocarBotin() {
        for (int x = 0; x < MUNDO_ANCHO; x++) {
            for (int y = 0; y < MUNDO_ALTO; y++) {
                campos[x][y] = false;
            }
        }
        int len = jollyroger.partes.size();
        for (int i = 0; i < len; i++) {
            Plantilla parte = jollyroger.partes.get(i);
            campos[parte.x][parte.y] = true;
        }

        int botinX = random.nextInt(MUNDO_ANCHO);
        int botinY = random.nextInt(MUNDO_ALTO);
        int botinx2 = random.nextInt(MUNDO_ANCHO);
        int botiny2 = random.nextInt(MUNDO_ALTO);

        while (true) {
            if (!campos[botinX][botinY])
                break;
            if (!campos[botinx2][botiny2])
                break;
            botinX += 1;
            botinx2 += 1;
            if (botinX >= MUNDO_ANCHO) {
                botinX = 0;
                botinY += 1;
                if (botinY >= MUNDO_ALTO) {
                    botinY = 0;
                }
            }
            if (botinx2 >= MUNDO_ANCHO) {
                botinx2 = 0;
                botiny2 += 1;
                if (botiny2 >= MUNDO_ALTO) {
                    botinY = 0;
                }
            }
        }
        botin = new Botin(botinX, botinY, random.nextInt(3));
        botin2 = new Botin(botinx2, botiny2, random.nextInt(4));
    }

    public void crecemos(){
        jollyroger.abordaje();
        if (jollyroger.partes.size() == MUNDO_ANCHO * MUNDO_ALTO) {
            finalJuego = true;
            return;
        } else {
            colocarBotin();
        }

        if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
            tick -= TICK_DECREMENTO;
        }
    }
    public void decrecemos(){
        jollyroger.retirada();
        if (jollyroger.partes.size() == MUNDO_ANCHO * MUNDO_ALTO) {
            finalJuego = true;
        } else {
            colocarBotin();
        }

    }

    public void update(float deltaTime) {
        if (finalJuego)
            return;

        tiempoTick += deltaTime;

        while (tiempoTick > tick) {
            tiempoTick -= tick;
            jollyroger.avance();
            if (jollyroger.comprobarChoque()) {
                finalJuego = true;
                return;
            }

            Plantilla head = jollyroger.partes.get(0);
            if (head.x == botin.x && head.y == botin.y) {
                if (botin.tipo == 1 || botin.tipo == 2) {
                    puntuacion += 5;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.gol.play(1);
                    crecemos();
                } else if (botin.tipo == 0) {
                    puntuacion += 10;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.gol.play(1);
                    crecemos();
                } else {
                    puntuacion -= 5;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.banana.play(1);
                    decrecemos();
                }
            } else if (head.x == botin2.x && head.y == botin2.y) {
                if (botin2.tipo == 1 || botin2.tipo == 2) {
                    puntuacion += 5;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.gol.play(1);
                    crecemos();
                } else if (botin2.tipo == 0) {
                    puntuacion += 10;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.gol.play(1);
                    crecemos();
                } else {
                    if (puntuacion > 0)
                        puntuacion -= 5;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.banana.play(1);
                    decrecemos();
                }
            }
        }
    }
}



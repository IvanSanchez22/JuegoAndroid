package com.ldm.ejemplojuegomundial.juego;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.ldm.ejemplojuegomundial.androidimpl.AndroidJuego;

public class Configuraciones {
    public static boolean sonidoHabilitado = true;
    //Mostramos las 5 puntuaciones mejores empiezan todas en 0
    public static int[] maxPuntuaciones = new int[5];

    public static void cargar() {
        SharedPreferences sharedPreferences = AndroidJuego.ctx.getSharedPreferences("puntuaciones", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        maxPuntuaciones = gson.fromJson(sharedPreferences.getString("puntuacion", gson.toJson(new int[5])),
                new TypeToken<int[]>(){}.getType());
    }

    public static void save() {
      SharedPreferences sharedPreferences = AndroidJuego.ctx.getSharedPreferences("puntuaciones", Context.MODE_PRIVATE);
      SharedPreferences.Editor miEdit = sharedPreferences.edit();
      Gson gson = new Gson();
      miEdit.putString("puntuacion", gson.toJson(maxPuntuaciones));
      miEdit.putBoolean("sonidoHabilitado", sonidoHabilitado);
      miEdit.apply();
    }

    public static void addScore(int score) {
        for (int i = 0; i < 5; i++) {
            if (maxPuntuaciones[i] < score) {
                for (int j = 4; j > i; j--)
                    maxPuntuaciones[j] = maxPuntuaciones[j - 1];
                maxPuntuaciones[i] = score;
                break;
            }
        }
    }
}
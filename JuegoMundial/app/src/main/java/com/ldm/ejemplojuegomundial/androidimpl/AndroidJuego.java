package com.ldm.ejemplojuegomundial.androidimpl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import com.ldm.ejemplojuegomundial.Audio;
import com.ldm.ejemplojuegomundial.FileIO;
import com.ldm.ejemplojuegomundial.Juego;
import com.ldm.ejemplojuegomundial.Graficos;
import com.ldm.ejemplojuegomundial.Input;
import com.ldm.ejemplojuegomundial.Pantalla;
import com.ldm.ejemplojuegomundial.juego.R;

public abstract class AndroidJuego extends Activity implements Juego {
    public static Context ctx;
    AndroidFastRenderView renderView;
    Graficos graficos;
    Audio audio;
    Input input;
    FileIO fileIO;
    Pantalla pantalla;
    WakeLock wakeLock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 480 : 320;
        int frameBufferHeight = isLandscape ? 320 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);

        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        ctx = getBaseContext();
        renderView = new AndroidFastRenderView(this, frameBuffer);
        graficos = new AndroidGraficos(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        pantalla = getStartScreen();
        setContentView(renderView);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myapp:GLGame");
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        pantalla.resume();
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        pantalla.pause();

        if (isFinishing())
            pantalla.dispose();
    }


    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graficos getGraphics() {
        return graficos;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Pantalla pantalla) {
        if (pantalla == null)
            throw new IllegalArgumentException("Pantalla no debe ser null");

        this.pantalla.pause();
        this.pantalla.dispose();
        pantalla.resume();
        pantalla.update(0);
        this.pantalla = pantalla;
    }

    public Pantalla getCurrentScreen() {
        return pantalla;
    }
}
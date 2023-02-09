package com.ldm.ejemplojuegomundial;

import com.ldm.ejemplojuegomundial.Graficos.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}


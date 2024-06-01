package com.MagnetoGameLib.main;

import java.awt.*;

public class Background {
    public Texture tex = Game.getTex();
    private float w, h, x, y;

    public Background(int w, int h, int x, int y){
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g){
        g.drawImage(tex.getBackground(), (int)x, (int)y, (int)w, (int)h, null);
    }

    public void tick(){}
}

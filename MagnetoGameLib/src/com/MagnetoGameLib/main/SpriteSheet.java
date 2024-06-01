package com.MagnetoGameLib.main;

import java.awt.image.BufferedImage;

public class SpriteSheet{
    private BufferedImage img;

    public SpriteSheet(BufferedImage img){this.img = img;}

    public BufferedImage grabImage(int col, int row, int w, int h){
        BufferedImage sImg = img.getSubimage((col*w) - w,(row*h) - h, w, h);
        return sImg;
    }
}

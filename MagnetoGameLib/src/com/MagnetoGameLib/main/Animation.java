package com.MagnetoGameLib.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage actImg;

    public Animation(int speed, BufferedImage... sprites){
        this.speed = speed;
        frames = sprites.length;
        images = new BufferedImage[sprites.length];
        for(int i = 0; i < sprites.length; i++){
            images[i] = sprites[i];
        }
    }

    public Animation(){}

    public void runAnim(){
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }

    public void nextFrame(){
        // if(count == frames)
        //      playedOnce = true;

        count = count % frames;
        actImg = images[count];
        count++;
    }

    public BufferedImage[] getImages(){
        return images;
    }

    public void setImage(BufferedImage[] images){
        this.images = images;
    }

    public void drawAnim(Graphics g, int x, int y){
        g.drawImage(actImg, x, y, null);
    }

    public void drawAnim(Graphics g, int x, int y, int sX, int sY) {
        g.drawImage(actImg, x, y, sX, sY, null);
    }

    public int getLength(){
        return frames;
    }

    public void resetAnim(){
        index = 0;
        count = 0;
        actImg = images[count];
    }
}

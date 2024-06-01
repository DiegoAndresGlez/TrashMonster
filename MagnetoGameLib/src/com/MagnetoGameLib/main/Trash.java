package com.MagnetoGameLib.main;

import java.awt.*;
import java.util.Random;
import java.util.Timer;

public class Trash extends GameObject{
    private Random r = new Random();

    private Animation idleAnim;
    private Handler handler;

    Texture tex = Game.getTex();

    public Trash(int x, int y, ID id, Handler handler){
        super(x, y, id);
        idleAnim = new Animation(64, tex.plasticBottleSprites[0], tex.plasticBottleSprites[1], tex.plasticBottleSprites[2],
                tex.plasticBottleSprites[3]);
        this.handler = handler;
    }
    public void tick(){
        y += velY;
        x += velX;
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, 16*2, 28*2);
    } //para dibujar hitboxes
    public void render(Graphics g){
        //if(id == ID.Coin){
        idleAnim.drawAnim(g, x, y);
        idleAnim.runAnim();
        //}
        //g.fillOval(x, y, 16, 24);
    }
}


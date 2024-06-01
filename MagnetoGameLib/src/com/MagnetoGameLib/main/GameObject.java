package com.MagnetoGameLib.main;

//Player, trash, etc, cada uno es objeto del juego (GameObject)
//Player va heredar de GameObject

import java.awt.*;

public abstract class GameObject {
    //Protected solo puede ser accesado por el propio objeto
    protected int x, y, w, h;
    protected ID id; //id de cada objeto
    protected int velX = 0, velY = 0;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Rectangle getBounds(){
        return new Rectangle(x, y, w, h);
    } //para dibujar hitboxes

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY(){
        return y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelY() {
        return velY;
    }
}

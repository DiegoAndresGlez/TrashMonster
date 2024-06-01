package com.MagnetoGameLib.main;

import java.awt.*;

public class Block extends GameObject{
    Texture tex = Game.getTex();

    public Block(int x, int y, ID id){ //puede escribir type de block
        super(x, y, id);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.drawImage(tex.wallTileSprite[0], 300, 300, null);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, w, h);
    } //para dibujar hitboxes
}

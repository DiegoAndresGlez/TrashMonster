package com.MagnetoGameLib.main;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject{
    private Animation idleAnim = null;
    private Animation runRightAnim = null;
    private Animation runLeftAnim = null;
    Texture tex = Game.getTex();
    private boolean faceRight = true;
    private HUD hud;

    public Enemy(int x, int y, ID id, HUD hud){
        super(x, y, id);
        this.hud = hud;
        idleAnim = new Animation(8,
                tex.trashMonsterIdleSprites[0],
                tex.trashMonsterIdleSprites[1],
                tex.trashMonsterIdleSprites[2],
                tex.trashMonsterIdleSprites[3]);
        runRightAnim = new Animation(8,
                tex.trashMonsterRunRightSprites[0],
                tex.trashMonsterRunRightSprites[1],
                tex.trashMonsterRunRightSprites[2],
                tex.trashMonsterRunRightSprites[3]);
        runLeftAnim = new Animation(8,
                tex.trashMonsterRunLeftSprites[0],
                tex.trashMonsterRunLeftSprites[1],
                tex.trashMonsterRunLeftSprites[2],
                tex.trashMonsterRunLeftSprites[3]);
        if(hud.getTrashCount() >= 50){
            velX = 11;
            velY = 11;
        }else{
            velX = 3;
            velY = 3;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 28*8 , 36*8);
    } //para dibujar hitboxes
    public void tick(){
        x += velX;
        y += velY;

        if(hud.getTrashCount() < 50){
            if(y == 0 || y == Game.HEIGHT - 300){
                velY+=2;
                System.out.println("velY: " + velY);
            }

            if(x == 0 || x == Game.WIDTH - 256){
                velX+=2;
                System.out.println("velX: " + velX);
            }

            if(y <= 0 || y >= Game.HEIGHT - 300){
                velY *= -1;
                velY--;
            }
            if(x <= 0 || x >= Game.WIDTH - 256){
                velX *= -1;
                velX--;
            }
        }else{
            if(y == 0 || y == Game.HEIGHT - 300){
                velY+=1;
            }

            if(x == 0 || x == Game.WIDTH - 256){
                velX+=1;
            }

            if(y <= 0 || y >= Game.HEIGHT - 300){
                velY *= -1;
            }
            if(x <= 0 || x >= Game.WIDTH - 256){
                velX *= -1;
            }
        }

        if(velX < 0)
            faceRight = false;
        else
            faceRight = true;

        //x = clamp(x, 0, Game.WIDTH-80);
        //y = clamp(y, 0, Game.HEIGHT-100);
        idleAnim.runAnim();
        runRightAnim.runAnim();
        runLeftAnim.runAnim();

    }
    @Override
    public void render(Graphics g) {
        //displayHitBox(g);

        if (velX != 0 || (velY > 0 || velY < 0)) {
            if (faceRight) {
                //runRightAnim.runAnim();
                runRightAnim.drawAnim(g, x, y);

            } else {
                //runLeftAnim.runAnim();
                runLeftAnim.drawAnim(g, x, y);
            }
        } else if(velX == 0 && velY == 0){
            if (faceRight) {
                idleAnim.drawAnim(g, x, y);
            }
        }
    }

    public void displayHitBox(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green); //dibujando hitboxes (comentar para no verse)
        g2d.draw(getBounds());
    }

    public static int clamp(int var, int min, int max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }
}

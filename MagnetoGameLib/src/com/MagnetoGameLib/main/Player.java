package com.MagnetoGameLib.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Player extends GameObject {
    Random r = new Random();
    private Animation runRightAnim, runLeftAnim, idleRightAnim, idleLeftAnim;
    private boolean faceRight;
    private int health = 100, hpWidth = 40;
    private int invicibilityTime = 30;
    private boolean alive = true;
    private boolean hurt = false;
    private Handler handler;
    private HUD hud;

    Texture tex = Game.getTex();
    public Player(int x, int y, ID id, Handler handler, HUD hud){
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        runRightAnim = new Animation(8,
                tex.lizardRunRightSprites[0],
                tex.lizardRunRightSprites[1],
                tex.lizardRunRightSprites[2],
                tex.lizardRunRightSprites[3]);
        runLeftAnim = new Animation(8,
                tex.lizardRunLeftSprites[0],
                tex.lizardRunLeftSprites[1],
                tex.lizardRunLeftSprites[2],
                tex.lizardRunLeftSprites[3]);
        idleRightAnim = new Animation(8,
                tex.lizardIdleRightSprites[0],
                tex.lizardIdleRightSprites[1],
                tex.lizardIdleRightSprites[2],
                tex.lizardIdleRightSprites[3]);
        idleLeftAnim = new Animation(8,
                tex.lizardIdleLeftSprites[0],
                tex.lizardIdleLeftSprites[1],
                tex.lizardIdleLeftSprites[2],
                tex.lizardIdleLeftSprites[3]);
        if(id == ID.Player)
            faceRight = true;
        else if(id == ID.Player2)
            faceRight = false;
        //velX = 1; // Por cada tick se mueve 1 en x;
        //velX = r.nextInt(5) + 1;
        //velY = r.nextInt(5);
    }

    //PARA CREAR HITBOX (IMPORTANTE)
    public Rectangle getBounds(){
        return new Rectangle(x, y, 16*2, 21*2);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = clamp(x, 0, Game.WIDTH-80);
        y = clamp(y, 0, Game.HEIGHT-100);

        runRightAnim.runAnim();
        runLeftAnim.runAnim();
        idleRightAnim.runAnim();
        idleLeftAnim.runAnim();

        if (velX != 0) {
            if (velX < 0) {
                faceRight = false;
            } else if (velX > 0) {
                faceRight = true;
            }
        }

        collision();
    }

    public void collision(){
        if(alive){
            for(int i = 0; i < handler.object.size(); i++){
                GameObject tempObject = handler.object.get(i);
                if(tempObject.getId() == ID.Trash){
                    if(getBounds().intersects(tempObject.getBounds())){
                        hud.incTrash();
                        handler.removeObject(tempObject);
                    }
                }
                if(tempObject.getId() == ID.Enemy){
                    if(getBounds().intersects(tempObject.getBounds())){
                        invicibilityTime--;
                        if(invicibilityTime <= 0){
                            health -= 10;
                            hurt = true;
                            invicibilityTime = 30;
                        }else{
                            hurt = false;
                        }

                        System.out.println(health);
                        if(health == 0){
                            alive = false;
                            handler.removeObject(this);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //displayHitBox(g);
        updateHealthBar(g);
        playerTag(g);

        if(id == ID.Player) {
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
                    //idleRightAnim.runAnim();
                    idleRightAnim.drawAnim(g, x, y);
                } else {
                    //idleRightAnim.runAnim();
                    idleLeftAnim.drawAnim(g, x, y);
                }
            }
        }

        if(id == ID.Player2) {
            if (velX != 0 || (velY > 0 || velY < 0)) {
                if (faceRight) {
                    //runRightAnim.runAnim();
                    runRightAnim.drawAnim(g, x, y);

                } else {
                    //runLeftAnim.runAnim();
                    runLeftAnim.drawAnim(g, x, y);

                }
            } else if (velX == 0 && velY == 0) {
                if (faceRight) {
                    //idleRightAnim.runAnim();
                    idleRightAnim.drawAnim(g, x, y);
                } else {
                    //idleRightAnim.runAnim();
                    idleLeftAnim.drawAnim(g, x, y);
                }
            }
        }
    }

    public void playerTag(Graphics g){
        if(id == ID.Player){
            g.setColor(new Color(255,0,0));
            g.setFont(new Font("Serif", Font.BOLD, 20));
            g.drawString("P1", this.getX()+5, this.getY()-10);
        }
        if(id == ID.Player2){
            g.setColor(Color.CYAN);
            g.setFont(new Font("Serif", Font.BOLD, 20));
            g.drawString("P2", this.getX()+5, this.getY()-10);
        }
    }

    public void updateHealthBar(Graphics g){
        int decrementHealthBar = 1;
        if(hpWidth > 0 && hurt == false){
            g.setColor(Color.black);
            g.fillRect(x-5, y-8, 40, 5);
            g.drawRect(x-5, y-8, 40, 5);

            g.setColor(Color.green);
            g.fillRect(x-5, y-8, hpWidth, 5);
            g.drawRect(x-5, y-8, hpWidth, 5);
        }else{
            g.setColor(Color.black);
            g.fillRect(x-5, y-8, 40, 5);
            g.drawRect(x-5, y-8, 40, 5);

            g.setColor(Color.green);
            g.fillRect(x-5, y-8, 1, 5);
            g.drawRect(x-5, y-8, 1, 5);
        }

        //System.out.println("Hurt: " + hurt);

        if(hurt){
            hpWidth -= decrementHealthBar;
            //System.out.println("hpWidth " + hpWidth);
        }
    }

    public boolean getStatus(){
        return alive;
    }

    public void setStatus(boolean alive){
        this.alive = alive;
    }

    public void displayHitBox(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green); //dibujando hitboxes (comentar para no verse)
        g2d.draw(getBounds());
    }

    //Al toparse con los bordes de la venta, el objeto Player no podrá salir de pantalla (su coordenada
    //se reasignara al valor limite sin excedir)
    public static int clamp(int var, int min, int max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }
}

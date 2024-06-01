package com.MagnetoGameLib.main;

import java.awt.event.*;

public class KeyInput extends KeyAdapter implements KeyListener {

    private Handler handler;
    private HUD hud;
    public KeyInput(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(hud.getTrashCount() >= 50){
                if(tempObject.getId() == ID.Player){
                    if(key == KeyEvent.VK_W) tempObject.setVelY(-6);
                    if(key == KeyEvent.VK_S) tempObject.setVelY(6);
                    if(key == KeyEvent.VK_A) tempObject.setVelX(-6);
                    if(key == KeyEvent.VK_D) tempObject.setVelX(6);
                }
                if(tempObject.getId() == ID.Player2){
                    //Eventos de teclado SOLO para Player 2
                    if(key == KeyEvent.VK_UP) tempObject.setVelY(-6);
                    if(key == KeyEvent.VK_DOWN) tempObject.setVelY(6);
                    if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-6);
                    if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(6);
                }
            }else{
                if(tempObject.getId() == ID.Player){
                    //Eventos de teclado SOLO para Player 1
                    //Coordenada de donde esta posicionado el jugador menos 5 por cada W presionada
                    if(key == KeyEvent.VK_W) tempObject.setVelY(-3);
                    if(key == KeyEvent.VK_S) tempObject.setVelY(3);
                    if(key == KeyEvent.VK_A) tempObject.setVelX(-3);
                    if(key == KeyEvent.VK_D) tempObject.setVelX(3);
                }
                if(tempObject.getId() == ID.Player2){
                    //Eventos de teclado SOLO para Player 2
                    if(key == KeyEvent.VK_UP) tempObject.setVelY(-3);
                    if(key == KeyEvent.VK_DOWN) tempObject.setVelY(3);
                    if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-3);
                    if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(3);
                }
            }
        }
        //System.out.println(key);
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                //Eventos de teclado SOLO para Player 1
                //Coordenada de donde esta posicionado el jugador menos 5 por cada W presionada
                if(key == KeyEvent.VK_W) tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) tempObject.setVelX(0);

            }
            if(tempObject.getId() == ID.Player2){
                //Eventos de teclado SOLO para Player 2
                if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
                if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
            }
        }
    }
}

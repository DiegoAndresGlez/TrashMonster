package com.MagnetoGameLib.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;

public class Game extends Canvas implements Runnable{
    //public static final int WIDTH = 1920, HEIGHT = 1080;
    public static final int WIDTH = 1280, HEIGHT = 720;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Player p1, p2;
    private Enemy enemy;
    public static Texture tex;
    private HUD hud;
    private Window window;
    private Background background;
    private HUD savedStatsHUD;
    private KeyInput keyInput;
    private int flag = 0;

    public Game(){
        tex = new Texture();
        handler = new Handler();
        hud = new HUD(handler);
        keyInput = new KeyInput(handler, hud);
        this.addKeyListener(keyInput);
        background = new Background(WIDTH, HEIGHT, 0, 0);
        window = new Window(WIDTH, HEIGHT, "TRASH MONSTER", this);//this es la clase Game

        //hud.setTrashCount(48);
        p1 = new Player(100, 100, ID.Player, handler, hud);
        handler.addObject(p1); //Crear Player1
        p2 = new Player(1000, 100, ID.Player2, handler, hud);
        handler.addObject(p2); //Crear Player2
        enemy = new Enemy(WIDTH/2-60, HEIGHT/2-144, ID.Enemy, hud);
        handler.addObject(enemy);

        TrashHandler trashHandler = new TrashHandler(handler);
        trashHandler.start();
        trashHandler.run();
    }
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){ //Runnable por ser interfaz, requiere sobreescribir run()
        // GAME LOOP - ALGORITMO POPULAR PARA RENDERIZAR JUEGOS 2D
        //this.requestFocus(); //cuando inicia el juego no tienes que dar clic para empezar a mover Player
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 64;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
        }
    }

    private void tick(){
        handler.tick();
        if(hud.getTrashCount() == 50){
            handler.object.remove(enemy);
            enemy = new Enemy(WIDTH/2-60, HEIGHT/2-144, ID.Enemy, hud);
            handler.object.add(enemy);
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){ //BufferStrategy empieza en null
            this.createBufferStrategy(3); //Puede ser 2 pero recomendable 3
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT); //FONDO NEGRO

        background.render(g);
        handler.render(g); //Renderizar todos los objetos
        hud.render(g);
        checkEndGame(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        new Game();
    }

    private void checkEndGame(Graphics g){
        //p1.setStatus(false);
        //p2.setStatus(false);
        if(!p1.getStatus() && !p2.getStatus()){
            File fTemp = new File("highestrecord.log");
            boolean fileExist = fTemp.exists();
            System.out.println("File exists: " + fileExist);
            if(fileExist) {
                if(flag == 0){ //Bandera para saber si ya se guardo los datos en un archivo
                    try {
                        ObjectInputStream ins = new ObjectInputStream(new FileInputStream("highestrecord.log"));
                        savedStatsHUD = (HUD) ins.readObject();
                        hud.setHighestTrashCount(savedStatsHUD.getHighestTrashCount());
                        System.out.println("HTS: " + hud.getHighestTrashCount());
                        ins.close();
                        if(hud.getTrashCount() >= savedStatsHUD.getHighestTrashCount()){
                            hud.setHighestTrashCount(hud.getTrashCount());
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highestrecord.log"));
                            oos.writeObject(hud);
                            oos.close();
                            flag = 1;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else{
                if(flag == 0){
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highestrecord.log"));
                        hud.setHighestTrashCount(hud.getTrashCount());
                        oos.writeObject(hud);
                        System.out.println("GAME HTS: " + hud.getHighestTrashCount());

                        ObjectInputStream is = new ObjectInputStream(new FileInputStream("highestrecord.log"));
                        savedStatsHUD = (HUD)is.readObject();
                        System.out.println("FILE HTS: " + savedStatsHUD.getHighestTrashCount());

                        oos.close();
                        is.close();
                        flag = 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("Serif", Font.BOLD, 100));
            g.drawString("YOU DIED", 380, 300);
            g.setFont(new Font("Serif", Font.BOLD, 30));
            g.drawString("Trash scored: " + hud.getTrashCount(), 380, 350);
            g.setFont(new Font("Serif", Font.BOLD, 30));
            g.drawString("Trash highest record: " + hud.getHighestTrashCount(), 380, 400);

            window.setVisibleRestartButton(true);
            window.restartButton.addActionListener(e -> {
                resetGame();
                flag = 0;
                g.dispose();
            });

        }
    }

//    private void loadLevel(){
//        handler.addObject(new Block(300, 300, ID.Wall));
//    }
    private void resetGame(){

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject != null){
                handler.removeObject(tempObject);
            }
        }
        p1 = new Player(100, 100, ID.Player, handler, hud);
        handler.addObject(p1); //Crear Player1
        p2 = new Player(1000, 100, ID.Player2, handler, hud);
        handler.addObject(p2); //Crear Player2
        enemy = new Enemy(WIDTH/2-60, HEIGHT/2-144, ID.Enemy, hud);
        handler.addObject(enemy);
        hud.setTrashCount(0);
        window.setVisibleRestartButton(false);
    }

    public static Texture getTex(){
        return tex;
    }
}


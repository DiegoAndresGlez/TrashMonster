package com.MagnetoGameLib.main;

import java.util.Random;

public class TrashHandler implements Runnable{
    private Handler handler;

    private Random r = new Random();
    public TrashHandler(Handler handler){
        this.handler = handler;
    }
    private void spawnTrash(Handler handler){
        Random r = new Random(System.currentTimeMillis());
        int aux = r.nextInt(1,4);
        int offsetW = r.nextInt(1119+1);
        int offsetH = r.nextInt(629+1);
        int temp;
        for(int i = 0; i < 15; i++) {
            Trash trash = new Trash(offsetW,offsetH, ID.Trash, handler);
            handler.addObject(trash);
            offsetW = r.nextInt(1119+1);
            offsetH = r.nextInt(629+1);
            System.out.println(offsetW + " " +offsetH);
        }
    }

    public void run() {
        while(true){
            spawnTrash(handler);
            try {
                Thread.sleep(24000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start(){
        Thread thread = new Thread(this);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //boolean running = true;
    }
}

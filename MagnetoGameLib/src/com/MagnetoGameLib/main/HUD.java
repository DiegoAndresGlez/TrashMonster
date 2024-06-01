package com.MagnetoGameLib.main;

import java.awt.*;
import java.awt.Color.*;
import java.io.*;

public class HUD implements Serializable {
    transient int x, y;

    transient int time;

    public int trashCount = 0;
    public int highestTrashCount;
    transient public Handler handler;
    public HUD(Handler handler){
        this.handler = handler;
        File fTemp = new File("highestrecord.log");
        boolean fileExist = fTemp.exists();
        System.out.println("HUD File exists: " + fileExist);
        if(fileExist){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("highestrecord.log"));
                HUD temp = (HUD)ois.readObject();
                this.highestTrashCount = temp.getHighestTrashCount();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void tick(){
        update();
    }

    public void render(Graphics g){
       // g.setColor(Color.BLACK);
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        //g.fillRect(0, 0, 160, 180);
        g.drawString("TRASH: " + trashCount, 50, 50);
        g.drawString("TRASH HIGHEST RECORD: " + highestTrashCount, 50, 75);
        //System.out.println("Trash count: "+ trashCount);
    }
    public int getHighestTrashCount(){return highestTrashCount;}
    public void setHighestTrashCount(int trashCount){
        this.highestTrashCount = trashCount;
    }
    public int getTrashCount(){
        return trashCount;
    }
    public void setTrashCount(int trashCount){this.trashCount = trashCount;}

    public void incTrash(){
        this.trashCount += 1;
    }

    public void update(){

    }
}

package com.MagnetoGameLib.main;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BufferedImgLoader {
    private BufferedImage img;

    public BufferedImage loadImg(String path){
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }
}

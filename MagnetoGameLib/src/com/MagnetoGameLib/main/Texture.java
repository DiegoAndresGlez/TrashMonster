package com.MagnetoGameLib.main;

import com.MagnetoGameLib.main.util.Utils;
import jdk.jshell.execution.Util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.nio.Buffer;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Texture {
    SpriteSheet coinSheet;
    SpriteSheet lizardRunRightSheet;
    SpriteSheet lizardRunLeftSheet;
    SpriteSheet lizardIdleRightSheet;
    SpriteSheet lizardIdleLeftSheet;
    SpriteSheet trashMonsterSheet;

    SpriteSheet plasticBottleSheet;
    SpriteSheet wallTileSheet;
    SpriteSheet trashMonsterRunRightSheet;
    SpriteSheet trashMonsterRunLeftSheet;
    private BufferedImage background = null;
    private BufferedImage coinImg = null;
    private BufferedImage lizardRunRightImg = null;
    private BufferedImage lizardRunLeftImg = null;
    private BufferedImage lizardIdleRightImg = null;
    private BufferedImage lizardIdleLeftImg = null;
    private BufferedImage plasticBottleImg = null;
    private BufferedImage trashMonsterImg = null;
    private BufferedImage wallTileImg = null;
    private BufferedImage trashMonsterRunRightImg = null;
    private BufferedImage trashMonsterRunLeftImg = null;
    public BufferedImage[] coinSprites = new BufferedImage[6];
    public BufferedImage[] lizardRunRightSprites = new BufferedImage[4];
    public BufferedImage[] lizardRunLeftSprites = new BufferedImage[4];
    public BufferedImage[] lizardIdleRightSprites = new BufferedImage[4];
    public BufferedImage[] lizardIdleLeftSprites = new BufferedImage[4];
    public BufferedImage[] plasticBottleSprites = new BufferedImage[4];
    public BufferedImage[] trashMonsterIdleSprites = new BufferedImage[4];
    public BufferedImage[] trashMonsterRunRightSprites = new BufferedImage[4];
    public BufferedImage[] trashMonsterRunLeftSprites = new BufferedImage[4];
    public BufferedImage[] wallTileSprite = new BufferedImage[1];

    public Texture(){
        BufferedImgLoader loader = new BufferedImgLoader();
        //Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        //System.out.println(path);
        try{
            background = loader.loadImg( ".\\Resources\\Assets\\background.png");
            coinImg = loader.loadImg(".\\Resources\\Assets\\goldencoin.png");
            lizardRunRightImg = loader.loadImg(".\\Resources\\Assets\\lizard_f_run_anim.png");
            lizardRunLeftImg = loader.loadImg(".\\Resources\\Assets\\lizard_f_runleft_anim.png");
            lizardIdleRightImg = loader.loadImg(".\\Resources\\Assets\\lizard_f_idle_anim.png");
            lizardIdleLeftImg = loader.loadImg(".\\Resources\\Assets\\lizard_f_idleleft_anim.png");
            trashMonsterImg = loader.loadImg(".\\Resources\\Assets\\big_demon_idle_anim.png");
            trashMonsterRunRightImg = loader.loadImg(".\\Resources\\Assets\\big_demon_runright1.png");
            trashMonsterRunLeftImg = loader.loadImg(".\\Resources\\Assets\\big_demon_runleft.png");
            plasticBottleImg = loader.loadImg(".\\Resources\\Assets\\plasticbottlesheet.png");
            wallTileImg = loader.loadImg(".\\Resources\\Assets\\wall_mid.png");
        } catch (Exception e){
            e.printStackTrace();
        }

        coinSheet = new SpriteSheet(coinImg);
        lizardRunRightSheet = new SpriteSheet(lizardRunRightImg);
        lizardRunLeftSheet = new SpriteSheet(lizardRunLeftImg);
        lizardIdleRightSheet = new SpriteSheet(lizardIdleRightImg);
        lizardIdleLeftSheet = new SpriteSheet(lizardIdleLeftImg);
        trashMonsterSheet = new SpriteSheet(trashMonsterImg);
        trashMonsterRunRightSheet = new SpriteSheet(trashMonsterRunRightImg);
        trashMonsterRunLeftSheet = new SpriteSheet(trashMonsterRunLeftImg);
        plasticBottleSheet = new SpriteSheet(plasticBottleImg);
        wallTileSheet = new SpriteSheet(wallTileImg);

        getTextures();
    }

    public BufferedImage getBackground() {
        return background;
    }

    public void getTextures(){
        for(int i = 0; i < 6; i++) {
            Image aux = coinSheet.grabImage(i+1, 1, 16, 16).getScaledInstance(16*2, 16*2, 0);
            coinSprites[i] = Utils.toBufferedImage(aux);
        }

        for(int i = 0; i < 4; i++){
            //Sprites que se usaran para la animacion de Player para correr hacia la derecha.
            Image aux = lizardRunRightSheet.grabImage(i+1,1,16, 21).getScaledInstance(16*2, 21*2, 0);
            lizardRunRightSprites[i] = Utils.toBufferedImage(aux);

            //Para voltear horizontalmente los sprites.
            aux = lizardRunLeftSheet.grabImage(i+1,1,16, 21).getScaledInstance(16*2, 21*2, 0);
            lizardRunLeftSprites[i] = Utils.toBufferedImage(aux);

            //Animacion al quedarse quieto (si Player voltea a la derecha).
            aux = lizardIdleRightSheet.grabImage(i+1,1,16,21).getScaledInstance(16*2, 21*2, 0);
            lizardIdleRightSprites[i] = Utils.toBufferedImage(aux);

            //Animacion al quedarse quieto (si Player voltea a la derecha).
            aux = lizardIdleLeftSheet.grabImage(i+1,1,16,21).getScaledInstance(16*2, 21*2, 0);
            lizardIdleLeftSprites[i] = Utils.toBufferedImage(aux);

            aux = plasticBottleSheet.grabImage(i+1,1,16,28).getScaledInstance(16*2, 28*2, 0);
            plasticBottleSprites[i] = Utils.toBufferedImage(aux);

            //Sprites para Trash Monster
            aux = trashMonsterSheet.grabImage(i+1, 1, 32, 36).getScaledInstance(32*8, 36*8, 0);
            trashMonsterIdleSprites[i] = Utils.toBufferedImage(aux);

            aux = trashMonsterRunRightSheet.grabImage(i+1, 1, 32, 36).getScaledInstance(32*8, 36*8, 0);
            trashMonsterRunRightSprites[i] = Utils.toBufferedImage(aux);

            aux = trashMonsterRunLeftSheet.grabImage(i+1, 1, 32, 36).getScaledInstance(32*8, 36*8, 0);
            trashMonsterRunLeftSprites[i] = Utils.toBufferedImage(aux);
        }

        Image aux = wallTileSheet.grabImage(1,1, 16,16).getScaledInstance(16*2, 16*2, 0);
        wallTileSprite[0] = Utils.toBufferedImage(aux);
    }
}

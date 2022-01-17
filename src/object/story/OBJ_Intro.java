package object.story;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Intro extends Entity {

    GamePanel gp2;

    public OBJ_Intro(GamePanel gp){

        super(gp);

        gp2 = gp;

        name = "Intro";
        imageWidth = gp.screenWidth;
        imageHeight = gp.screenHeight;
        image = setupScaledImage("/introduction/intro_1");
        image2 = setupScaledImage("/introduction/intro_2");
        image3 = setupScaledImage("/introduction/intro_3");
        image4 = setupScaledImage("/introduction/intro_4");
        image5 = setupScaledImage("/introduction/intro_5");
        image6 = setupScaledImage("/introduction/intro_6");

    }

    public void draw(Graphics2D g2){

        BufferedImage image = this.image;
        int screenX = worldX - gp2.player.worldX + gp2.player.screenX;
        int screenY = worldY - gp2.player.worldY + gp2.player.screenY;

        if(worldX + gp2.tileSize > gp2.player.worldX - gp2.player.screenX &&
                worldX - gp2.tileSize < gp2.player.worldX + gp2.player.screenX &&
                worldY + gp2.tileSize > gp2.player.worldY - gp2.player.screenY &&
                worldY - gp2.tileSize < gp2.player.worldY + gp2.player.screenY){

            g2.drawImage(image, screenX, screenY, imageWidth, imageHeight, null);
        }
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}

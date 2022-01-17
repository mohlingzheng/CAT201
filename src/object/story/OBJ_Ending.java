package object.story;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Ending extends Entity {

    GamePanel gp2;

    public OBJ_Ending(GamePanel gp){

        super(gp);

        gp2 = gp;

        name = "Intro";
        imageWidth = gp.screenWidth;
        imageHeight = gp.screenHeight;
        image = setupScaledImage("/ending/ending_1");
        image2 = setupScaledImage("/ending/ending_2");
        image3 = setupScaledImage("/ending/ending_3");
        image4 = setupScaledImage("/ending/ending_4");
        image5 = setupScaledImage("/ending/ending_5");
        image6 = setupScaledImage("/ending/ending_6");
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

package object.others;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_BlackScreen extends Entity {

    GamePanel gp2;

    public OBJ_BlackScreen(GamePanel gp){

        super(gp);

        gp2 = gp;

        name = "Black";
        imageWidth = gp.screenWidth + gp.tileSize;
        imageHeight = gp.screenHeight + gp.tileSize;
        image = setupScaledImage("/object/blackscreen");

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
    }
}

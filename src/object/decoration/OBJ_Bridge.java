package object.decoration;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Bridge extends Entity {

    GamePanel gp2;

    public OBJ_Bridge(GamePanel gp) {

        super(gp);

        gp2 = gp;

        name = "Bridge";
        direction = "down";
        imageWidth = gp.tileSize * 5;
        imageHeight = gp.tileSize * 7;
        down1 = setupScaledImage("/object/bridge");
        up1 = setupScaledImage("/object/xbridge");
        image = up1;
        collision = false;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize * 5;
        solidArea.height = gp.tileSize * 7;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
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

package object.decoration;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Tree extends Entity {

    GamePanel gp2;

    public OBJ_Tree(GamePanel gp) {

        super(gp);

        gp2 = gp;

        name = "Tree";
        direction = "down";

        imageWidth = gp.tileSize * 3;
        imageHeight = gp.tileSize * 5;

        down1 = setupScaledImage("/object/xtree");
        image = down1;

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize * 3;
        solidArea.height = gp.tileSize * 5;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = this.image;
        int screenX = worldX - gp2.player.worldX + gp2.player.screenX;
        int screenY = worldY - gp2.player.worldY + gp2.player.screenY;

        if(worldX + gp2.tileSize * 8 > gp2.player.worldX - gp2.player.screenX &&
                worldX - gp2.tileSize * 8 < gp2.player.worldX + gp2.player.screenX &&
                worldY + gp2.tileSize * 8 > gp2.player.worldY - gp2.player.screenY &&
                worldY - gp2.tileSize * 8 < gp2.player.worldY + gp2.player.screenY){

            g2.drawImage(image, screenX, screenY, imageWidth, imageHeight, null);
        }
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }

}

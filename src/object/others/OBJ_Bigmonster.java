package object.others;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Bigmonster extends Entity {

    GamePanel gp2;

    public OBJ_Bigmonster(GamePanel gp) {

        super(gp);

        gp2 = gp;

        name = "Big Monster";
        direction = "left";
        imageWidth = gp.tileSize * 6;
        imageHeight = gp.tileSize * 6;
        down1 = setupScaledImage("/object/bigmonster");
        left1 = down1;
        right1 = down1;
        up1 = down1;
        image = up1;
        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize * 6;
        solidArea.height = gp.tileSize * 6;
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
    }
}

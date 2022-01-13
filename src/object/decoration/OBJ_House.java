package object.decoration;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_House extends Entity {
    GamePanel gp2;

    public OBJ_House(GamePanel gp) {

        super(gp);

        gp2 = gp;

        name = "House";
        direction = "down";
        down1 = setupScaledImage("/object/house");
        image = down1;

        imageWidth = gp.tileSize * 3;
        imageHeight = gp.tileSize * 3;

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize * 3;
        solidArea.height = gp.tileSize * 3;
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
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}

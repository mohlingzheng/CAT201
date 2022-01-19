package monster;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {

    GamePanel gp;

    public Bullet(GamePanel gp){

        super(gp);
        this.gp = gp;

        type = 3;
        speed = 4;
        name = "Bullet";
        maxLife = 1;
        life = maxLife;

        solidArea.x = 10;
        solidArea.y = 10;
        solidArea.width = 28;
        solidArea.height  = 28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getCharacterImage("monster/bullet", "bullet", 2);
    }

    // For Ending
    public void setAction(){
        if(gp.ui.endConv == true){
            direction = "right";
            worldX += speed;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

            switch (direction){
                case "up":
                    image = up1;
                    break;
                case "down":
                    image = down1;
                    break;
                case "left":
                    image = left1;
                    break;
                case "right":
                    image = right1;
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}

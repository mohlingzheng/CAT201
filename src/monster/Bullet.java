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

    public void getImage(){

        up1 = setup("/monster/bullet/bullet_up");
        up2 = setup("/monster/bullet_up");
        down1 = setup("/monster/bullet_down");
        down2 = setup("/monster/bullet_down");
        left1 = setup("/monster/bullet_left");
        left2 = setup("/monster/bullet_left");
        right1 = setup("/monster/bullet_right");
        right2 = setup("/monster/bullet_right");

    }

//    public void updateBullet(int i, int z) {
//
//        collisionOn = false;
//        gp.cChecker.checkTile(this);
//        gp.cChecker.checkObject(this, false);
//        gp.cChecker.checkEntity(this, gp.npc);
//        gp.cChecker.checkEntity(this, gp.monster1);
//        gp.cChecker.checkEntity(this, gp.monster2);
//
//        boolean contactPlayer = gp.cChecker.checkPlayer(this);
//
//        if(this.type == 3 && contactPlayer == true) {
//            if(gp.player.invincible == false) {
//
//                gp.player.life -= 1;
//                gp.player.invincible = true;
//                if(gp.bullet[i][z] != null) {
//                    gp.bullet[i][z] = null;
//                }
//            }
//        }
//
//        if(collisionOn == false) {
//
//            switch (direction){
//                case "up":
//                    worldY -= speed;
//                    break;
//                case "down":
//                    worldY += speed;
//                    break;
//                case "left":
//                    worldX -= speed;
//                    break;
//                case "right":
//                    worldX += speed;
//                    break;
//            }
//        }
//    }

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

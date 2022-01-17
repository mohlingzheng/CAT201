package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    public int dialogueIndex = 0;
    public BufferedImage image = null, image2 = null, image3 = null;
    public BufferedImage image4  = null, image5  = null, image6  = null;
    public String name;
    public boolean collision = false;

    // CHARACTER STATUS
    public int maxLife;
    public int life;

    // MONSTER STATUS
    public int type;    // 0 = player, 1 = monster1, 2 = monster2, 3 = bullet, 5 = villager
    public boolean invincible = false;
    public int invincibleCounter = 0;
    // MONSTER BULLET
    public int bulletActionLockCounter = 0;

    // CHANGE SPEECH
    public int conversationState = 0;

    // IMAGE SCALE
    public int imageWidth;
    public int imageHeight;

    public Entity(GamePanel gp){
        this.gp = gp;
        imageWidth = gp.tileSize;
        imageHeight = gp.tileSize;
    }

    public void setAction(){

    }
    public void speak(){

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
            gp.gameState = gp.playState;
        }
        else{
            gp.ui.currentDialogue = dialogues[dialogueIndex];
            dialogueIndex++;

            switch (gp.player.direction){
                case "up":
                    direction = "down";
                    break;
                case "down":
                    direction = "up";
                    break;
                case "left":
                    direction = "right";
                    break;
                case "right":
                    direction = "left";
                    break;
            }
        }

    }
    public void update(){

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster1);
        gp.cChecker.checkEntity(this, gp.monster2);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 1 && contactPlayer == true) {
            if(gp.player.invincible == false) {
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }
        else if(this.type == 2 && contactPlayer == true) {
            if(gp.player.invincible == false) {

                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        // IF COLLISION IS FALSE, ANY ENTITY CAN MOVE EXCEPT VILLAGER(type = 5)
        if(collisionOn == false && type != 5){

            switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        if(type != 5){
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void updateBullet(int i, int z) {

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster1);
        gp.cChecker.checkEntity(this, gp.monster2);

        int bulletIndex  = gp.cChecker.checkBullet(this, gp.bullet, gp.row, gp.col);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 3 && contactPlayer == true) {
            if(gp.player.invincible == false) {

                gp.player.life -= 1;
                gp.player.invincible = true;
                if(gp.bullet[i][z] != null) {
                    gp.bullet[i][z] = null;
                }
            }
        }

        if(collisionOn == false) {

            switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
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
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }

    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage setupScaledImage(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, imageWidth, imageHeight);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void setBulletAction() {

        bulletActionLockCounter ++;

        if(bulletActionLockCounter == 60) {

            switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
            bulletActionLockCounter = 0;
        }
    }

    public void getCharacterImage(String characterDirectory, String characterName, int style){

        // setup all image (with moving animation)
        if(style == 1){
            up1 = setup("/" + characterDirectory + "/" + characterName + "_up_1");
            up2 = setup("/" + characterDirectory + "/" + characterName + "_up_2");
            down1 = setup("/" + characterDirectory + "/" + characterName + "_down_1");
            down2 = setup("/" + characterDirectory + "/" + characterName + "_down_2");
            left1 = setup("/" + characterDirectory + "/" + characterName + "_left_1");
            left2 = setup("/" + characterDirectory + "/" + characterName + "_left_2");
            right1 = setup("/" + characterDirectory + "/" + characterName + "_right_1");
            right2 = setup("/" + characterDirectory + "/" + characterName + "_right_2");
        }
        // setup one type only (without moving animation)
        else if(style == 2){
            up1 = setup("/" + characterDirectory + "/" + characterName + "_up_1");
            up2 = setup("/" + characterDirectory + "/" + characterName + "_up_1");
            down1 = setup("/" + characterDirectory + "/" + characterName + "_down_1");
            down2 = setup("/" + characterDirectory + "/" + characterName + "_down_1");
            left1 = setup("/" + characterDirectory + "/" + characterName + "_left_1");
            left2 = setup("/" + characterDirectory + "/" + characterName + "_left_1");
            right1 = setup("/" + characterDirectory + "/" + characterName + "_right_1");
            right2 = setup("/" + characterDirectory + "/" + characterName + "_right_1");
        }

    }

}

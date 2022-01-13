package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public int objIndex = 0;
    public int npcIndex = 0;
    public int stoneCount = 0;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);
        this.keyH = keyH;
        type = 0;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getCharacterImage("player", "player", 1);
    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 28;
        worldY = gp.tileSize * 67;
        speed = 8;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }
    public void update(){

        // If player is on slippery ground
        if(gp.eHandler.slipperyEvent == true){
            slipperyUpdate();
        }
        // Player is moving normally
        else if(keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true ){
            normalUpdate();
        }
        // Player is standing still
        else{
            standUpdate();
        }
        // Check Invincible
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        // When no life
        if(life == 0){
            reviveLocation();
        }
    }

    public void normalUpdate(){

        if(keyH.upPressed == true){
            direction = "up";
        }
        else if(keyH.downPressed == true){
            direction = "down";
        }
        else if(keyH.leftPressed == true){
            direction = "left";
        }
        else if(keyH.rightPressed == true){
            direction = "right";
        }

        // CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // CHECK OBJECT COLLISION
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // CHECK NPC COLLISION
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        // CHECK MONSTER COLLISION
        int monster1Index = gp.cChecker.checkEntity(this, gp.monster1);
        contactMonster(monster1Index);

        int monster2Index  = gp.cChecker.checkEntity(this, gp.monster2);
        contactMonster(monster2Index);

        // CHECK EVENT
        gp.eHandler.checkEvent();

        gp.keyH.enterPressed = false;

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn == false){

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

    // Change standing position image
    public void standUpdate(){
        standCounter++;

        if(standCounter == 20){
            spriteNum = 1;
            standCounter = 0;
        }
    }

    // Cave Mission
    public void slipperyUpdate(){
        // CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // CHECK OBJECT COLLISION
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // CHECK NPC COLLISION
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        // CHECK EVENT
        gp.eHandler.checkEvent();

        gp.keyH.enterPressed = false;

        if(collisionOn == true){
            gp.eHandler.slipperyEvent = false;
        }

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn == false){

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

    // Pick up Power Stone
    public void pickUpObject(int i){

        if(i != 999){

            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Power Stone":
                    switch (i){
                        case 9:
                            gp.progressState = gp.forestState;
                            gp.obj[i] = null;
                            gp.npc[5].conversationState = 2;
                            break;
                        case 10:
                            gp.progressState = gp.mazeState;
                            gp.obj[19].image = gp.obj[19].down1;
                            gp.obj[19].collision = false;
                            gp.npc[6].conversationState = 4;
                            break;
                        case 11:
                            gp.progressState = gp.endingState;
                            break;
                    }
                    stoneCount++;
                    gp.obj[i] = null;
                    break;
            }

        }
    }

    public void interactNPC(int i){

        if(i != 999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogState;
                gp.ui.dialogueType = gp.ui.conversationState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i){

        if(i != 999){
            if(invincible == false){
                life -= 1;
                invincible = true;
            }
        }
    }

    public void contactBullet(int i, int z) {

        if(i != 999) {
            if(z != 999) {
                if (invincible == false) {
                    life -= 1;
                    invincible = true;
                }
            }
        }
    }

    // Set Player position when dead
    public void reviveLocation(){

        gp.ui.currentDialogue = "You Are Dead!";
        gp.ui.dialogueType = gp.ui.objInteractionState;
        gp.gameState = gp.dialogState;
        if(gp.progressState == gp.earlyState){
            gp.player.worldX = gp.tileSize * 28;
            gp.player.worldY = gp.tileSize * 67;
        }
        else if(gp.progressState == gp.caveState){
            gp.player.worldX = gp.tileSize * 55;
            gp.player.worldY = gp.tileSize * 66;
        }
        else if(gp.progressState == gp.forestState){
            gp.player.worldX = gp.tileSize * 57;
            gp.player.worldY = gp.tileSize * 68;
        }
        else if(gp.progressState == gp.mazeState){
            gp.player.worldX = gp.tileSize * 55;
            gp.player.worldY = gp.tileSize * 70;
        }
        else if(gp.progressState == gp.endingState){
            gp.player.worldX = gp.tileSize * 53;
            gp.player.worldY = gp.tileSize * 68;
        }

        life = maxLife;
    }

    public void draw(Graphics2D g2){

//      g2.setColor(Color.white);
//      g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

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

        int x = screenX;
        int y = screenY;

        if(screenX > worldX){
            x = worldX;
        }
        if(screenY > worldY){
            y = worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.worldWidth - worldX){
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffset = gp.screenHeight - screenY;
        if(bottomOffset > gp.worldHeight - worldY){
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }

        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, x, y,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // SHOW COLLISION RECTANGLE
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

        g2.drawRect(gp.tileSize*68, gp.tileSize*83, gp.tileSize, gp.tileSize);


    }

}

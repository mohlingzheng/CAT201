package monster;

import entity.Entity;
import main.GamePanel;

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

        getImage();
    }

    public void getImage(){

        up1 = setup("/monsters/bullet");
        up2 = setup("/monsters/bullet");
        down1 = setup("/monsters/bullet");
        down2 = setup("/monsters/bullet");
        left1 = setup("/monsters/bullet");
        left2 = setup("/monsters/bullet");
        right1 = setup("/monsters/bullet");
        right2 = setup("/monsters/bullet");

    }

    public void updateBullet(int i, int z) {

        //setAction();
        //setBulletAction();
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
}

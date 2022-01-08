package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Monster1 extends Entity {

    public Monster1(GamePanel gp){

        super(gp);

        type = 1;
        name = "Green Slime";
        speed = 1;
        maxLife = 1;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/monsters/monster_up_1");
        up2 = setup("/monsters/monster_up_2");
        down1 = setup("/monsters/monster_down_1");
        down2 = setup("/monsters/monster_down_2");
        left1 = setup("/monsters/monster_left_1");
        left2 = setup("/monsters/monster_left_2");
        right1 = setup("/monsters/monster_right_1");
        right2 = setup("/monsters/monster_right_2");
    }

    public void setAction(){

        actionLockCounter ++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {

                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}

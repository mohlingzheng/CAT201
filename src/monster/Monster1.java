package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Monster1 extends Entity {

    public Monster1(GamePanel gp){

        super(gp);

        type = 1;
        name = "Monster1";
        speed = 2;
        maxLife = 1;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getCharacterImage("monster/monster1", "monster", 1);
    }

    public void setAction(){

        actionLockCounter ++;

        if(actionLockCounter == 90){

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

package monster;

import entity.Entity;
import main.GamePanel;

import java.util.ArrayList;

public class Monster2 extends Entity {

    static ArrayList bullets;
    public Monster2(GamePanel gp) {

        super(gp);

        type = 2;
        name  ="Monster2";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        getCharacterImage("monster/monster2", "static", 2);
        direction = "down";

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height  = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        bullets = new ArrayList();

    }

}

package object.obtainable;

import entity.Entity;
import main.GamePanel;

public class OBJ_PowerStone extends Entity {

    public OBJ_PowerStone(GamePanel gp){
        super(gp);

        name = "Power Stone";
        down1 = setup("/object/powerstone");
        up1 = setup("/object/powerstone_2");
        image = down1;
        image2 = up1;
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}

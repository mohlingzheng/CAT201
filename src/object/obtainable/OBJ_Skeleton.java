package object.obtainable;

import entity.Entity;
import main.GamePanel;

public class OBJ_Skeleton extends Entity {

    GamePanel gp2;
    
    public OBJ_Skeleton(GamePanel gp){

        super(gp);

        gp2 = gp;

        name = "Skeleton";
        speed = 0;
        down1 = setup("/object/skeleton");
        up1 = down1;
        left1 = down1;
        right1 = down1;
        collision = false;
        imageHeight = gp.screenHeight;
        imageWidth = gp.screenWidth;

        image = setupScaledImage("/object/letter/letter_1");
        image2 = setupScaledImage("/object/letter/letter_2");
        image3 = setupScaledImage("/object/letter/letter_3");
        image4 = setupScaledImage("/object/letter/letter_4");
        image5 = setupScaledImage("/object/letter/letter_5");
        image6 = setupScaledImage("/object/letter/letter_6");
    }

}

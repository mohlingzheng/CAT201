package entity;

import main.GamePanel;

public class NPC_OBJ_Signboard extends Entity{

    public NPC_OBJ_Signboard(GamePanel gp){
        super(gp);
        type = 5;
        direction = "down";
        speed = 1;
        image = setup("/object/sign");
        down1 = image;
        up1 = image;
        left1 = image;
        right1 = image;
        setDialogue();
    }

    public void setDialogue(){

        if(conversationState == 1){
            dialogues[0] =  "This cave is slippery. \nIt also consists of some traps. \n" +
                            "[Hint] Interact with the pedal to solve the traps.";
        }
        else if(conversationState == 2){
            dialogues[0] =  "This forest is full of monsters. \nBe careful with everything inside.\n" +
                            "[Hint] Run and dodge is all you can do.";
        }
        else if(conversationState == 3){
            dialogues[0] =  "This maze is dark. \nThe scariest thing is the darkness.\n" +
                            "[Hint] Your vision will be reduced.";
        }
    }

    public void setAction(){



    }
    public void speak(){
        setDialogue();
        super.speak();
    }
}

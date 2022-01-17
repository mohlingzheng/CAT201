package entity;

import main.GamePanel;

public class NPC_Kid extends Entity{

    public NPC_Kid(GamePanel gp){
        super(gp);
        type = 5;
        direction = "down";
        speed = 1;

        getCharacterImage("npc/kid", "kid", 2);
        setDialogue();
    }

    public void setDialogue(){

        dialogues[0] = "I was trapped here. Thank you for \nopening the door!";

    }

    public void setAction(){



    }
    public void speak(){
        setDialogue();
        super.speak();
    }
}

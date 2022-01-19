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

        if(conversationState == 0){
            dialogues[0] = "I was trapped here. Thank you for \nopening the door!";
        }

        else if(conversationState == 1){
            dialogues[0] =  "Mother: \n" +
                    "Where have you been? I was so \nworried about you.";
            dialogues[1] =  "Son: \n" +
                    "I am so sorry, mom. I got trapped \nin the cave.";
            dialogues[2] =  "Mother: \n" +
                    "I am so sorry that I have been \nmad at you.";
            dialogues[3] =  "Mother: \n" +
                    "Promise me, don’t you ever leave \nme like that again! ";
            dialogues[4] =  "Mother: \n" +
                    "You hear me? I cannot afford to \nlose you, my son.";
            dialogues[5] =  "Son: \n" +
                    "I promise.";
            dialogues[6] =  "Mother: \n" +
                    "Thank you for your help, traveler.";
            dialogues[7] =  "Son: \n" +
                    "Thank you, sir.";
        }

    }

    public void setAction(){

    }
    public void speak(){
        setDialogue();
        super.speak();
        if(speed == 1){
            speed = 2;
        }
    }
}

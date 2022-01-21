package entity;

import main.GamePanel;

public class NPC_Worker extends Entity{

    public NPC_Worker(GamePanel gp){
        super(gp);
        type = 5;
        direction = "down";
        speed = 1;

        getCharacterImage("npc/worker", "worker", 2);
        setDialogue();
    }

    public void setDialogue(){

        // Fallen Tree
        if(conversationState == 1){
            dialogues[0] = "The tree was stroke down by the monster.";
            dialogues[1] = "It is blocking the path to the forest.";
            dialogues[2] = "We will remove it as soon as possible.";
        }
        // Clear Tree
        else if(conversationState == 2){
            dialogues[0] = "We have removed the broken tree.";
            dialogues[1] = "You can reach the East Forest from here now.";
        }
        // Broken Bridge
        else if(conversationState == 3){
            dialogues[0] = "The bridge is broken, and we are fixing \nit right now.";
            dialogues[1] = "Do come back again if you want to go to \nthe other side of the bridge.";
        }
        // Fixed Bridge
        else if(conversationState == 4){
            dialogues[0] =  "The bridge is fixed. Let’s hope the \n" +
                            "monster does not come back anytime \nsoon.";
        }

    }

    public void setAction(){



    }
    public void speak(){
        setDialogue();
        super.speak();
    }
}

package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Villager extends Entity{

    public NPC_Villager(GamePanel gp){
        super(gp);
        type = 5;
        direction = "down";
        speed = 1;

        getCharacterImage("npc", "oldman", 2);
        setDialogue();

    }

    public void setDialogue(){

        if(conversationState == 1){
            dialogues[0] = "Cayor Kingdom is a young kingdom.";
            dialogues[1] = "We will be not defeated by some \nugly monsters.";
        }
        else if(conversationState == 2){
            dialogues[0] = "It’s a blessing to have Power \nStone.";
            dialogues[1] = "But we have lost it now…";
        }
        else if(conversationState == 3){
            dialogues[0] = "We should be always ready.";
            dialogues[1] = "The monster could come back \nanytime soon.";
        }
        else if(conversationState == 4){
            dialogues[0] = "I wouldn’t want my kids to live \nin fear.";
            dialogues[1] = "If only the Power Stone could be \nfound again…";
        }
        else if(conversationState == 5){
            dialogues[0] = "It’s a blessing to have Power \nStone.";
            dialogues[1] = "But we have lost it now…";
        }

    }

    public void setAction(){



    }
    public void speak(){
        setDialogue();
        super.speak();
    }
}

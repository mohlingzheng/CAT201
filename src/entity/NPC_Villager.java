package entity;

import main.GamePanel;

public class NPC_Villager extends Entity{

    public NPC_Villager(GamePanel gp, int t){
        super(gp);
        type = 5;
        direction = "down";
        speed = 1;

        if(t == 1){
            getCharacterImage("npc/oldman", "oldman", 2);
        }
        else if(t == 2){
            getCharacterImage("npc/villager", "villager", 2);
        }
        else if(t == 3){
            getCharacterImage("npc/villager2", "villager", 2);
        }
        else if(t == 4){
            getCharacterImage("npc/oldman", "oldman", 2);
        }

        setDialogue();
    }

    public void setDialogue(){

        if(conversationState == 1){
            dialogues[0] = "Cayor Kingdom is a young kingdom.";
            dialogues[1] = "We will be not defeated by some ugly monsters.";
        }
        else if(conversationState == 2){
            dialogues[0] = "It’s a blessing to have Power Stone.";
            dialogues[1] = "But we have lost it now…";
        }
        else if(conversationState == 3){
            dialogues[0] = "We should be always ready.";
            dialogues[1] = "The monster could come back anytime soon.";
        }
        else if(conversationState == 4){
            dialogues[0] = "I wouldn’t want my kids to live in fear.";
            dialogues[1] = "If only the Power Stone could be found again…";
        }
        else if(conversationState == 5){
            dialogues[0] = "It’s a blessing to have Power Stone.";
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

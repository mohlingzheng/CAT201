package entity;

import main.GamePanel;

import java.util.Random;


public class NPC_King extends Entity{

    public NPC_King(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getCharacterImage("npc", "king", 1);
        setDialogue();
    }

    public void setDialogue(){

        if(conversationState == 0){
            dialogues[0] = "Remember, find the other three pieces \nof Power Stone.";
            dialogues[1] = "One is in the North Cave.";
            dialogues[2] = "Another one is in the East Forest.";
            dialogues[3] = "And the last one is in the South Maze.";
        }
        else if(conversationState == 1){
            dialogues[0] = "DIE!!!";
            dialogues[1] = "DIE!!!";
            dialogues[2] = "DEMON!!!";
            dialogues[3] = "Err...";
        }

    }
    public void setAction(){

        actionLockCounter ++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }
    public void speak(){
        setDialogue();
        super.speak();
    }

}

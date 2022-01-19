package entity;

import main.GamePanel;

public class NPC_King extends Entity{

    public NPC_King(GamePanel gp){
        super(gp);

        name = "King";
        direction = "right";
        type = 5;
        speed = 2;
        conversationState = 0;

        getCharacterImage("npc/king", "king", 1);
        setDialogue();
    }

    public void setDialogue(){

        if(conversationState == 0){
            dialogues[0] = "Remember, find the other three pieces \nof Power Stone.";
            dialogues[1] = "One is in the North Cave.";
            dialogues[2] = "Another one is in the East Forest.";
            dialogues[3] = "And the last one is in the South Maze.";
        }
        else if(conversationState == 2){

        }

    }

    public void setAction(){
        // Action for Intro Talk
        if(gp.ui.startConv == true || gp.ui.endConv == true){
            if(gp.cChecker.checkPlayer(this) == false){
                worldX += speed;
            }
            else{
                standStill = true;
            }
        }
    }

    public void speak(){
        setDialogue();
        super.speak();
    }

}

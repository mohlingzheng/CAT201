package entity;

import main.GamePanel;

public class NPC_King extends Entity{

    public NPC_King(GamePanel gp){
        super(gp);

        name = "King";
        direction = "right";
        type = 5;
        speed = 1;
        conversationState = 0;

        getCharacterImage("npc/king", "king", 1);
        setDialogue();
    }

    public void setDialogue(){

        if(conversationState == 0){
            dialogues[0] =  "King:\n" +
                            "Ah! Young man. You must be coming from the \nportal.";
            dialogues[1] =  "Trevor:\n" +
                            "What? Who are you? Where am I?";
            dialogues[2] =  "King:\n" +
                            "Be calm, young man. You are in the land of \n" +
                            "Cavor Kingdom and I am the king of this \n" +
                            "kingdom. What’s your name?";
            dialogues[3] =  "Trevor:\n" +
                            "I… err… I am Trevor.";
            dialogues[4] =  "King:\n" +
                            "Surprised not, Trevor. You are here because \n" +
                            "of the Power Stone.";
            dialogues[5] =  "Trevor:\n" +
                            "Power Stone?";
            dialogues[6] =  "King:\n" +
                            "Our kingdom was attacked by the demon \n" +
                            "dragon. Thanks to Power Stone, we were able \n" +
                            "to fight back the demon dragon. ";
            dialogues[7] =  "King:\n" +
                            "The dragon fled away but there’s no \n" +
                            "guarantee that it won’t come back. \n" +
                            "Unfortunately, it broke into piece and \n" +
                            "spread around our kingdom.";
            dialogues[8] =  "Trevor:\n" +
                            "What does it have to do with me?";
            dialogues[9] =  "King:\n" +
                            "Power Stone was able to open portal \n" +
                            "that can connect to another world. ";
            dialogues[10] = "King:\n" +
                            "The portal must have been opened during \n" +
                            "the splitting of the Power Stone and you \n" +
                            "accidentally entered the portal. That is \n" +
                            "why and how you are here.";
            dialogues[11] = "Trevor:\n" +
                            "Is there a way to go back to my world?";
            dialogues[12] = "King:\n" +
                            "By using Power Stone, you can open the \n" +
                            "portal again and go back to your world. ";
            dialogues[13] = "King:\n" +
                            "We would like to retrieve the pieces of \n" +
                            "Power Stone but we can’t leave our kingdom \n" +
                            "because we need to make sure the kingdom is \n" +
                            "safe from the monsters. ";
            dialogues[14] = "King:\n" +
                            "So, I will leave the task to you, young man.";
            dialogues[15] = "Trevor:\n" +
                            "Do you know where the pieces of the Power \n" +
                            "Stone fall?";
            dialogues[16] = "King:\n" +
                            "One is in the North Cave. Another one is in \n" +
                            "the East Forest and the last one is in the \n" +
                            "South Maze. ";
            dialogues[17] = "King:\n" +
                            "Retrieve the Power Stone so our kingdom is \n" +
                            "safe again and you can go back to your world.";
            dialogues[18] = "Trevor:\n" +
                            "I will try my best…";

        }
        else if(conversationState == 1){
            dialogues[0] = "Remember, find the other three pieces \nof Power Stone.";
            dialogues[1] = "One is in the North Cave.";
            dialogues[2] = "Another one is in the East Forest.";
            dialogues[3] = "And the last one is in the South Maze.";
        }
        else if(conversationState == 2){

        }

    }
    public void setAction(){

    }
    public void speak(){

        if(dialogues[dialogueIndex] == null && conversationState == 0) {
            // Turn off King Conversation
            gp.ui.startConv = false;
            conversationState = 1;
            for(int i = 0; i < 20; i++){
                dialogues[i] = null;
            }
        }
        setDialogue();
        super.speak();
    }

}

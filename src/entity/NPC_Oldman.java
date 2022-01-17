package entity;

import main.GamePanel;

public class NPC_Oldman extends Entity{

    public NPC_Oldman(GamePanel gp){
        super(gp);
        type = 5;
        direction = "down";
        speed = 1;

        getCharacterImage("npc/oldman", "oldman", 2);
        setDialogue();
    }

    public void setDialogue(){

        dialogues[0] =  "My son is an adventurer. He wants \n" +
                        "to defeat the monster to prove himself.";

        dialogues[1] =  "I have stopped him from going into \n" +
                        "the forest, but he thought I don’t \n" +
                        "believe his ability.";

        dialogues[2] =  "Once he entered the forest, he never \n" +
                        "came out.";

        dialogues[3] =  "I miss him so much. I don’t even know \n" +
                        "if he is still alive or not.";

        dialogues[4] =  "I wish to see him again before I died…";

        dialogues[5] =  "Oh… if you are going to enter the \n" +
                        "forest, be careful. There are \n" +
                        "monsters in that forest. That is \n" +
                        "what my son warned me about.";

    }

    public void setAction(){



    }
    public void speak(){
        setDialogue();
        super.speak();
    }
}

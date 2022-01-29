package main;

import entity.Entity;
import object.others.OBJ_BlackScreen;
import object.others.OBJ_Heart;
import object.obtainable.OBJ_PowerStone;
import object.story.OBJ_Ending;
import object.story.OBJ_Intro;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font timesBD;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0 for menu

    // Create Mission Window
    public String missionList[] = new String[4];
    BufferedImage powerstone_image;
    public int missionCount = 0;

    // If talk to NPC, will continue talking. If interact object, only once.
    public int dialogueType = 0;
    public final int conversationState = 1;
    public final int objInteractionState = 2;

    // CheckPoint Renew
    public boolean hitCheckPoint = false;
    public int frameCount = 0;

    // Black Screen Effect
    BufferedImage blackScreen;
    BufferedImage controlScreen;

    // Intro Ending Related
    BufferedImage introImage[] = new BufferedImage[6];
    BufferedImage endingImage[] = new BufferedImage[6];
    public int sceneNum = 0;
    public int sceneMessageNum = 0;
    String introMessage[][] = new String[6][5];
    String endingMessage[][] = new String[6][5];

    // Talk State
    public int introOrEnding = 1; // intro = 1, ending = 2;
    public boolean startConv = true;
    public boolean endConv = false;
    public String introConversation[] = new String[19];
    public int introConvNumber = 0;
    public String endingConversation[] = new String[14];
    public int endingConvNumber = 0;

    // Pop Out State
    public int popOutMessageNum = 0;
    public int popOutMessageType = 0;

    // Fade In Fade Out
    public int countFadeTime = 210;
    public int fadeType = 1;        // 1 = Fade Out, 2 = Fade In
    public int endingTimer = 0;


    // Constructor
    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/timesbd.ttf");
            timesBD = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        // CREATE MISSION
        Entity powerstone = new OBJ_PowerStone(gp);
        powerstone_image = powerstone.image;
        missionList[0] = "Find the pieces in North Cave";
        missionList[1] = "Find the pieces in East Forest";
        missionList[2] = "Find the pieces in South Maze";
        missionList[3] = "Return to the king";

        // Maze Effect
        Entity black = new OBJ_BlackScreen(gp);
        blackScreen = black.image;
        controlScreen = black.image2;

        storySetup();
        conversationSetup();

    }

    // All updates drawing
    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(timesBD);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState){
            if(gp.musicOn == false){
                gp.musicOn = true;
                gp.playMusic(0);
            }
            drawMissionListWindow();
            drawCheckPointMessage();
            drawPlyaerLife();
            drawDarkRegion();
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            drawPlyaerLife();
            drawPauseScreen();
        }
        // DIALOG STATE
        else if(gp.gameState == gp.dialogState){
            drawPlyaerLife();
            drawMissionListWindow();
            drawDialogScreen();
        }
        // TRANSITION STATE
        else if(gp.gameState == gp.transitionState){
            drawTransitionWindow();
        }
        // STORY STATE
        else if(gp.gameState == gp.videoState){
            if(startConv == true){
                drawStoryImage();
                fadeInEffect();
            }
            else if(introOrEnding == 2){
                if(fadeType == 1){
                    fadeOutEffect();
                    if(countFadeTime >= 255){
                        endingTimer++;
                        if(endingTimer == 60){
                            fadeType = 2;
                            endingTimer = 0;
                            countFadeTime = 255;
                        }
                    }
                }
                else if(fadeType == 2){
                    drawStoryImage();
                    fadeInEffect();
                    if(countFadeTime == 0){
                        fadeType = 3;
                        gp.npc[0].standStill = true;
                    }
                }
                else if(fadeType == 3){
                    drawStoryImage();
                }
                else if(fadeType == 4){
                    sceneNum = 5;
                    drawStoryImage();
                    fadeOutEffect();
                    if(countFadeTime == 255){
                        gp.gameState = gp.titleState;
                    }
                }
            }


        }
        // TALK STATE
        else if(gp.gameState == gp.talkState){
            // Intro scene and speech
            if(gp.npc[0].standStill == true && startConv == true){
                drawIntroEndingConversation();
            }
            // Ending scene and speech
            else if(endConv == true){
                if(fadeType == 1){
                    fadeOutEffect();
                    if(countFadeTime >= 255){
                        endingTimer++;
                        if(endingTimer == 30){
                            gp.aSetter.endingSceneLocationSet();
                        }
                        else if(endingTimer == 60){
                            fadeType = 2;
                            endingTimer = 0;
                        }
                    }
                }
                else if(fadeType == 2){
                    fadeInEffect();
                    if(countFadeTime <= 0){
                        fadeType = 3;
                        gp.npc[0].standStill = false;
                    }
                }
                else if(fadeType == 3){
                    if(gp.npc[0].standStill == true && endConv == true){
                        drawIntroEndingConversation();
                    }
                }

            }
        }
        // POP OUT STATE
        else if(gp.gameState == gp.popOutState){
            drawDarkRegion();
            drawPlyaerLife();
            drawMissionListWindow();
            popOutMessage();
        }

    }
    public void drawPlyaerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DRAW MAX HEART
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }

    // Before game
    public void drawTitleScreen(){

        if(titleScreenState == 0){
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));
            String text = "Traversed";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            // SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x+5, y+5);
            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // CHARACTER IMAGE
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

        }
        else if(titleScreenState == 1){
            int x = 0;
            int y = 0;
            int width = gp.screenWidth;
            int height = gp.screenHeight;
            g2.drawImage(controlScreen, x, y, width, height, null);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
            g2.setColor(Color.white);
            String words = "Press Enter Key to Start The Game...";
            g2.drawString(words, gp.tileSize * 8, gp.tileSize * 11 + 35);
        }

    }

    // Intro with image
    public void drawStoryImage(){

        if(introOrEnding == 1){
            g2.drawImage(introImage[sceneNum], 0, 0, null);

            int x = gp.tileSize * 1 - 19;
            int y = gp.tileSize * 8 ;
            drawSubWindow(x, y, gp.tileSize*13 + 37, gp.tileSize*3 + 18);

            x = x + 20;
            y = y + 35;

            writeCaption(x, y, sceneNum, 0);
        }
        else if(introOrEnding == 2){
            g2.drawImage(endingImage[sceneNum], 0, 0, null);

            int x = gp.tileSize * 1 - 19;
            int y = gp.tileSize * 8 ;
            drawSubWindow(x, y, gp.tileSize*13 + 37, gp.tileSize*3 + 18);

            x = x + 20;
            y = y + 35;

            writeCaption(x, y, sceneNum, 0);
        }


    }

    public void drawIntroEndingConversation(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        if(gp.npc[0].standStill == true && startConv == true){
            drawSubWindow(x, y, width, height);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
            x += 20;
            y += 35;

            for(String line : introConversation[introConvNumber].split("\n")){
                g2.drawString(line,x ,y);
                y += 35;
            }
        }
        else if(gp.npc[0].standStill == true && endConv == true){
            drawSubWindow(x, y, width, height);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
            x += 20;
            y += 35;

            for(String line : endingConversation[endingConvNumber].split("\n")){
                g2.drawString(line,x ,y);
                y += 35;
            }
        }

    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogScreen(){

        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        x += 20;
        y += 35;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x ,y);
            y += 35;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    // When entering different Area
    public void drawTransitionWindow(){
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        int length = (int)g2.getFontMetrics().getStringBounds(currentDialogue, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        int y = gp.screenHeight/2;
        g2.drawString(currentDialogue,x ,y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        String anyKey = "Press Enter to continue";
        length = (int)g2.getFontMetrics().getStringBounds(anyKey, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        y = gp.screenHeight/2 + gp.tileSize;
        g2.drawString(anyKey,x ,y);
    }

    // Show the mission list
    public void drawMissionListWindow(){

        Color myColor = new Color(0, 0, 0, 50);
        int x = gp.tileSize * 11;
        int y = gp.tileSize;

        g2.setColor(myColor);
        g2.fillRect(x, y, gp.tileSize*4, gp.tileSize*2);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 10F));
        g2.setColor(Color.white);
        x = x + gp.tileSize;

        // No mission completed
        int i = 0;

        // First mission completed
        if(missionCount == 1){
            i = 1;
        }

        // Second mission completed
        else if(missionCount == 2){
            i = 2;
        }
        else if(missionCount == 3){
            i = 3;
            g2.drawImage(powerstone_image, x- gp.tileSize, y - gp.tileSize/2, null);
            g2.drawString(missionList[i],x ,y);
        }
        for(; i < 3; i++){
            y = y + gp.tileSize/2;
            g2.drawImage(powerstone_image, x- gp.tileSize, y - gp.tileSize/2, null);
            g2.drawString(missionList[i],x ,y);
        }
    }

    // Show the checkpoint of the game progress
    public void drawCheckPointMessage(){

        if(hitCheckPoint == true){
            int x = 20;
            int y = gp.tileSize * 11;
            int width = gp.tileSize * 2 + 10;

            g2.setColor(Color.black);
            g2.fillRect(x, y, width, gp.tileSize/2);

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
            x = x + 4;
            y = y + 17;
            g2.drawString("CheckPoint",x ,y);

            frameCount++;
            if(frameCount == 120){
                hitCheckPoint = false;
                frameCount = 0;
            }
        }


    }

    // Low Vision in Maze Mission
    public void drawDarkRegion(){

        if(gp.eHandler.enterMaze == true){
            g2.drawImage(blackScreen, 0, 0, null);
        }
    }

    // Get the length of the text
    public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    // Setup image and caption for Intro and Ending
    public void storySetup(){

        // Story Objects
        Entity intro = new OBJ_Intro(gp);
        introImage[0] = intro.image;
        introImage[1] = intro.image2;
        introImage[2] = intro.image3;
        introImage[3] = intro.image4;
        introImage[4] = intro.image5;
        introImage[5] = intro.image6;
        introMessage[0][0] =
                "Ever since Trevor can remember, his parents were always \n" +
                "angry at him for tiny matters. He tried to obey all the \n" +
                "commands from them, but he always feels his parents don’t \n" +
                "satisfy with him. ";

        introMessage[1][0] =
                "As he grown up, the quarrels never stop. He didn’t understand \n" +
                "why his parents have been mean at him, but he never \n" +
                "communicates with them. One day, they had a huge quarrel.";

        introMessage[2][0] =
                "He can’t hold it anymore and ran away from home.";

        introMessage[3][0] =
                "He ran to a park and nobody is around. As he was wandering \n" +
                "in the park, many conflicts of the past reappeared in his \n" +
                "memory. He didn’t like being at home and he had no place \n" +
                "to go.";

        introMessage[4][0] =
                "Suddenly, he saw a bright circle shows up from a few miles \n" +
                "away. He has no idea what it is. ";

        introMessage[5][0] =
                "He was wondering what the bright circle is and starting to \n" +
                "approach it. Before he could react, he was absorbed by the \n" +
                "bright light. He felt dizzy and lost the consciousness.";

        Entity ending = new OBJ_Ending(gp);
        endingImage[0] = ending.image;
        endingImage[1] = ending.image2;
        endingImage[2] = ending.image3;
        endingImage[3] = ending.image4;
        endingImage[4] = ending.image5;
        endingImage[5] = ending.image6;

        endingMessage[0][0] =
                "The monster was defeated, and the kingdom was safe now. \n" +
                "The whole kingdom was very happy. In order to express the \n" +
                "gratitude toward Trevor, the king used the Power Stone to \n" +
                "open the portal. ";

        endingMessage[1][0] =
                "Through the portal, he traversed back to his world. It was \n" +
                "evening and he was now ready to go home. He wanted to tell \n" +
                "them how he felt and apologize to them.";

        endingMessage[2][0] =
                "On the way back home, he saw his parents are shouting his \n" +
                "name and looking for him anxiously. He realized his parents \n" +
                "care for him. He felt guilty for not treating his parents well \n" +
                "and blessed to have such wonderful parents. ";

        endingMessage[3][0] =
                "Without a second of hesitation, he ran towards them. It was \n" +
                "a tear and joy emotion that he could not describe.";

        endingMessage[4][0] =
                "He apologized to his parents for running off without telling \n" +
                "anyone, and for his stubbornness along these years. His \n" +
                "parents also apologized to him for not listening to his thought.";

        endingMessage[5][0] =
                "They smiled happily and go back home together.";

    }

    public void conversationSetup(){
        introConversation[0] =  "King:\n" +
                                "Ah! Young man. You must be coming from the \nportal.";
        introConversation[1] =  "Trevor:\n" +
                                "What? Who are you? Where am I?";
        introConversation[2] =  "King:\n" +
                                "Be calm, young man. You are in the land of \n" +
                                "Cavor Kingdom and I am the king of this \n" +
                                "kingdom. What’s your name?";
        introConversation[3] =  "Trevor:\n" +
                                "I… err… I am Trevor.";
        introConversation[4] =  "King:\n" +
                                "Surprised not, Trevor. You are here because of \n" +
                                "the Power Stone.";
        introConversation[5] =  "Trevor:\n" +
                                "Power Stone?";
        introConversation[6] =  "King:\n" +
                                "Our kingdom was attacked by the demon \n" +
                                "dragon. Thanks to Power Stone, we were able \n" +
                                "to fight back the demon dragon. ";
        introConversation[7] =  "King:\n" +
                                "The dragon fled away but there’s no guarantee \n" +
                                "that it won’t come back. Unfortunately, it \n" +
                                "broke into piece and spread around our kingdom.";
        introConversation[8] =  "Trevor:\n" +
                                "What does it have to do with me?";
        introConversation[9] =  "King:\n" +
                                "Power Stone was able to open portal that can \n" +
                                "connect to another world.";
        introConversation[10] = "King:\n" +
                                "The portal must have been opened during the \n" +
                                "splitting of the Power Stone and you accidentally \n" +
                                "entered the portal. That is why and how you are \n" +
                                "here.";
        introConversation[11] = "Trevor:\n" +
                                "Is there a way to go back to my world?";
        introConversation[12] = "King:\n" +
                                "By using Power Stone, you can open the portal \n" +
                                "again and go back to your world. ";
        introConversation[13] = "King:\n" +
                                "We would like to retrieve the pieces of Power \n" +
                                "Stone but we can’t leave our kingdom because \n" +
                                "we need to make sure the kingdom is safe from \n" +
                                "the monsters.";
        introConversation[14] = "King:\n" +
                                "So, I will leave the task to you, young man.";
        introConversation[15] = "Trevor:\n" +
                                "Do you know where the pieces of the Power \n" +
                                "Stone fall?";
        introConversation[16] = "King:\n" +
                                "One is in the North Cave. Another one is in the \n" +
                                "East Forest and the last one is in the South \n" +
                                "Maze.";
        introConversation[17] = "King:\n" +
                                "Retrieve the Power Stone so our kingdom is \n" +
                                "safe again and you can go back to your world.";
        introConversation[18] = "Trevor:\n" +
                                "I will try my best…";

        endingConversation[0] =     "Trevor:\n" +
                                    "King. I have found all the pieces of the Power \n" +
                                    "Stone.";

        endingConversation[1] =     "King\n" +
                                    "Great job, young man. Now my kingdom is \n" +
                                    "safe. You at the same time, can also return \n" +
                                    "your world.";

        endingConversation[2] =     "Suddenly, the earth is trembling…";

        endingConversation[3] =     "Trevor:\n" +
                                    "What’s happening?";

        endingConversation[4] =     "King:\n" +
                                    "Oh no. The evil monster has come back. Quickly \n" +
                                    "Trevor, use the Power Stone to defeat the \n" +
                                    "monster.";

        endingConversation[5] =     "As Trevor holds the Power Stone up, the stone \n" +
                                    "emits a bright energy from within. The energy \n" +
                                    "power strikes the monster. ";

        endingConversation[6] =     "Monster:\n" +
                                    "Er!!!";

        endingConversation[7] =     "The monster could not stand against the strong \n" +
                                    "power from the stone. It shatters and died.";

        endingConversation[8] =     "Trevor:\n" +
                                    "We did it!";

        endingConversation[9] =     "King:\n" +
                                    "You did it, Trevor.";

        endingConversation[10] =    "Suddenly, a portal shows up in front of them.";

        endingConversation[11] =    "Trevor:\n" +
                                    "Is this the portal to back to my world?";

        endingConversation[12] =    "King:\n" +
                                    "Yes, it is. Thank you, young man. You saved us \n" +
                                    "all. We appreciate your kindness. ";

        endingConversation[13] =    "Trevor:\n" +
                                    "It would be my honor. ";

        endingConversation[13] =    "Villagers:\n" +
                                    "Thank you, Trevor… Goodbye!";

        endingConversation[13] =    "Learnt a lot, Trevor entered the portal, back \n" +
                                    "to the real world.";

    }

    // Write Caption for Intro and Ending
    public void writeCaption(int x, int y, int scene, int num){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        if(introOrEnding == 1){
            for(String line : introMessage[scene][num].split("\n")){
                g2.drawString(line,x ,y);
                y += 35;
            }
        }
        else if(introOrEnding == 2){
            for(String line : endingMessage[scene][num].split("\n")){
                g2.drawString(line,x ,y);
                y += 35;
            }
        }

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.setColor(Color.gray);
        String words = "Press Enter Key to Continue...";
        g2.drawString(words, gp.tileSize * 9, gp.tileSize * 11 + 35);

    }

    // Draw Pop Out message from Skeleton
    public void popOutMessage(){

        if(popOutMessageType == 0){
            int x = gp.tileSize*2;
            int y = gp.tileSize/2;
            int width = gp.screenWidth - (gp.tileSize*4);
            int height = gp.tileSize*4;
            drawSubWindow(x, y, width, height);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
            x += 20;
            y += 35;

            currentDialogue = "This is a letter from the skeleton.";

            for(String line : currentDialogue.split("\n")){
                g2.drawString(line,x ,y);
                y += 35;
            }
        }
        else if(popOutMessageType == 1){
            int x = 0;
            int y = 0;
            switch (popOutMessageNum){
                case 20:
                    g2.drawImage(gp.obj[popOutMessageNum].image, x, y, null);
                    break;
                case 21:
                    g2.drawImage(gp.obj[popOutMessageNum].image2, x, y, null);
                    break;
                case 22:
                    g2.drawImage(gp.obj[popOutMessageNum].image3, x, y, null);
                    break;
                case 23:
                    g2.drawImage(gp.obj[popOutMessageNum].image4, x, y, null);
                    break;
                case 24:
                    g2.drawImage(gp.obj[popOutMessageNum].image5, x, y, null);
                    break;
                case 25:
                    g2.drawImage(gp.obj[popOutMessageNum].image6, x, y, null);
                    break;
            }
        }


    }

    // From 255 to 0, slowly showing
    public void fadeInEffect(){
        if(countFadeTime > 0){
            countFadeTime--;
            Color myColor = new Color(0, 0, 0, countFadeTime);
            g2.setColor(myColor);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

    }

    // From 0 to 255, slowly disappear
    public void fadeOutEffect(){

        if(countFadeTime < 255){
            countFadeTime++;
        }
        Color myColor = new Color(0, 0, 0, countFadeTime);
        g2.setColor(myColor);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    }

}

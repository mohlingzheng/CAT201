package main;

import entity.Entity;
import object.others.OBJ_Heart;
import object.obtainable.OBJ_PowerStone;

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
    public String missionList[] = new String[3];
    BufferedImage powerstone_image;

    // If talk to NPC, will continue talking. If interact object, only once.
    public int dialogueType = 0;
    public final int conversationState = 1;
    public final int objInteractionState = 2;

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
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(timesBD);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState){
            drawMissionListWindow();
            drawPlyaerLife();
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlyaerLife();
            drawPauseScreen();
        }
        // DIALOG STATE
        if(gp.gameState == gp.dialogState){
            drawPlyaerLife();
            drawMissionListWindow();
            drawDialogScreen();
        }
        // TRANSITION STATE
        if(gp.gameState == gp.transitionState){
            drawTransitionWindow();
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
    public void drawTitleScreen(){

        if(titleScreenState == 0){
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));
            String text = "Trevor Transversed";
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
//        else if(titleScreenState == 1){
//
//            // CLASS SELECTION SCREEN
//            g2.setColor(Color.white);
//            g2.setFont(g2.getFont().deriveFont(42F));
//
//            String text = "Select your class!";
//            int x = getXforCenteredText(text);
//            int y = gp.tileSize*3;
//            g2.drawString(text, x, y);
//
//            text = "Fighter";
//            x = getXforCenteredText(text);
//            y += gp.tileSize*2;
//            g2.drawString(text, x, y);
//            if(commandNum == 0){
//                g2.drawString(">", x-gp.tileSize, y);
//            }
//
//            text = "Thief";
//            x = getXforCenteredText(text);
//            y += gp.tileSize;
//            g2.drawString(text, x, y);
//            if(commandNum == 1){
//                g2.drawString(">", x-gp.tileSize, y);
//            }
//
//            text = "Sorcerer";
//            x = getXforCenteredText(text);
//            y += gp.tileSize;
//            g2.drawString(text, x, y);
//            if(commandNum == 2){
//                g2.drawString(">", x-gp.tileSize, y);
//            }
//
//            text = "Back";
//            x = getXforCenteredText(text);
//            y += gp.tileSize*2;
//            g2.drawString(text, x, y);
//            if(commandNum == 3){
//                g2.drawString(">", x-gp.tileSize, y);
//            }
//        }


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

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x ,y);
            y += 40;
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

    public void drawTransitionWindow(){
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        int length = (int)g2.getFontMetrics().getStringBounds(currentDialogue, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        int y = gp.screenHeight/2;
        g2.drawString(currentDialogue,x ,y);
    }

    public void drawMissionListWindow(){

        Color myColor = new Color(0, 0, 0, 50);
        int x = gp.tileSize * 11;
        int y = gp.tileSize;

        g2.setColor(myColor);
        g2.fillRect(x, y, gp.tileSize*4, gp.tileSize*2);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 10F));
        g2.setColor(Color.white);
        x = x + gp.tileSize;
        for(int i = 0; i < 3; i++){
            y = y + gp.tileSize/2;
            g2.drawImage(powerstone_image, x- gp.tileSize, y - gp.tileSize/2, null);
            g2.drawString(missionList[i],x ,y);
        }
    }

    public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}

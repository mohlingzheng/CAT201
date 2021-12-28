package main;

import object.OBJ_Door;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public boolean slipperyEvent = false;
    public boolean[] doorCondition = new boolean[5];

    public EventHandler(GamePanel gp){

        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                row ++;
                col = 0;
            }
        }
        for(int i = 0; i < 5; i++){
            doorCondition[i] = false;
        }
    }

    public void checkEvent(){

        // Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent == true){
            enterExitCave(gp.transitionState);
            checkSlipperyTile();
            checkNonSlipperyTile();
            buttonTrigger(gp.dialogState);
        }
    }
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void enterExitCave(int gameState){
        if(hit(55, 44, "any") == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You enter the cave!";
            gp.player.worldX = gp.tileSize * 19;
            gp.player.worldY = gp.tileSize * 31;
        }
        if(hit(19, 32, "any") == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You leave the cave!";
            gp.player.worldX = gp.tileSize * 55;
            gp.player.worldY = gp.tileSize * 46;
        }
    }

    public void checkSlipperyTile(){
        for(int row = 18; row < 28; row++){
            for(int col = 24; col < 38; col++){
                // Need to change mapTileNum accordingly
                if(hit(col, row, "any") && gp.tileM.mapTileNum[col][row] == 13){
                    slipperyEvent = true;
                    canTouchEvent = true;
                }
            }
        }
    }

    public void checkNonSlipperyTile(){
        if(hit(25, 27, "any") == true){
            slipperyEvent = false;
        }
        else if(hit(26, 25, "any") == true){
            slipperyEvent = false;
        }
        else if(hit(27, 25, "any") == true){
            slipperyEvent = false;
        }
        else if(hit(27, 24, "any") == true){
            slipperyEvent = false;
        }
        else if(hit(28, 24, "any") == true){
            slipperyEvent = false;
        }
        else if(hit(29, 21, "any") == true){
            slipperyEvent = false;
        }
        else if(hit(34, 23, "any") == true){
            slipperyEvent = false;
        }
        else if(hit(35, 23, "any") == true){
            slipperyEvent = false;
        }
    }

    public void buttonTrigger(int gameState){
        int col = 45, row = 22;
        if(hit(col, row, "any") == true){
            doorSwitch(col, row, gameState);
        }
        col = 50; row = 26;
        if(hit(col, row, "any") == true){
            doorSwitch(col, row, gameState);
        }
        col = 49; row = 32;
        if(hit(col, row, "any") == true){
            doorSwitch(col, row, gameState);
        }
        col = 53; row = 22;
        if(hit(col, row, "any") == true){
            doorSwitch(col, row, gameState);
        }
    }

    public void doorSwitch(int col, int row, int gameState){
        if(gp.keyH.enterPressed == true){
            int door1 = 0, door2 = 0;
            int doorCol1 = 0, doorRow1 = 0;
            int doorCol2 = 0, doorRow2 = 0;
            if(col == 45 && row == 22){
                door1 = 0; doorCol1 = 47; doorRow1 = 27;
                door2 = 4; doorCol2 = 47; doorRow2 = 31;
            }
            else if(col == 50 && row == 26){
                door1 = 2; doorCol1 = 52; doorRow1 = 29;
                door2 = 4; doorCol2 = 47; doorRow2 = 31;
            }
            else if(col == 49 && row == 32){
                door1 = 0; doorCol1 = 47; doorRow1 = 27;
                door2 = 1; doorCol2 = 49; doorRow2 = 25;
            }
            else if(col == 53 && row == 22){
                door1 = 2; doorCol1 = 52; doorRow1 = 29;
                door2 = 3; doorCol2 = 52; doorRow2 = 29;
            }
            gp.aSetter.createDoor(door1, doorCol1, doorRow1, doorCondition[door1]);
            gp.aSetter.createDoor(door2, doorCol2, doorRow2, doorCondition[door2]);
            doorCondition[door1] = !doorCondition[door1];
            doorCondition[door2] = !doorCondition[door2];
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Two doors have been modified.";
        }
    }
}

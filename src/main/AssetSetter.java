package main;

import entity.NPC_King;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        // Cave Mission
        gp.obj[0] = new OBJ_Door(gp);
        gp.obj[0].worldX = gp.tileSize*47;
        gp.obj[0].worldY = gp.tileSize*27;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = gp.tileSize*49;
        gp.obj[1].worldY = gp.tileSize*25;

        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].worldX = gp.tileSize*52;
        gp.obj[2].worldY = gp.tileSize*29;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = gp.tileSize*54;
        gp.obj[3].worldY = gp.tileSize*27;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = gp.tileSize*47;
        gp.obj[4].worldY = gp.tileSize*31;

        gp.obj[5] = new OBJ_Key(gp);
        gp.obj[5].worldX = gp.tileSize*45;
        gp.obj[5].worldY = gp.tileSize*22;

        gp.obj[6] = new OBJ_Key(gp);
        gp.obj[6].worldX = gp.tileSize*50;
        gp.obj[6].worldY = gp.tileSize*26;

        gp.obj[7] = new OBJ_Key(gp);
        gp.obj[7].worldX = gp.tileSize*49;
        gp.obj[7].worldY = gp.tileSize*32;

        gp.obj[8] = new OBJ_Key(gp);
        gp.obj[8].worldX = gp.tileSize*53;
        gp.obj[8].worldY = gp.tileSize*22;

        // Set Object here


    }
    public void setNPC(){

        gp.npc[0] = new NPC_King(gp);
        gp.npc[0].worldX = gp.tileSize * 50;
        gp.npc[0].worldY = gp.tileSize * 70;

    }

    public void createDoor(int num, int col, int row, boolean existDoor){
        if(existDoor == true){
            gp.obj[num].worldX = gp.tileSize*col;
            gp.obj[num].worldY = gp.tileSize*row;
        }
        else if(existDoor == false){
            gp.obj[num].worldX = gp.tileSize*0;
            gp.obj[num].worldY = gp.tileSize*0;
        }
    }
}

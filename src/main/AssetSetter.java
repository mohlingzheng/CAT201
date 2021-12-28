package main;

import entity.NPC_King;
import object.OBJ_Door;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

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

    }
    public void setNPC(){

        gp.npc[0] = new NPC_King(gp);
        gp.npc[0].worldX = gp.tileSize * 50;
        gp.npc[0].worldY = gp.tileSize * 70;

    }

    public void createDoor(int num, int col, int row, boolean existDoor){
        if(existDoor == true){
            gp.obj[num] = new OBJ_Door(gp);
            gp.obj[num].worldX = gp.tileSize*col;
            gp.obj[num].worldY = gp.tileSize*row;
        }
        else if(existDoor == false){
            gp.obj[num] = null;
        }
    }
}

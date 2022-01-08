package main;

import entity.NPC_King;
import entity.NPC_Villager;
import monster.Bullet;
import monster.Monster1;
import monster.Monster2;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_PowerStone;

public class AssetSetter {

    GamePanel gp;
    int j = 0;

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

        gp.obj[9] = new OBJ_PowerStone(gp);
        gp.obj[9].worldX = gp.tileSize*57;
        gp.obj[9].worldY = gp.tileSize*27;

        gp.obj[10] = new OBJ_PowerStone(gp);
        gp.obj[10].worldX = gp.tileSize*144;
        gp.obj[10].worldY = gp.tileSize*51;

        gp.obj[11] = new OBJ_PowerStone(gp);
        gp.obj[11].worldX = gp.tileSize*126;
        gp.obj[11].worldY = gp.tileSize*115;

        gp.obj[12] = new OBJ_PowerStone(gp);
        gp.obj[12].worldX = gp.tileSize*55;
        gp.obj[12].worldY = gp.tileSize*71;

    }
    public void setNPC(){

        gp.npc[0] = new NPC_King(gp);
        gp.npc[0].worldX = gp.tileSize * 50;
        gp.npc[0].worldY = gp.tileSize * 70;
        gp.npc[0].conversationState = 0;

        gp.npc[1] = new NPC_Villager(gp);
        gp.npc[1].worldX = gp.tileSize * 24;
        gp.npc[1].worldY = gp.tileSize * 65;
        gp.npc[1].conversationState = 1;

        gp.npc[2] = new NPC_Villager(gp);
        gp.npc[2].worldX = gp.tileSize * 24;
        gp.npc[2].worldY = gp.tileSize * 74;
        gp.npc[2].conversationState = 2;

        gp.npc[3] = new NPC_Villager(gp);
        gp.npc[3].worldX = gp.tileSize * 32;
        gp.npc[3].worldY = gp.tileSize * 74;
        gp.npc[3].conversationState = 3;

        gp.npc[4] = new NPC_Villager(gp);
        gp.npc[4].worldX = gp.tileSize * 31;
        gp.npc[4].worldY = gp.tileSize * 63;
        gp.npc[4].conversationState = 4;

    }

    public void setMonster1(){
        gp.monster1[0] = new Monster1(gp);
        gp.monster1[0].worldX = gp.tileSize*50;
        gp.monster1[0].worldY = gp.tileSize*68;
    }

    public void setMonster2(){
        gp.monster2[0] = new Monster2(gp);
        gp.monster2[0].worldX = gp.tileSize*50;
        gp.monster2[0].worldY = gp.tileSize*67;
    }

    public void setBullet() {
        if(j>=19) {
            j = 0;
        }
        gp.bullet[0][j] = new Bullet(gp);
        gp.bullet[0][j].worldX = gp.tileSize*56;
        gp.bullet[0][j].worldY = gp.tileSize*70;
        gp.bullet[0][j].direction = "right";

        gp.bullet[1][j] = new Bullet(gp);
        gp.bullet[1][j].worldX = gp.tileSize*55;
        gp.bullet[1][j].worldY = gp.tileSize*69;
        gp.bullet[1][j].direction = "up";

        gp.bullet[2][j] = new Bullet(gp);
        gp.bullet[2][j].worldX = gp.tileSize*55;
        gp.bullet[2][j].worldY = gp.tileSize*71;
        gp.bullet[2][j].direction = "down";

        gp.bullet[3][j] = new Bullet(gp);
        gp.bullet[3][j].worldX = gp.tileSize*54;
        gp.bullet[3][j].worldY = gp.tileSize*70;
        gp.bullet[3][j].direction = "left";

        j++;
    }

    // Cave Mission
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

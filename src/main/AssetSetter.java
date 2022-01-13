package main;

import entity.NPC_King;
import entity.NPC_Villager;
import entity.NPC_Worker;
import monster.Bullet;
import monster.Monster1;
import monster.Monster2;
import object.others.OBJ_Door;
import object.others.OBJ_Key;
import object.decoration.OBJ_Bridge;
import object.decoration.OBJ_Castle;
import object.decoration.OBJ_House;
import object.obtainable.OBJ_PowerStone;
import object.decoration.OBJ_Tree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetSetter {

    GamePanel gp;

    // Bullet and Monster
    public int bullettimecount;
    int j = 0;
    int numbering[][] = new int[27][2];
    final int numMonster2[] = new int[4];

    public AssetSetter(GamePanel gp){
        this.gp = gp;
        numMonster2[0] = 6;
        numMonster2[1] = 6 + 8;
        numMonster2[2] = 6 + 8 + 8;
        numMonster2[3] = 6 + 8 + 8 + 5;
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
        gp.obj[12].direction = "up";

        // Fallen Tree
        gp.obj[13] = new OBJ_Tree(gp);
        gp.obj[13].worldX = gp.tileSize*64;
        gp.obj[13].worldY = gp.tileSize*66;
        gp.obj[13].collision = false;

        gp.obj[14] = new OBJ_House(gp);
        gp.obj[14].worldX = gp.tileSize*31;
        gp.obj[14].worldY = gp.tileSize*60;

        gp.obj[15] = new OBJ_House(gp);
        gp.obj[15].worldX = gp.tileSize*31;
        gp.obj[15].worldY = gp.tileSize*71;

        gp.obj[16] = new OBJ_House(gp);
        gp.obj[16].worldX = gp.tileSize*24;
        gp.obj[16].worldY = gp.tileSize*71;

        gp.obj[17] = new OBJ_House(gp);
        gp.obj[17].worldX = gp.tileSize*76;
        gp.obj[17].worldY = gp.tileSize*66;

        gp.obj[18] = new OBJ_Castle(gp);
        gp.obj[18].worldX = gp.tileSize*23;
        gp.obj[18].worldY = gp.tileSize*61;

        // Broken Bridge
        gp.obj[19] = new OBJ_Bridge(gp);
        gp.obj[19].worldX = gp.tileSize*53;
        gp.obj[19].worldY = gp.tileSize*78;


    }

    public void setNPC(){

        gp.npc[0] = new NPC_King(gp);
        gp.npc[0].worldX = gp.tileSize * 50;
        gp.npc[0].worldY = gp.tileSize * 70;
        gp.npc[0].conversationState = 0;

        gp.npc[1] = new NPC_Villager(gp);
        gp.npc[1].worldX = gp.tileSize * 24;
        gp.npc[1].worldY = gp.tileSize * 66;
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

        // Fallen Tree
        gp.npc[5] = new NPC_Worker(gp);
        gp.npc[5].worldX = gp.tileSize * 62;
        gp.npc[5].worldY = gp.tileSize * 68;
        gp.npc[5].conversationState = 1;

        // Broken Bridge
        gp.npc[6] = new NPC_Worker(gp);
        gp.npc[6].worldX = gp.tileSize * 55;
        gp.npc[6].worldY = gp.tileSize * 75;
        gp.npc[6].conversationState = 3;

    }

    public void setMonster1(){

        // Load location of monsters
        final int numMonster = 36;
        int numbering[][] = new int[numMonster][2];
        String filePath = "/textfile/Monster1Location.txt";

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < 2 && row < numMonster){

                String line = br.readLine();

                while(col < 2){

                    String numbers[] = line.split("\t");

                    int num = Integer.parseInt(numbers[col]);
                    numbering[row][col] = num;
                    col++;
                }
                if(col == 2){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            System.out.println("r");
        }

        // Set monster
        for(int i = 0; i < numMonster; i++){
            gp.monster1[i] = new Monster1(gp);
            gp.monster1[i].worldX = gp.tileSize*numbering[i][0];
            gp.monster1[i].worldY = gp.tileSize*numbering[i][1];
        }

    }

    public void setMonster2(){

        // Load location of monsters
        final int numMonster = 27;
        String filePath = "/textfile/Monster2Location.txt";

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < 2 && row < numMonster){

                String line = br.readLine();

                while(col < 2){

                    String numbers[] = line.split("\t");

                    int num = Integer.parseInt(numbers[col]);
                    numbering[row][col] = num;
                    col++;
                }
                if(col == 2){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            System.out.println("r");
        }

        // Set monster
        // For Up, i from 0 to 5
        for(int i = 0; i < numMonster2[0]; i++){
            gp.monster2[i] = new Monster2(gp);
            gp.monster2[i].worldX = gp.tileSize*numbering[i][0];
            gp.monster2[i].worldY = gp.tileSize*numbering[i][1];
            gp.monster2[i].direction = "up";
        }

        // For Up, i from 6 to 13
        for(int i = 6; i < numMonster2[1]; i++){
            gp.monster2[i] = new Monster2(gp);
            gp.monster2[i].worldX = gp.tileSize*numbering[i][0];
            gp.monster2[i].worldY = gp.tileSize*numbering[i][1];
            gp.monster2[i].direction = "up";
        }

        // For Up, i from 14 to 21
        for(int i = 14; i < numMonster2[2]; i++){
            gp.monster2[i] = new Monster2(gp);
            gp.monster2[i].worldX = gp.tileSize*numbering[i][0];
            gp.monster2[i].worldY = gp.tileSize*numbering[i][1];
            gp.monster2[i].direction = "up";
        }

        // For Up, i from 22 to 26
        for(int i = 22; i < numMonster2[3]; i++){
            gp.monster2[i] = new Monster2(gp);
            gp.monster2[i].worldX = gp.tileSize*numbering[i][0];
            gp.monster2[i].worldY = gp.tileSize*numbering[i][1];
            gp.monster2[i].direction = "up";
        }
    }

    // Bullet Setting
    public void setBullet() {

        bullettimecount++;

        if(bullettimecount == 90){
            bullettimecount = 0;
            if(j >= 19) {
                j = 0;
            }

            // For Up, i from 0 to 5
            for(int i = 0; i < numMonster2[0]; i++){
                gp.bullet[i][j] = new Bullet(gp);
                gp.bullet[i][j].worldX = gp.tileSize*numbering[i][0];
                gp.bullet[i][j].worldY = gp.tileSize*numbering[i][1] - gp.tileSize;
                gp.bullet[i][j].direction = "up";
            }

            // For Up, i from 6 to 13
            for(int i = 6; i < numMonster2[1]; i++){
                gp.bullet[i][j] = new Bullet(gp);
                gp.bullet[i][j].worldX = gp.tileSize*numbering[i][0];
                gp.bullet[i][j].worldY = gp.tileSize*numbering[i][1] + gp.tileSize;
                gp.bullet[i][j].direction = "down";
            }

            // For Up, i from 14 to 21
            for(int i = 14; i < numMonster2[2]; i++){
                gp.bullet[i][j] = new Bullet(gp);
                gp.bullet[i][j].worldX = gp.tileSize*numbering[i][0] - gp.tileSize;
                gp.bullet[i][j].worldY = gp.tileSize*numbering[i][1];
                gp.bullet[i][j].direction = "left";
            }

            // For Up, i from 22 to 26
            for(int i = 22; i < numMonster2[3]; i++){
                gp.bullet[i][j] = new Bullet(gp);
                gp.bullet[i][j].worldX = gp.tileSize*numbering[i][0] + gp.tileSize;
                gp.bullet[i][j].worldY = gp.tileSize*numbering[i][1];
                gp.bullet[i][j].direction = "right";
            }

            j++;
        }

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

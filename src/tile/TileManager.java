package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[500];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/textfile/MapsV01.txt");
    }

    public void getTileImage(){

        // Tile for Maze
        setup(0, "cave/cave", false);
        for (int i = 1; i < 21; i++){
            String name = "maze/" + String.valueOf(i);
            setup(i, name, true);
        }
        setup(21, "maze/21", false);
        setup(22, "maze/21", false);

        for(int i = 23; i < 101; i++){
            setup(i, "cave/cave", true);
        }

        // Tile for Normal
        setup(101, "normal/earth_1", false);
        setup(102, "normal/earth_grass_1", false);
        setup(103, "normal/earth_grass_2", false);
        setup(104, "normal/earth_grass_3", false);
        setup(105, "normal/earth_grass_4", false);
        setup(106, "normal/earth_grass_5", false);
        setup(107, "normal/earth_grass_6", false);
        setup(108, "normal/earth_grass_7", false);
        setup(109, "normal/earth_grass_8", false);
        setup(110, "normal/earth_side_down", false);
        setup(111, "normal/earth_side_left", false);
        setup(112, "normal/earth_side_right", false);
        setup(113, "normal/earth_side_up", false);
        setup(114, "normal/fence", true);
        setup(115, "normal/fence_side_left", true);
        setup(116, "normal/fence_side_right", true);
        setup(117, "normal/flower", false);
        setup(118, "normal/forest_tree_1", true);
        setup(119, "normal/forest_tree_2", true);
        setup(120, "normal/forest_tree_3", true);
        setup(121, "normal/grass", false);
        setup(122, "normal/water", true);
        setup(123, "normal/tree_1", true);
        setup(124, "normal/tree_2", true);
        setup(125, "normal/tree_3", true);
        setup(126, "normal/tree_4", true);

        for(int i = 127; i < 131; i++){
            setup(i, "normal/grass", false);
        }

        // Tile for Cave
        setup(131, "cave/cave", true);
        setup(132, "cave/cave_stone", true);
        setup(133, "cave/cave_tiles_1", true);
        setup(134, "cave/cave_tiles_2", true);
        setup(135, "cave/cave_tiles_3", true);
        setup(136, "cave/cave_tiles_4", true);
        setup(137, "cave/earth_3", false);
        setup(138, "cave/earth_4", false);

        for(int i = 139; i < 151; i++){
            setup(i, "cave/cave", true);
        }

        // Tile for Forest
        setup(151, "forest/forest", false);
        setup(152, "forest/forest_tree_1", true);
        setup(153, "forest/forest_tree_2", true);
        setup(154, "forest/forest_tree_3", true);

    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setupScaled(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, 116, 138);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split("\t");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }


    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Stop moving the camera at the edge
            if(gp.player.screenX > gp.player.worldX){
                screenX = worldX;
            }
            if(gp.player.screenY > gp.player.worldY){
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;
            if(rightOffset > gp.worldWidth - gp.player.worldX){
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - gp.player.screenY;
            if(bottomOffset > gp.worldHeight - gp.player.worldY){
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            else if(gp.player.screenX > gp.player.worldX ||
                    gp.player.screenY > gp.player.worldY ||
                    rightOffset > gp.worldWidth - gp.player.worldX||
                    bottomOffset > gp.worldHeight - gp.player.worldY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

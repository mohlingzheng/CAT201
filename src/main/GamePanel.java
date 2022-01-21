package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int originalTileSize = 32; //16x16 tile
    final int scaleUp = 3;
    final int scaleDown = 2;

    public final int tileSize = originalTileSize * scaleUp / scaleDown; //48x48 tile
    public final int maxScreenCol = 15;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 720 pixels   816 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels  672 pixel

    // WORLD SETTINGS
    public final int maxWorldCol = 164;
    public final int maxWorldRow = 140;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // MONSTER
    public int k = 0, row = 40, col = 20, indexI = 0;
    public Entity[] monster1 = new Entity[40];
    public Entity[] monster2 = new Entity[40];
    public Entity[][] bullet = new Entity[row][col];

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[30];
    public Entity npc[] = new Entity[30];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int transitionState = 4;
    public final int videoState = 5;
    public final int talkState = 6;
    public final int popOutState = 7;

    // PROGRESS STATE
    public int progressState = 0;
    public final int earlyState = 0;
    public final int caveInState = 1;
    public final int caveOutState = 2;
    public final int forestInState = 3;
    public final int forestOutState = 4;
    public final int mazeInState = 5;
    public final int mazeOutState = 6;
    public final int endingState = 7;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);  // GamePanel can recognise key input
        this.setFocusable(true);    // GamePanel focuses on receive key input
    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster1();
        aSetter.setMonster2();
//        playMusic(0);
//        stopMusic();
        gameState = titleState;

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
        playMusic(0);
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // MONSTER BULLET
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }

            if(timer >= 1000000000){

//                System.out.println("FPS:" + drawCount);

                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        if(gameState == playState){
            // PLAYER
            player.update();
            aSetter.setBullet();

            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            // MONSTER
            for(int i = 0; i < monster1.length; i++){
                if(monster1[i] != null){
                    monster1[i].update();
                }
            }

            // BULLET
            for(int i = 0; i < row; i++) {
                for(int z = 0; z < col; z++) {
                    if (bullet[i][z] != null) {
                        bullet[i][z].updateBullet(i, z);
                        if(bullet[i][z] != null) {
                            if (bullet[i][z].collisionOn) {
                                bullet[i][z].worldX = 0;
                                bullet[i][z].worldY = 0;
                                bullet[i][z] = null;
                            }
                        }
                    }
                }
            }


        }

        else if(gameState == dialogState){

        }

        else if(gameState == pauseState){

        }

        // TALK STATE
        else if(gameState == talkState){
            // Intro Talking Scene
            if(npc[0].standStill == false){
                npc[0].setAction();
            }
            else if(ui.endConv == true){
                if(bullet[27][0] != null){
                    bullet[27][0].setAction();
                    if(bullet[27][0].worldX == tileSize * 35){
                        bullet[27][0] = null;
                    }
                }
            }
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
        // OTHERS
        else {
            // TILE
            tileM.draw(g2);

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            for(int i = 0; i < monster1.length; i++){
                if(monster1[i] != null){
                    entityList.add(monster1[i]);
                }
            }

            for(int i = 0; i < monster2.length; i++){
                if(monster2[i] != null){
                    entityList.add(monster2[i]);
                }
            }

            for(int i = 0; i < row; i++) {
                for(int j = 0; j < col; j++) {
                    if (bullet[i][j] != null) {
                        entityList.add(bullet[i][j]);
                    }
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }

            });

            // DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            // EMPTY ENTITY LIST
            entityList.clear();

            // UI
            ui.draw(g2);
        }

        g2.dispose();
    }
    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){

        music.stop();
    }

    public void playSE(int i){

        se.setFile(i);
        se.play();
    }
}

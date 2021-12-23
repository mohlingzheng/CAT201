package main;

import entity.Entity;
import entity.NPC_OldMan;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
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

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;


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
//        playMusic(0);
//        stopMusic();
        gameState = titleState;
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update(){

        if(gameState == playState){
            player.update();

            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState){

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

            // OBJECT
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }
            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].draw(g2);
                }
            }

            // PLAYER
            player.draw(g2);

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

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState){

            if(gp.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_W){
                    gp.ui.commmandNum --;
                    if(gp.ui.commmandNum < 0){
                        gp.ui.commmandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commmandNum ++;
                    if(gp.ui.commmandNum > 2){
                        gp.ui.commmandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commmandNum == 0){
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commmandNum == 1){

                    }
                    if(gp.ui.commmandNum == 2){
                        System.exit(0);
                    }
                }
            }
            else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W){
                    gp.ui.commmandNum --;
                    if(gp.ui.commmandNum < 0){
                        gp.ui.commmandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commmandNum ++;
                    if(gp.ui.commmandNum > 3){
                        gp.ui.commmandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commmandNum == 0){
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commmandNum == 1){
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commmandNum == 2){
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commmandNum == 3){
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
        }

        // PLAY STATE
        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }

        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}

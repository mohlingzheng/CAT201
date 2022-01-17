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
                int i = 0;
                int j = 1;
                if(code == KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < i){
                        gp.ui.commandNum = j;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > j){
                        gp.ui.commandNum = i;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        gp.gameState = gp.videoState;
                    }
                    if(gp.ui.commandNum == 1){
                        System.exit(0);
                    }
                }
            }
            else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        gp.gameState = gp.playState;
                        //gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1){
                        gp.gameState = gp.playState;
                        //gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 2){
                        gp.gameState = gp.playState;
                        //gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 3){
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
        }

        // PLAY STATE
        else if(gp.gameState == gp.playState){
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
                if(gp.ui.dialogueType == gp.ui.conversationState){
                    gp.player.npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
                    gp.npc[gp.player.npcIndex].speak();
                }
                else if(gp.ui.dialogueType == gp.ui.objInteractionState)
                    gp.gameState = gp.playState;
            }
        }

        // TRANSITION STATE
        else if(gp.gameState == gp.transitionState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

        // VIDEO STATE
        else if(gp.gameState == gp.videoState){
            if(code == KeyEvent.VK_ENTER){
                gp.ui.sceneNum++;
                if(gp.ui.sceneNum >= gp.ui.introImage.length){
                    gp.gameState = gp.playState;
                    gp.ui.sceneNum = 0;
                }
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

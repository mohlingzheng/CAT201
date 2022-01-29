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
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1){
                        System.exit(0);
                    }
                }
            }
            else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_ENTER){
                    gp.gameState = gp.videoState;
                }
            }
            else if(gp.ui.titleScreenState == 2){
                if(code == KeyEvent.VK_ENTER){
                    gp.gameState = gp.titleState;

                }
                if(code == KeyEvent.VK_L){
                    System.exit(0);
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
            // Normal condition
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
            if(gp.ui.introOrEnding == 1){
                if(gp.musicOn == false){
                    gp.musicOn = true;
                    gp.playMusic(4);
                }
                if(code == KeyEvent.VK_ENTER){
                    gp.ui.sceneNum++;
                    if(gp.ui.sceneNum >= gp.ui.introImage.length){
                        if(gp.musicOn == true){
                            gp.musicOn = false;
                            gp.stopMusic();
                        }
                        gp.gameState = gp.talkState;
                        gp.ui.sceneNum = 0;
                    }
                }
            }
            else if(gp.ui.introOrEnding == 2){
                if(gp.musicOn == false){
                    gp.musicOn = true;
                    gp.playMusic(5);
                }
                if(gp.ui.fadeType == 3){
                    if(code == KeyEvent.VK_ENTER){
                        gp.ui.sceneNum++;
                        if(gp.ui.sceneNum >= gp.ui.endingImage.length){
                            gp.ui.fadeType = 4;
                        }
                    }
                }
                else if(gp.ui.fadeType == 4){
                    if(code == KeyEvent.VK_ENTER){
                        gp.ui.titleScreenState = 2;
                        gp.gameState = gp.titleState;
                    }
                }
            }

        }

        // TALK STATE
        else if(gp.gameState == gp.talkState){
            if(gp.npc[0].standStill == true && gp.ui.startConv == true){
                if(gp.musicOn == false){
                    gp.musicOn = true;
                    gp.playMusic(0);
                }
                if(code == KeyEvent.VK_ENTER){
                    gp.ui.introConvNumber++;
                    if(gp.ui.introConvNumber >= gp.ui.introConversation.length){
                        gp.ui.introConvNumber = 0;
                        gp.ui.startConv = false;
                        gp.gameState = gp.playState;
                    }

                }
            }
            else if(gp.ui.fadeType == 3 && gp.npc[0].standStill == true){
                if(gp.musicOn == true){
                    gp.musicOn = false;
                    gp.stopMusic();
                }
                if(code == KeyEvent.VK_ENTER){
                    gp.ui.endingConvNumber++;
                    if(gp.ui.endingConvNumber >= gp.ui.endingConversation.length){
                        gp.ui.endingConvNumber = 0;
                        gp.ui.endConv = false;
                        gp.ui.fadeType = 1;
                        gp.gameState = gp.videoState;
                    }
                    // Show Monster
                    else if(gp.ui.endingConvNumber == 3){
                        gp.obj[26].worldX = gp.tileSize*34;
                        gp.obj[26].worldY = gp.tileSize*66;
                        gp.npc[1].direction = "right";
                        gp.npc[2].direction = "right";
                        gp.npc[6].direction = "right";
                        gp.npc[7].direction = "right";
                        gp.npc[8].direction = "right";

                    }
                    // Player turn
                    else if(gp.ui.endingConvNumber == 4){
                        gp.player.direction = "right";
                        gp.npc[1].direction = "down";
                        gp.npc[2].direction = "up";
                        gp.npc[6].direction = "down";
                        gp.npc[7].direction = "up";
                        gp.npc[8].direction = "up";
                    }
                    // Power Stone bullet
                    else if(gp.ui.endingConvNumber == 5){
                        gp.aSetter.powerstoneBullet();
                        gp.playSE(3);
                    }
                    // Remove Monster
                    else if(gp.ui.endingConvNumber == 7){
                        gp.obj[26].worldX = gp.tileSize*1;
                        gp.obj[26].worldY = gp.tileSize*1;
                        gp.obj[26] = null;
                    }
                    // Turn back to King
                    else if(gp.ui.endingConvNumber == 8){
                        gp.player.direction = "left";
                    }
                }
            }
        }

        // POP OUT STATE
        else if(gp.gameState == gp.popOutState){
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.popOutMessageType == 0){
                    gp.ui.popOutMessageType = 1;
                }
                else if(gp.ui.popOutMessageType == 1){
                    gp.gameState = gp.playState;
                    gp.ui.popOutMessageType = 0;
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

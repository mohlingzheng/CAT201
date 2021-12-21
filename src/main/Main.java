package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Traversed");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // Set window to be sized to fit the preferred size

        window.setLocationRelativeTo(null);
        window.setVisible(true);//This is a comment

<<<<<<< HEAD
        gamePanel.startGameThread();//This is a comment
=======
        gamePanel.startGameThread(); // new comment
>>>>>>> a0bc3e07fa91d0da49378742cca4c99951c44482

    }
}

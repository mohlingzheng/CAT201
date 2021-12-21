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
        window.setVisible(true);

        gamePanel.startGameThread(); // new comment

    }
}

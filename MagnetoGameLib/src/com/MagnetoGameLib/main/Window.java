package com.MagnetoGameLib.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas{
    public JFrame frame;
    public JButton restartButton;
    public Game game;
    public Window(int width, int height, String title, Game game){
        frame = new JFrame(title);
        restartButton = new JButton();
        this.game = game;

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        restartButton.setBounds(380, 450, 100, 25);
        restartButton.setBackground(new Color(139,0,0));
        restartButton.setText("Restart");
        restartButton.setFont(new Font("Serif", Font.BOLD, 20));
        restartButton.setVisible(false);

        frame.add(restartButton);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);

        game.start();
    }

    public void setVisibleRestartButton(boolean b){
        restartButton.setVisible(b);
    }
}


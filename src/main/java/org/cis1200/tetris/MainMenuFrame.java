package org.cis1200.tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuFrame extends JFrame {
    JButton leaderboardButton = new JButton("leaderboard");
    JButton startButton = new JButton("start game");
    JButton quitButton = new JButton("quit game");
    JButton instructionsButton = new JButton("instructions");
    MainMenuFrame sf;

    public MainMenuFrame() {

        this.setTitle("Startup Form");
        this.setLayout(new FlowLayout());//Set layout to be FlowLayout explicitly.
        this.add(startButton);
        this.add(leaderboardButton);
        this.add(quitButton);
        this.add(instructionsButton);
        this.setSize(500, 500);
        this.setVisible(true);
        //this.add(instructPanel);
        sf = this;
    }

    public void setup() {
        startButton.addActionListener(e -> {
            //this.setVisible(false);
            Tetris.start();
        });

        leaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                Tetris.showLeaderboard();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                System.exit(0);
            }
        });


        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.showInstructions();
            }
        });

    }


}

package org.cis1200.tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuFrame extends JFrame {
    JButton leaderboardButton = new JButton("leaderboard");
    JButton startButton = new JButton("start game");
    JButton quitButton = new JButton("quit game");
    MainMenuFrame mmf;
    JButton instructionsButton = new JButton("instructions");

    public MainMenuFrame() {
        this.setLayout(new FlowLayout());//Set layout to be FlowLayout explicitly.
        this.add(startButton);
        this.add(leaderboardButton);
        this.add(quitButton);
        this.add(instructionsButton);
        this.setSize(500, 500);
        this.setVisible(true);
        mmf = this;

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                Tetris.start();
            }
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


    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayFrame().setVisible(true);

            }
        });

    }
}

package org.cis1200.tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class StartupForm extends JFrame{
    JButton leaderboardButton = new JButton("leaderboard");
    JButton startButton = new JButton("start game");
    JButton quitButton = new JButton("quit game");
    JButton instructionsButton = new JButton("instructions");
    public StartupForm() {
        this.setLayout(new FlowLayout());//Set layout to be FlowLayout explicitly.
        this.add(startButton);
        this.add(leaderboardButton);
        this.add(quitButton);
        this.add(instructionsButton);
        this.setSize(500, 500);
        this.setVisible(true);

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
    }


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                new GameForm().setVisible(true);

            }
        });

    }
}

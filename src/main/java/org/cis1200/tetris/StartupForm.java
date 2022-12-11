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
    JPanel instructPanel = new JPanel();
    StartupForm sf;
    public StartupForm() {

        this.setTitle("Startup Form");
        this.setLayout(new FlowLayout());//Set layout to be FlowLayout explicitly.
        this.add(startButton);
        this.add(leaderboardButton);
        this.add(quitButton);
        this.add(instructionsButton);
        this.setSize(500, 500);
        this.setVisible(true);
        //this.add(instructPanel);
        sf=this;
    }

    public void setup() {
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

        String instruction="<html><b>Here is how to play the game</b><br>" +
                "<li>asfsafsfdfdsfa<br>"+
                "<li>asfafasfafsaf<br>" +
                "<li>asdfaswerwreewre<br>"+
                "<li>sadfalsjljljljljjllkjsafsafdasfasfdsafsafsfsafsasfsafsfsafsafasfsafd<br>"+
                "fsafsafsafsafasfasfdasfdsadfasfafsafdasfsafd"+
                "</html>";

        JLabel instructLable = new JLabel();
        instructPanel.add(instructLable);
        sf.add(instructPanel);

        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructLable.setFont(new Font("Arial", Font.ITALIC, 14));
                instructLable.setForeground(Color.black);
                instructLable.setHorizontalAlignment(JLabel.LEFT);
                instructLable.setBackground(Color.CYAN);
                instructLable.setText(instruction);
            }
        });

    }


}

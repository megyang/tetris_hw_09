package org.cis1200.tetris;

import java.io.*;
import javax.swing.*;

public class StartupForm extends JFrame{
    public StartupForm() {
        this.setBounds(50, 100, 1000, 1000);

        JButton leaderboard = new JButton("leaderboard");
        JButton start = new JButton("start game");
        JButton quit = new JButton("quit game");
        JButton instructions = new JButton("instructions");

        leaderboard.setBounds(150,200,100,50);
        start.setBounds(150,300,100,50);
        quit.setBounds(150,400,100,50);
        instructions.setBounds(150,500,100,50);

        this.add(start);
        this.add(leaderboard);
        this.add(quit);
        this.add(instructions);
    }
    private void btnStartActionPerformed(java.awt.event.ActionEvent e) {
        this.setVisible(false);
        Tetris.start();
    }
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });

    }


}

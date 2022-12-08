package org.cis1200.tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class StartupForm extends JFrame implements ActionListener{
    JButton leaderboardButton = new JButton("leaderboard");
    JButton startButton = new JButton("start game");
    JButton quitButton = new JButton("quit game");
    JButton instructionsButton = new JButton("instructions");
    public StartupForm() {
        /*
        JFrame f=new JFrame("Button Example");
        JButton b=new JButton("Click Here");
        b.setBounds(50,100,95,30);
        f.add(b);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
        b.addActionListener(this);
*/
        this.setTitle("Startup Form");
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
    }


    public static void main(String args[]) {
        StartupForm st = new StartupForm();
        /*
            public void run()
            {
                new GameForm().setVisible(true);

            }
);*/

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Tetris.start();
    }
}

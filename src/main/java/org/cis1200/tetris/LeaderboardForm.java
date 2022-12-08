package org.cis1200.tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class LeaderboardForm extends JFrame {
    JButton mainMenuButton = new JButton("main menu");
    public LeaderboardForm() {
        this.setLayout(new FlowLayout());
        this.add(mainMenuButton);
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                Tetris.showStart();
            }
        });    }

}

package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instructions extends JFrame {
    JButton mainMenuButton = new JButton("main menu");
    Instructions i;

    public Instructions() {
        i = this;
        start();
    }

    public void start() {
        this.setSize(800, 850);
        JPanel instructPanel = new JPanel();
        String instruction = "<html><b>Here is how to play the game</b><br>" +
                "<li>To play Tetris, follow these steps:\n" +
                "\n<ul>" +
                "<li>Start the game by pressing the \"Start Game\" button." +
                "<li>Use the left and right arrow keys on your keyboard to move the falling " +
                "tetrominoes left and right." +
                "<li>Use the up arrow key to rotate the tetrominoes clockwise." +
                "<li>Use the down arrow key to make the tetrominoes fall faster. Space bar to " +
                "drop tetrominoes to bottom." +
                "<li>Try to create horizontal lines without gaps to clear the " +
                "lines and score points." +
                "<li>The game ends when the blocks reach the top of the playing field " +
                "and are unable to fall further." +
                "<li>Click on \"Queue\" Button anytime to add tetrominoes to block " +
                "preview on the right" +
                "<li>Click on the save and load buttons to save a particular game " +
                " state and reload the game" +
                "to that game state." +

                "</ul></html>";

        JLabel instructLabel = new JLabel();
        instructLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        instructLabel.setForeground(Color.black);
        instructLabel.setHorizontalAlignment(JLabel.LEFT);
        instructLabel.setBackground(Color.CYAN);
        instructLabel.setText(instruction);
        instructPanel.add(instructLabel);
        this.add(instructPanel);

        instructPanel.add(mainMenuButton);

        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //this.setVisible(false);
                Tetris.showStart();
            }
        });
        mainMenuButton.setFocusable(false);
    }
}

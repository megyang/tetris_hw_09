package org.cis1200.tetris;

import javax.swing.*;

//JFrame is class inside swing package (javax.swing.JFrame)
//a class extends another class
//game form is a more advanced JFrame and inherits from JFrame
//GameForm is subclass of JFrame (superclass)
//ex: GameForm inherits setVisible from JFrame.
//subclasses inherit public and protected members and override inherited methods
//overriding is giving a method a different body

public class GameForm extends JFrame {
    public GameForm() {
        this.add(new GameArea(10));
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });

    }
}

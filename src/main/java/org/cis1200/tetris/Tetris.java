package org.cis1200.tetris;

import org.cis1200.FileLineIterator;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.util.List;

public class Tetris implements Runnable {
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;
    public static final String PATH_TO_FALLEN = "files/fallenblocks.csv";


    public static void start() {
        gf.setVisible(true);
        gf.startGame();
    }
    public static void showStart() {
        sf.setVisible(true);
    }

    public static void gameOver(int score) {

        String userName = JOptionPane.showInputDialog("game over!\n please enter your name: ");
        gf.setVisible(false);
        lf.addPlayer(userName, score);
    }
    public static void showLeaderboard() {
        lf.setVisible(true);
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gf = new GameForm();
                sf = new StartupForm();
                lf = new LeaderboardForm();
                gf.start();
                sf.setup();
                sf.setVisible(true);
                gf.setVisible(true);
            }
        });
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        gf = new GameForm();
        sf = new StartupForm();
        lf = new LeaderboardForm();
        gf.start();
        sf.setup();
        sf.setVisible(true);
        gf.setVisible(true);
    }
}

package org.cis1200.tetris;

import javax.swing.*;

public class Tetris implements Runnable {
    private static PlayFrame pf;
    private static MainMenuFrame mmf;
    private static LeaderBoardFrame lf;
    private static Instructions i;

    public Tetris() {

    }

    public static void start() {
        pf.setVisible(true);
        pf.startGame();
    }

    public static void showStart() {
        mmf.setVisible(true);
    }

    public static void showInstructions() {
        i.setVisible(true);
    }

    public static void gameOver(int score) {

        String userName = JOptionPane.showInputDialog("game over!\n please enter your name: ");
        pf.setVisible(false);
        lf.addPlayer(userName, score);
    }

    public static void showLeaderboard() {
        lf.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                pf = new PlayFrame();
                mmf = new MainMenuFrame();
                lf = new LeaderBoardFrame();
                i = new Instructions();
                pf.start();
                mmf.setVisible(true);
                pf.setVisible(true);
            }
        });
    }
}

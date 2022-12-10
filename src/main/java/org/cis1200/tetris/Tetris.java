package org.cis1200.tetris;

import org.cis1200.FileLineIterator;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.util.List;

public class Tetris {
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;
    public static final String PATH_TO_FALLEN = "files/fallenblocks.csv";
    private Color[][] fallenblocks;

public Tetris() {
    loadFallenblocks();
}

public Color[][] loadFallenblocks() {
    BufferedReader br = FileLineIterator.fileToReader(PATH_TO_FALLEN);
    List<String[]> fallenList = TetrisParser.csvDataToFallen(br);
    int rownum=fallenList.size();
    int colnum=fallenList.get(0).length;
    fallenblocks = new Color[rownum][colnum];
    int i=0,j=0;
    for (String[] line:fallenList) {
        j=0;
        for (String s : line) {
            if(s.equals("null")){
                fallenblocks[i][j] = null;
            } else {
                String[] colors = s.split(";");
                int r = Integer.parseInt(colors[0]);
                int g = Integer.parseInt(colors[1]);
                int b = Integer.parseInt(colors[2]);
                fallenblocks[i][j] = new Color(r, g, b);
            }
            j++;
        }
        i++;
    }
    int a = fallenblocks.length;
    return fallenblocks;
}
    public static void start() {
        gf.setVisible(true);
        gf.startGame();
    }

    public Color[][] getFallenblocks(){
        return fallenblocks;
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
        Tetris tt=new Tetris();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gf = new GameForm();
                sf = new StartupForm();
                lf = new LeaderboardForm();

                sf.setVisible(true);
                gf.setVisible(true);
            }
        });
    }
}

package org.cis1200.tetris;

import javax.swing.*;
import java.io.IOException;
import java.util.Properties;

public class Tetris implements Runnable {
    private static PlayFrame gf;
    private static MainMenuFrame sf;
    private static LeaderBoardFrame lf;
    private static Instructions i;
    private static final String CONFIG = ".properties";

    //following fields are stored in .properties file
    //if .properties files is not available, the use default value
    public static String PATH_TO_FALLEN = "files/fallenblocks.csv";
    public static String LEADERBOARD_PATH = "files/leaderboard";
    public static String GAME_STATE="files/game_state.properties";


    public static void start() {
        gf.setVisible(true);
        gf.startGame();
    }

    public static void showStart() {
        sf.setVisible(true);
    }

    public static void showInstructions() {
        i.setVisible(true);
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
                gf = new PlayFrame();
                sf = new MainMenuFrame();
                lf = new LeaderBoardFrame();
                i = new Instructions();
                gf.start();
                sf.setup();
                sf.setVisible(true);
                gf.setVisible(true);

                Properties props;
                try {
                    props = RWProperties.ReadFile(CONFIG);
                    PATH_TO_FALLEN = props.getProperty("PATH_TO_FALLEN");
                    LEADERBOARD_PATH = props.getProperty("LEADERBOARD_PATH");
                    GAME_STATE=props.getProperty("GAME_STATE");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println("\nCouldn't open .properties. Using Default configuration");
                }
            }
        });
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        gf = new PlayFrame();
        sf = new MainMenuFrame();
        lf = new LeaderBoardFrame();
        i = new Instructions();
        gf.start();
        sf.setup();
        sf.setVisible(true);
        gf.setVisible(true);


        Properties props;
        try {
            props = RWProperties.ReadFile(CONFIG);
            PATH_TO_FALLEN = props.getProperty("PATH_TO_FALLEN");
            LEADERBOARD_PATH = props.getProperty("LEADERBOARD_PATH");
            GAME_STATE=props.getProperty("GAME_STATE");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("\nCouldn't open .properties. Using Default configuration");
        }


    }

}

package org.cis1200.tetris;

public class Tetris {
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;

    public static void start() {
        gf.setVisible(true);
        gf.startGame();
    }

    public Tetris() {
        gf = new GameForm();
        sf = new StartupForm();
        lf = new LeaderboardForm();
    }
    public static void main(String[] args) {

        Tetris tt=new Tetris();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                sf.setVisible(true);
                gf.setVisible(true);
             //   gf.startGame();
            }
        });
    }
}

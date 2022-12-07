package org.cis1200.tetris;

public class Tetris {
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;

    public static void start() {
        gf.setVisible(true);
        gf.startGame();
    }
    public static void main(String[] args) {

    }
}

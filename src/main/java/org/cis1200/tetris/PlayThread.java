package org.cis1200.tetris;

public class PlayThread extends Thread {
    private GameBoard ga;
    private PlayFrame gf;
    private int score;
    private int totLines;
    private int clearedLines;
    private int level = 1;
    private int sleep = 1000;
    private int speedIncrease = 200;
    public PlayThread(GameBoard ga, PlayFrame gf){
        this.ga = ga;
        this.gf = gf;

        gf.updateScore(score);
        gf.updateLevel(level);
    }

    @Override
    public void run() {
        while (true) {
            ga.produceBlock();
            while (ga.moveDown()) {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    return;
                }
            }
            if (ga.checkBounds()) {
                Tetris.gameOver(score);
                break;
            } else {
                ga.keepFallenBlocks();
                clearedLines = ga.clearLines();
                totLines += clearedLines;
                if (clearedLines == 1) {
                    score += 40*(level);
                } else if (clearedLines == 2) {
                    score += 100*(level);
                } else if (clearedLines == 3) {
                    score += 300*(level);
                } else if (clearedLines == 4) {
                    score += 1200*(level);
                }

                gf.updateScore(score);

                int newLevel = totLines/5 + 1;
                if (newLevel > level) {
                    level = newLevel;
                    gf.updateLevel(level);
                    if (sleep > 100) {
                        sleep -= speedIncrease;
                    }
                }
            }
        }
    }

}

package org.cis1200.tetris;

public class PlayThread extends Thread {
    private final GameBoard gb;
    private final PlayFrame pf;
    private int score;
    private int totLines;
    private int clearedLines;
    private int level = 1;
    private int sleep = 1000;
    private final int speedIncrease = 200;

    public PlayThread(GameBoard gb, PlayFrame pf) {
        this.gb = gb;
        this.pf = pf;

        pf.updateScore(score);
        pf.updateLevel(level);
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public void setScore(int score) {
        this.score = score;
    }
    @Override
    public void run() {
        while (true) {
            gb.produceBlock();
            while (gb.moveDown()) {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    return;
                }
            }
            if (gb.checkBounds()) {
                Tetris.gameOver(score);
                break;
            } else {
                gb.keepFallenBlocks();
                clearedLines = gb.clearLines();
                totLines += clearedLines;
                if (clearedLines == 1) {
                    score += 40 * (level);
                } else if (clearedLines == 2) {
                    score += 100 * (level);
                } else if (clearedLines == 3) {
                    score += 300 * (level);
                } else if (clearedLines == 4) {
                    score += 1200 * (level);
                }

                pf.updateScore(score);

                int newLevel = totLines / 5 + 1;
                if (newLevel > level) {
                    level = newLevel;
                    pf.updateLevel(level);
                    if (sleep > 100) {
                        sleep -= speedIncrease;
                    }
                }
            }
        }
    }

}

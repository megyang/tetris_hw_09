package org.cis1200.tetris;

import java.io.*;
import java.util.Scanner;

public class PlayThread extends Thread {
    private final GameBoard ga;
    private final PlayFrame gf;
    private int score;
    private int totLines;
    private int clearedLines;
    private int level = 1;
    private int sleep = 1000;
    private final int speedIncrease = 200;

    public PlayThread(GameBoard ga, PlayFrame gf) {
        this.ga = ga;
        this.gf = gf;

        gf.updateScore(score);
        gf.updateLevel(level);
    }

    public void saveScore() {
        StringBuilder builder = new StringBuilder();
        BufferedWriter bw;
        FileWriter fw;
        builder.append(score);
        try {
            fw = new FileWriter("files/score.txt", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bw = new BufferedWriter(fw);
        try {
            bw.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveLevel() {
        StringBuilder builder = new StringBuilder();
        BufferedWriter bw;
        FileWriter fw;
        builder.append(level);
        try {
            fw = new FileWriter("files/level.txt", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bw = new BufferedWriter(fw);
        try {
            bw.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadScore() {
        try {
            File myObj = new File("files/score.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                score = Integer.parseInt(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        gf.updateScore(score);
    }

    public void loadLevel() {
        try {
            File myObj = new File("files/level.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                level = Integer.parseInt(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
                    score += 40 * (level);
                } else if (clearedLines == 2) {
                    score += 100 * (level);
                } else if (clearedLines == 3) {
                    score += 300 * (level);
                } else if (clearedLines == 4) {
                    score += 1200 * (level);
                }

                gf.updateScore(score);

                int newLevel = totLines / 5 + 1;
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

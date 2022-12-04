package org.cis1200.tetris;

public class GameThread extends Thread {
    private GameArea ga;
    public GameThread(GameArea ga){
        this.ga = ga;
    }
    @Override
    public void run() {
        while (true) {
            ga.moveDown();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

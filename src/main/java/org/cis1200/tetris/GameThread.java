package org.cis1200.tetris;

public class GameThread extends Thread {
    private GameArea ga;
    public GameThread(GameArea ga){
        this.ga = ga;

    }
    @Override
    public void run() {
        while (true) {
            ga.produceBlock();
            while (ga.moveDown()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}

package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class QueueArea extends JPanel {
    private final int cellSize = 20;
    private LinkedList<Block> blockQueue;
    private final GameBoard ga;
    private int x = 400;
    private int y = 0;

    public QueueArea(GameBoard ga) {
        this.ga = ga;
        this.setBounds(410, 0, 400, 400);
        this.x = this.getBounds().x;
        this.y = this.getBounds().y;
    }

    public void retrieveQueue() {
        blockQueue = ga.getBlockQueue();
    }

    private void drawBlocks(Graphics g) {
        Block block = null;
        Iterator<Block> iterator = blockQueue.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            block = iterator.next();
            if (block != null) {
                drawBlock(g, block, i);
                i++;
            }
        }
    }

    private void drawBlock(Graphics g, Block block, int numInSeq) {
        int blockHeight = block.getHeight();
        int blockWidth = block.getWidth();
        int[][] blockShape = block.getShape();
        Color blockColor = block.getColor();

        for (int i = 0; i < blockHeight; i++) {
            for (int j = 0; j < blockWidth; j++) {
                if (blockShape[i][j] == 1) {
                    int x = this.x + 40 + (block.getX() + j) * cellSize;
                    int y = this.y + (block.getY() + i + numInSeq * 4) * cellSize;

                    drawOneGrid(g, x, y, blockColor);
                }
            }
        }
    }

    private void drawOneGrid(Graphics g, int x, int y, Color blockColor) {
        g.setColor(blockColor);
        g.fillRect(x, y, cellSize, cellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, cellSize, cellSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //call the paintComponent of the superclass (original method)
        super.paintComponent(g);
        g.drawRect(x, 0, 160, 800);
        retrieveQueue();
        if (blockQueue != null) {
            drawBlocks(g);
        }
    }
}

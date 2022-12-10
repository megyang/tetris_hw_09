package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class QueueArea extends JPanel {
    private int rows=4;
    private int columns=4;
    private int cellSize=20;
    private Block queuedBlock;
    private Queue<Block> blockqueue;
    private GameArea ga;
    private int x = 400;
    private int y = 0;


    public QueueArea(GameArea ga){
        this.ga = ga;
        this.setBounds(410,0,400,400);
        //this.setBackground(Color.GREEN);

        cellSize = this.getBounds().width/columns;
        this.x= this.getBounds().x;
        this.y= this.getBounds().y;
        rows = this.getBounds().height/cellSize;
        ga.addBlockToQueue();
        blockqueue = ga.getBlockqueue();
        queuedBlock = blockqueue.peek();
        repaint();
    }
    public void paintQueue () {
        ga.addBlockToQueue();
        blockqueue = ga.getBlockqueue();
        queuedBlock = blockqueue.peek();
        repaint();
    }
    private void drawBlock(Graphics g){
        int blockHeight = queuedBlock.getHeight();
        int blockWidth = queuedBlock.getWidth();
        int[][] blockShape = queuedBlock.getShape();
        Color blockColor = queuedBlock.getColor();

        for (int i = 0; i < blockHeight; i++) {
            for (int j = 0; j < blockWidth; j++) {
                if (blockShape[i][j] == 1) {
                    int x = this.x + (queuedBlock.getX() + j) * cellSize;
                    int y = this.y + (queuedBlock.getY() + i) * cellSize;

                    drawOneGrid(g, x, y, blockColor);
                }
            }
        }
    }
    private void drawOneGrid(Graphics g, int x, int y, Color blockColor){
        g.setColor(blockColor);
        g.fillRect(x,y, cellSize, cellSize);
        g.setColor(Color.black);
        g.drawRect(x,y, cellSize, cellSize);
    }
    //Graphics g is a painter
    @Override
    protected void paintComponent(Graphics g)
    {
        //call the paintComponent of the superclass (original method)
        super.paintComponent(g);
        g.setColor(Color.black);
        for (int i = 0; i < 100/cellSize; i++) {
            for (int j = 0; j < 100/cellSize; j++) {
                g.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
            }
        }
        g.drawRect(410,0,200,460);
        if (queuedBlock != null) {
            drawBlock(g);
        }
    }
}

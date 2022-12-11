package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class QueueArea extends JPanel implements Runnable {
    private int rows=6;
    private int columns=6;
    private int cellSize=20;
    private Block queuedBlock;
    private LinkedList<Block> blockqueue;
    private GameArea ga;
    private int x = 400;
    private int y = 0;


    public QueueArea(GameArea ga){
        this.ga = ga;
        this.setBounds(410,0,400,400);
        //this.setBackground(Color.GREEN);

        this.x= this.getBounds().x;
        this.y= this.getBounds().y;
        rows = this.getBounds().height/cellSize;
        //ga.addBlockToQueue();
        //blockqueue = ga.getBlockqueue();
        //queuedBlock = blockqueue.peek();
        //repaint();
    }

    public void paintQueue () {
        //ga.addBlockToQueue();
        blockqueue = ga.getBlockqueue();
        //repaint();
    }
    private void drawBlocks(Graphics g) {
        Block blk=null;
        int queueSize = blockqueue.size();
        Iterator<Block> it= blockqueue.iterator();
        int i = 0;
        while (it.hasNext()){
            blk = it.next();
            if (blk != null)
                drawBlock(g, blk, i);
            i++;
        }

    }
    private void drawBlock(Graphics g, Block blk, int seq){
        int blockHeight = blk.getHeight();
        int blockWidth = blk.getWidth();
        int[][] blockShape = blk.getShape();
        Color blockColor = blk.getColor();

        for (int i = 0; i < blockHeight; i++) {
            for (int j = 0; j < blockWidth; j++) {
                if (blockShape[i][j] == 1) {
                    int x = this.x + (blk.getX() + j) * cellSize;
                    int y = this.y + (blk.getY() + i + seq*4) * cellSize;

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

        if (blockqueue != null) {
            drawBlocks(g);
        }
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            repaint();
        } catch (InterruptedException e) {
            return;
        }
    }
}

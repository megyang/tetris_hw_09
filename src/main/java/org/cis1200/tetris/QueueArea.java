package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class QueueArea extends JPanel {
    private int cellSize=20;
    private LinkedList<Block> blockqueue;
    private GameBoard ga;
    private int x = 400;
    private int y = 0;


    public QueueArea(GameBoard ga){
        this.ga = ga;
        this.setBounds(410,0,400,400);
        //this.setBackground(Color.GREEN);
        this.x= this.getBounds().x;
        this.y= this.getBounds().y;
    }

    //retrieve queue from game area
    public void retrieveQueue() {
        blockqueue = ga.getBlockqueue();
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
                    int x = this.x +40 + (blk.getX() + j) * cellSize;
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
        g.drawRect(x,0,160,800);
        retrieveQueue();
        if (blockqueue != null) {
            drawBlocks(g);
        }
    }
}

package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;

//draw on JFrame
//override paintComponent method from JPanel
public class GameArea extends JPanel {
    private int rows;
    private int columns;
    private int cellSize;

    private Block block;
    public GameArea(int inColumns) {
        //location of GameArea on the GameForm
        this.setBounds(50,50,400,400);
        this.setBackground(Color.lightGray);

        columns = inColumns;
        cellSize = this.getBounds().width/columns;
        rows = this.getBounds().height/cellSize;

        produceBlock();
    }

    public void produceBlock() {
        block = new Block(new int[][] {{1,0},{1,0},{1,1}}, Color.blue);
    }

    public void moveDown() {
        block.moveDown();
        repaint();
    }
    private void drawBlock(Graphics g){
        int blockHeight = block.getHeight();
        int blockWidth = block.getWidth();
        int[][] blockShape = block.getShape();
        Color blockColor = block.getColor();
        for (int i = 0; i < blockHeight; i++) {
            for (int j = 0; j < blockWidth; j++) {
                if (blockShape[i][j] == 1) {
                    int x = (block.getX() + j) * cellSize;
                    int y = (block.getY() + i) * cellSize;

                    g.setColor(blockColor);
                    g.fillRect(x,y, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawRect(x,y, cellSize, cellSize);
                }
            }
        }
    }
    //Graphics g is a painter
    @Override
    protected void paintComponent(Graphics g)
    {
        //call the paintComponent of the superclass (original method)
        super.paintComponent(g);
        //g.fillRect(50,50,100,100);
        drawBlock(g);
    }
}
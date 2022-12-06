package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;

//draw on JFrame
//override paintComponent method from JPanel
public class GameArea extends JPanel {
    private int rows;
    private int columns;
    private int cellSize;
    private Color[][] fallenBlocks;

    private Block block;
    public GameArea(int inColumns) {
        //location of GameArea on the GameForm
        this.setBounds(0,0,400,400);
        this.setBackground(Color.lightGray);

        columns = inColumns;
        cellSize = this.getBounds().width/columns;
        rows = this.getBounds().height/cellSize;

        fallenBlocks = new Color[rows][columns];
    }

    public void produceBlock() {
        block = new Block(new int[][] {{1,0},{1,0},{1,1}}, Color.blue);
        block.startPoint(columns);
    }

    private boolean checkBounds(){
        if (block.getY() + block.getHeight() == rows) {
            return false;
        } else {
            return true;
        }
    }
    public boolean moveDown() {
        if (checkBounds() == false) {
            keepFallenBlocks();
            return false;
        }
        block.moveDown();
        repaint();
        return true;
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

                    drawOneGrid(g, x, y, blockColor);
                }
            }
        }
    }

    private void keepFallenBlocks() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();

        int x = block.getX();
        int y = block.getY();

        Color color = block.getColor();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (shape[i][j] == 1) {
                    fallenBlocks[i + y][j + x] = color;
                }
            }
        }
    }

    private void drawFallenBlocks (Graphics g) {
        Color color;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j ++) {
                color = fallenBlocks[i][j];
                if (color != null) {
                    int x = j * cellSize;
                    int y = i * cellSize;

                    drawOneGrid(g, x, y, color);

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
        g.drawRect(0,0,400,400);
        drawFallenBlocks(g);
        drawBlock(g);
    }
}
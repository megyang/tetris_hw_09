package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;

//draw on JFrame
//override paintComponent method from JPanel
public class GameArea extends JPanel {
    private int rows;
    private int columns;
    private int cellSize;

    private int[][] LBlock = {{1,0},{1,0},{1,1}};
    public GameArea(int inColumns) {
        //location of GameArea on the GameForm
        this.setBounds(50,50,400,400);
        this.setBackground(Color.lightGray);

        columns = inColumns;
        cellSize = this.getBounds().width/columns;
        rows = this.getBounds().height/cellSize;
    }

    private void drawBlock(Graphics g, int[][] block){
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[i].length; j++) {
                if (block[i][j] == 1) {
                    g.setColor(Color.red);
                    g.fillRect(j*cellSize + 100, i*cellSize + 100, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawRect(j*cellSize + 100, i*cellSize + 100, cellSize, cellSize);
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
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns*2; j ++) {
                g.drawRect(i * cellSize + 100, j*cellSize + 100, cellSize, cellSize);
            }
        }

        drawBlock(g,LBlock);
    }
}
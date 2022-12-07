package org.cis1200.tetris;

import org.cis1200.tetrisblocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

//draw on JFrame
//override paintComponent method from JPanel
public class GameArea extends JPanel {
    private int rows;
    private int columns;
    private int cellSize;
    private Color[][] fallenBlocks;

    private Block block;
    private Block[] blocks;
    public GameArea(int inColumns) {
        //location of GameArea on the GameForm
        this.setBounds(0,0,400,800);
        this.setBackground(Color.lightGray);

        columns = inColumns;
        cellSize = this.getBounds().width/columns;
        rows = this.getBounds().height/cellSize;

        fallenBlocks = new Color[rows][columns];

        blocks = new Block[]{ new IShape(),
                                new JShape(),
                                new LShape(),
                                new OShape(),
                                new SShape(),
                                new TShape(),
                                new ZShape()};
    }

    public void produceBlock() {
        Random r = new Random();
        block = blocks[r.nextInt(blocks.length)];
        block.startPoint(columns);
    }

    private boolean checkBottomBounds() {
        if (block.getY() + block.getHeight() == rows) {
            return false;
        } else {
            int[][] shape = block.getShape();
            int w = block.getWidth();
            int h = block.getHeight();

            for(int col = 0; col < w; col++) {
                for(int row = h - 1; row >=0; row --) {
                    if (shape[row][col] != 0) {
                        int x = col + block.getX();
                        int y = row + block.getY() + 1;
                        if (y < 0) {
                            //terminates the loop
                            break;
                        }
                        if(fallenBlocks[y][x] != null) {
                            return false;
                        }
                        break;
                    }
                }
            }
            return true;
        }
    }

    private boolean checkLeftBounds() {
        if (block.getX()==0) {
            return false;
        } else {
            int[][] shape = block.getShape();
            int w = block.getWidth();
            int h = block.getHeight();

            for(int row = 0; row < h; row++) {
                for(int col = 0; col < w; col++) {
                    if (shape[row][col] != 0) {
                        int x = col + block.getX() - 1;
                        int y = row + block.getY();
                        if (y < 0) {
                            //terminates the loop
                            break;
                        }
                        if(fallenBlocks[y][x] != null) {
                            return false;
                        }
                        break;
                    }
                }
            }

            return true;
        }
    }

    private boolean checkRightBounds() {
        if (block.getX() + block.getWidth() == columns) {
            return false;
        } else {
            int[][] shape = block.getShape();
            int w = block.getWidth();
            int h = block.getHeight();

            for(int row = 0; row < h; row++) {
                for(int col = w - 1; col >=0 ; col--) {
                    if (shape[row][col] != 0) {
                        int x = col + block.getX() + 1;
                        int y = row + block.getY();
                        if (y < 0) {
                            //terminates the loop
                            break;
                        }
                        if(fallenBlocks[y][x] != null) {
                            return false;
                        }
                        break;
                    }
                }
            }
            return true;
        }
    }
    public boolean checkBounds() {
        if (block.getY() < 0) {
            block = null;
            return true;
        } else {
            return false;
        }
    }
    public boolean moveDown() {
        if (!checkBottomBounds()) {
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }

    public void moveRight() {
        if (block == null) {
            return;
        }
        if (checkRightBounds()) {
            block.moveRight();
            repaint();
        }
    }

    public void moveLeft() {
        if (block == null) {
            return;
        }
        if (checkLeftBounds()) {
            block.moveLeft();
            repaint();
        }
    }

    public void drop() {
        if (block == null) {
            return;
        }
        while(checkBottomBounds()){
            block.moveDown();
        }
    }

    public void rotate() {
        if (block == null) {
            return;
        }
        block.rotate();
        if(block.getX()<0) {
            block.setX(0);
        } else if (block.getX() + block.getWidth() >= columns) {
            block.setX(columns - block.getWidth());
        } else if (block.getY() + block.getHeight() >= rows)
            block.setY(rows - block.getHeight());

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();


        //left bound
        for(int row = 0; row < h; row++) {
            for(int col = 0; col < w; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) {
                        break;
                    }
                    if (x < 0) {
                        block.setX(0);
                    } else if(fallenBlocks[y][x] != null) {
                        block.moveRight();
                        block.moveRight();
                    }
                    break;
                }
            }
        }

        //right bound
        for(int row = 0; row < h; row++) {
            for(int col = w - 1; col >=0 ; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) {
                        break;
                    }
                    if(fallenBlocks[y][x] != null) {
                        block.moveLeft();
                        if (x < 0) {
                            block.setX(0);
                        }
                        if (x > columns) {
                            block.setX(columns - block.getWidth());
                        }
                    }

                    break;
                }
            }
        }

        //bottom bound
        for(int col = 0; col < w; col++) {
            for(int row = h - 1; row >=0; row --) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) {
                        //terminates the loop
                        break;
                    }
                    if(fallenBlocks[y][x] != null) {
                        block.moveUp();
                    }
                    break;
                }
            }
        }

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

                    drawOneGrid(g, x, y, blockColor);
                }
            }
        }
    }

    public int clearLines() {
        boolean lineFilled;
        int linesCleared = 0;
        for (int row = rows - 1; row >= 0; row--) {
            lineFilled = true;
            for (int col = 0; col < columns; col++) {
                if (fallenBlocks[row][col] == null) {
                    lineFilled = false;
                    break;
                }
            }
            if (lineFilled) {
                linesCleared++;
                clearLine(row);
                shiftDown(row);
                row++;
                clearLine(0);
                repaint();
            }
        }
        return linesCleared;
    }

    private void clearLine(int row) {
        for (int i = 0; i < columns; i++) {
            fallenBlocks[row][i] = null;
        }
    }

    private void shiftDown(int row) {
        for (int r = row; r > 0; r--) {
            for (int c = 0; c < columns; c++) {
                fallenBlocks[r][c] = fallenBlocks[r-1][c];
            }
        }
    }
    public void keepFallenBlocks() {
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
        for (int i = 0; i < 400/cellSize; i++) {
            for (int j = 0; j < 800/cellSize; j++) {
                g.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
            }
        }
        g.drawRect(0,0,400,800);
        drawFallenBlocks(g);
        drawBlock(g);
    }
}
package org.cis1200.tetris;

import com.google.gson.reflect.TypeToken;
import org.cis1200.FileLineIterator;
import org.cis1200.tetrisblocks.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import com.google.gson.*;

import static org.cis1200.tetris.Tetris.PATH_TO_FALLEN;

//draw on JFrame
//override paintComponent method from JPanel
public class GameArea extends JPanel {
    private int rows;
    private int columns;
    private int cellSize;
    private Color[][] fallenBlocks;

    private Block block;
    private Block[] blocks;
    private Block queuedBlock;
    private LinkedList<Block> blockqueue=new LinkedList<>();

    private GameForm gf;

    public GameArea(GameForm gf, int inColumns) {
        this.gf = gf;
        //location of GameArea on the GameForm
        blocks = new Block[]{ new IShape(),
                new JShape(),
                new LShape(),
                new OShape(),
                new SShape(),
                new TShape(),
                new ZShape()};

        this.setBounds(0,0,400,800);
        this.setBackground(Color.lightGray);

        columns = inColumns;
        cellSize = this.getBounds().width/columns;
        rows = this.getBounds().height/cellSize;
    }

    public void resetBlocks() {
        fallenBlocks = new Color[rows][columns];
    }

    public void produceBlock() {
        if(blockqueue.size()>0) {
            block = blockqueue.remove();
            System.out.println("Queue size="+blockqueue.size()+"\n");

        } else {
            Random r = new Random();
            block = blocks[r.nextInt(blocks.length)];
            System.out.println("Generated\n");
        }
        block.startPoint(columns);
    }

    /*
     * Cannot add blocks[] content directly to the queue. Each element in the queue must be an
     * independent instance. For example, you can have the same shape in the queue multiple times.
     * Each of them must have its own lifecycle
     */
    public void addBlockToQueue() {
        Random r = new Random();
        Block tmpblock = blocks[r.nextInt(blocks.length)];
        Block newblock = null;
        if ( tmpblock instanceof IShape) {
            newblock = new IShape();
        } else if (tmpblock instanceof JShape) {
            newblock = new JShape();
        } else if (tmpblock instanceof LShape) {
            newblock = new LShape();
        } else if (tmpblock instanceof OShape) {
            newblock = new OShape();
        } else if (tmpblock instanceof SShape) {
            newblock = new SShape();
        } else if (tmpblock instanceof TShape) {
            newblock = new TShape();
        } else if (tmpblock instanceof ZShape) {
            newblock = new ZShape();
        }

        blockqueue.add(newblock);
    }

//Return duplicated LinkedList for proper encapsulation
    public LinkedList<Block> getBlockqueue() {
        LinkedList<Block> dupblk = new LinkedList<>();
        dupblk.addAll(blockqueue);
        return dupblk;
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
                        if(y < fallenBlocks.length && fallenBlocks[y][x] != null) {
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
                        if(x > 0 && fallenBlocks[y][x] != null) {
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
                        if(x < fallenBlocks[y].length && fallenBlocks[y][x] != null) {
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
        //Dynamic dispatching
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
                    } else if(x >= 0 && fallenBlocks[y][x] != null) {
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
                    if (x >= columns) {
                        block.setX(columns - block.getWidth());
                    } else if(x < fallenBlocks[y].length && fallenBlocks[y][x] != null) {
                        block.moveLeft();
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

                    if(y > fallenBlocks.length && fallenBlocks[y][x] != null) {
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
        if(fallenBlocks==null) {
            return;
        }
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

    public void saveFallenBlocks() {
        StringBuilder builder = new StringBuilder();
        BufferedWriter bw;
        FileWriter fw;
        String color;
        for(int i = 0; i < fallenBlocks.length; i++) {
            for (int j = 0; j < fallenBlocks[i].length; j++) {
                if (fallenBlocks[i][j] != null) {
                    int r = fallenBlocks[i][j].getRed();
                    int g = fallenBlocks[i][j].getGreen();
                    int b = fallenBlocks[i][j].getBlue();
                    color = String.valueOf(r) + ";" + String.valueOf(g) + ";" + String.valueOf(b);
                    builder.append(color);
                } else {
                    builder.append("null");
                }
                if(j < fallenBlocks[i].length - 1)//if this is not the last row element
                    builder.append(",");//then add comma (if you don't like commas you can use spaces)
            }
            if(i<fallenBlocks.length-1) {
                builder.append("\n");
            }
        }

        try {
            fw = new FileWriter(PATH_TO_FALLEN,false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bw = new BufferedWriter(fw);
        try {
            bw.write(builder.toString());

            //bw.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadFallenblocks() {
        BufferedReader br = FileLineIterator.fileToReader(PATH_TO_FALLEN);
        List<String[]> fallenList = TetrisParser.csvDataToFallen(br);
        resetBlocks();
        int i=0,j=0;
        for (String[] line:fallenList) {
            j=0;
            for (String s : line) {
                if(s.equals("null")){
                    fallenBlocks[i][j] = null;
                } else {
                    String[] colors = s.split(";");
                    int r = Integer.parseInt(colors[0]);
                    int g = Integer.parseInt(colors[1]);
                    int b = Integer.parseInt(colors[2]);
                    fallenBlocks[i][j] = new Color(r, g, b);
                }
                j++;
            }
            i++;
        }
        repaint();
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
        if (block != null) {
            drawBlock(g);
        }
        //gf.qa.repaint();
    }
}
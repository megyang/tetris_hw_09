package org.cis1200.tetris;

import java.awt.*;

public class Block {
    private int[][] shape;
    private Color color;
    private int x;
    private int y;

    public Block(int[][] shape,Color color){
        this.shape = shape;
        this.color = color;
        x = 2;
        y = 3;
    }

    public int[][] getShape(){
        return shape;
    }
    public Color getColor(){
        return color;
    }
    public int getHeight(){
        return shape.length;
    }
    public int getWidth(){
        return shape[0].length;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public void fall()  {}
    public void moveDown() {}
    public void moveLeft() {}
    public void moveRight() {}
}

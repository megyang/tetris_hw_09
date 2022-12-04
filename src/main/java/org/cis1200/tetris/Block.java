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
    }
    public void startPoint(int gridWidth) {
        y = -getHeight();
        x = (gridWidth - getWidth())/2;

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
    public void moveDown() { y++; }
    public void moveLeft() { x--; }
    public void moveRight() {x++; }
}

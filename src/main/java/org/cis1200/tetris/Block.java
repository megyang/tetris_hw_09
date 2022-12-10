package org.cis1200.tetris;

import java.awt.*;
import java.util.Random;

public class Block {
    private int[][] shape;
    private Color color;
    private int x;
    private int y;
    private int[][][] shapes;
    private int rotationNum;

    public Block(int[][] shape,Color color){
        this.shape = shape;
        this.color = color;
        diffShapes();
    }
    private void diffShapes() {
        shapes = new int[4][][];
        for (int i = 0; i < 4; i++) {
            int rows = shape[0].length;
            int cols = shape.length;
            shapes[i] = new int[rows][cols];
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x ++) {
                    shapes[i][y][x] = shape[cols - x - 1][y];
                }
            }
            shape = shapes[i];
        }
    }
    public void startPoint(int gridWidth) {
        //Random r = new Random();
        //r.nextInt(4);

        rotationNum = 0;
        shape = shapes[rotationNum];

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
    public int getRotationNum() { return rotationNum; }
    public void setX(int newX) { x = newX; }
    public void setY(int newY) {y = newY; }
    public int getY() { return y; }
    public void moveDown() { y++; }
    public void moveLeft() { x--; }
    public void moveRight() {x++; }
    public void moveUp() { y--; }
    public void rotate() {
        rotationNum++;
        if (rotationNum > 3) {
            rotationNum = 0;
        }
        shape = shapes[rotationNum];
    }
}

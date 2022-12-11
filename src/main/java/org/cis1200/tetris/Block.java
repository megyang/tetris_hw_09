package org.cis1200.tetris;

import java.awt.*;
import java.util.Random;

public class Block implements Comparable{
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

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

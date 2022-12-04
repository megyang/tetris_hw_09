package org.cis1200.tetris;

import java.awt.*;

public class Block {
    private int[][] shape;
    private Color color;

    public Block(int[][] shape,Color color){
        this.shape = shape;
        this.color = color;
    }
}

package org.cis1200.tetrisblocks;

import org.cis1200.tetris.Block;

import java.awt.*;

public class JShape extends Block{
    public JShape() {
        super(new int[][]{{0,1},{0,1},{1,1}}, Color.blue);
    }
}

package org.cis1200.tetrisblocks;

import org.cis1200.tetris.Block;

import java.awt.*;

public class LShape extends Block{
    public LShape() {
        super(new int[][]{{1,0},{1,0},{1,1}}, Color.orange);
    }
}
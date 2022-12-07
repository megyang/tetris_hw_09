package org.cis1200.tetrisblocks;

import org.cis1200.tetris.Block;

import java.awt.*;

public class ZShape extends Block {
    public ZShape() {
        super(new int[][]{{1,1,0},{0,1,1}}, Color.red);
    }
}
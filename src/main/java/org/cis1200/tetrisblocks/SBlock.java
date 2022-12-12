package org.cis1200.tetrisblocks;

import org.cis1200.tetris.Block;

import java.awt.*;

public class SBlock extends Block {
    public SBlock() {
        super(new int[][]{{0, 1, 1}, {1, 1, 0}}, Color.green);
    }
}

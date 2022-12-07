package org.cis1200.tetrisblocks;

import org.cis1200.tetris.Block;

import java.awt.*;

public class TShape extends Block {
    public TShape() {
        super(new int[][]{{1,1,1},{0,1,0}}, Color.magenta);
    }
}
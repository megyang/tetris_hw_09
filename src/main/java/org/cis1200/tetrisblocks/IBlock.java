package org.cis1200.tetrisblocks;

import org.cis1200.tetris.Block;

import java.awt.*;

public class IBlock extends Block{
    public IBlock() {
        super(new int[][]{{1, 1, 1, 1}}, Color.CYAN);
    }
    @Override
    public void rotate() {
        super.rotate();
        if (this.getWidth() == 1) {
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        } else {
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}

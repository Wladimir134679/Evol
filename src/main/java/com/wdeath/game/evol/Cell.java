package com.wdeath.game.evol;

import java.awt.*;

public abstract class Cell {

    public abstract void draw(Graphics2D g, int x, int y);
    public abstract void update(int x, int y);

}

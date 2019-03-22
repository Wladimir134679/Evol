package com.wdeath.game.evol;

import com.wdeath.game.evol.gen.MindEngine;
import com.wdeath.game.evol.gen.gens.Gemmation;

import java.awt.*;

public class Bot extends Cell {

    public MindEngine mind;
    public Color color;
    public World world;
    public int x, y;
    public int health = 5;

    public Bot(World world){
        this.world = world;
        mind = new MindEngine();
        mind.bot = this;
        color = Color.RED;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(color);
        g.fillRect(x * Screen.WIDTH_CELL, y * Screen.HEIGHT_CELL, Screen.WIDTH_CELL - 1, Screen.HEIGHT_CELL - 1);
    }

    @Override
    public void update(int x, int y) {
        this.x = x; this.y = y;
        mind.process();
        if(health >= 500){
            Gemmation gem = new Gemmation();
            gem.process(mind, 0);
        }
        health -= 1;
        death();
    }

    public void death(){
        if(health > 0)
            return;

        world.setCell(null, x, y);
    }


    public boolean isMy(Bot b){
        int s = 0;
        for(int i = 0; i < mind.mind.length; i++){
            if(mind.mind[i] != b.mind.mind[i]) {
                s++;
            }
            if(s > 2)
                return false;
        }
        return true;
    }

    public boolean move(int xc, int yc){
        return setPosition(x + xc, y + yc);
    }

    public boolean setPosition(int xc, int yc){
        xc = getXW(xc);
        yc = getYW(yc);

        if(world.getCell(xc, yc) != null)
            return false;
        world.setCell(null, x, y);
        world.setCell(this, xc, yc);
        return true;
    }

    public boolean isFreeDirection(){
        boolean m = false;
        if(!m)
            m = isFree(x + 1, y);
        if(!m)
            m = isFree(x - 1, y);
        if(!m)
            m = isFree(x, y + 1);
        if(!m)
            m = isFree(x, y - 1);
        return m;
    }

    public boolean isFree(int xc, int yc){
        xc = getXW(xc);
        yc = getYW(yc);
        return world.getCell(xc, yc) == null;
    }

    public boolean isBoot(int xc, int yc){
        xc = getXW(xc);
        yc = getYW(yc);
        Cell cell = world.getCell(xc, yc);
        if(cell instanceof Bot)
            return true;
        return false;
    }

    public int getXW(int xc){
        if(xc < 0)
            xc = world.w + xc;
        if(xc >= world.w)
            xc = xc - world.w;
        return xc;
    }

    public int getYW(int yc){
        if(yc >= world.h)
            yc = yc - world.h;
        if(yc < 0)
            yc = world.h + yc;
        return yc;
    }
}

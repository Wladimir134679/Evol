package com.wdeath.game.evol;

import java.awt.*;

public class World {

    public int w, h;

    private Cell[][] cells;

    private Cell[][] buf;

    public World(int w, int h){
        this.w = w;
        this.h = h;
        cells = new Cell[w][h];
        buf = new Cell[w][h];
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public void setCell(Cell cell, int x, int y){
        this.buf[x][y] = cell;
    }

    public void copyByf(){
        for(int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                cells[x][y] = buf[x][y];
            }
        }
    }

    public void copyCells(){
        for(int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                buf[x][y] = cells[x][y];
            }
        }
    }

    public void draw(Graphics2D g){
        for(int x = 0; x < w; x++){
            for(int y = 0; y < h; y++){
                Cell c = cells[x][y];
                if(c == null)
                    continue;
                c.draw(g, x, y);
            }
        }
    }

    public void update(){
        for(int x = 0; x < w; x++){
            for(int y = 0; y < h; y++){
                Cell c = cells[x][y];
                if(c == null)
                    continue;
                c.update(x, y);
            }
        }
    }
}

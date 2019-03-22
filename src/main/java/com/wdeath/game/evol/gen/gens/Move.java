package com.wdeath.game.evol.gen.gens;

import com.wdeath.game.evol.Bot;
import com.wdeath.game.evol.gen.MindEngine;

public class Move extends Mind {

    @Override
    public int process(MindEngine mind, int utk) {
        int t = mind.mind[mind.getUTKW(utk + 1)];
        int dir = t % 4;
        boolean m = false;
        int r = 0;
        if(dir == 0) {
            if(mind.bot.move(1, 0)){
                m = true;
            }else{
                r = move(mind, 1, 0);
            }
        }
        if(dir == 1) {
            if(mind.bot.move(-1, 0)){
                m = true;
            }else{
                r = move(mind, -1, 0);
            }
        }
        if(dir == 2) {
            if(mind.bot.move(0, 1)){
                m = true;
            }else{
                r = move(mind, 0, 1);
            }
        }
        if(dir == 3) {
            if(mind.bot.move(0, -1)){
                m = true;
            }else{
                r = move(mind, 0, -1);
            }
        }
//        if(m)
            mind.bot.health -= 5;

        if(r != 0)
            return utk + 1 +  mind.mind[r];
        return utk + 1;
    }

    @Override
    public boolean end() {
        return true;
    }

    private int move(MindEngine mind, int x, int y){
        int xc = mind.bot.getXW(mind.bot.x + x);
        int yc = mind.bot.getYW(mind.bot.y + y);

        if(mind.bot.isBoot(xc, yc)) {
            if (!mind.bot.isMy((Bot)(mind.bot.world.getCell(xc, yc))))
                return 1;
            else
                return 2;
        }
        if(mind.bot.isFree(xc, yc))
            return 3;

        return 0;
    }
}

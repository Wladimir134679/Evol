package com.wdeath.game.evol.gen.gens;

import com.wdeath.game.evol.Bot;
import com.wdeath.game.evol.gen.MindEngine;

public class Watch extends Mind {

    @Override
    public int process(MindEngine mind, int utk) {
        int dir = mind.mind[mind.getUTKW(utk + 1)] % 4;

        int s = 0;
        if(dir == 0)
            s = watch(mind, 1, 0);
        if(dir == 1)
            s = watch(mind, 0, 1);
        if(dir == 2)
            s = watch(mind, 1, 0);
        if(dir == 3)
            s = watch(mind, 0, -1);

        mind.bot.health -= 5;
        return utk + mind.mind[mind.getUTKW(utk + 1 + s)];
    }

    @Override
    public boolean end() {
        return false;
    }

    private int watch(MindEngine mind, int x, int y){
        int xc = mind.bot.getXW(mind.bot.x + x);
        int yc = mind.bot.getYW(mind.bot.y + y);

        if(mind.bot.isBoot(xc, yc))
            return 1;
        if(mind.bot.isFree(xc, yc))
            return 2;

        return 0;
    }
}

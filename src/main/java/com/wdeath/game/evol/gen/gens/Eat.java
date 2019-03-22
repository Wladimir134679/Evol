package com.wdeath.game.evol.gen.gens;

import com.wdeath.game.evol.Bot;
import com.wdeath.game.evol.Cell;
import com.wdeath.game.evol.gen.MindEngine;

public class Eat extends Mind{

    @Override
    public int process(MindEngine mind, int utk) {
        int t = mind.mind[mind.getUTKW(utk + 1)];
        int dir = t % 4;
        boolean m = false;
        if(dir == 0)
            m = eat(mind, utk, 1, 0);
        if(dir == 1)
            m = eat(mind, utk, 0, 1);
        if(dir == 2)
            m = eat(mind, utk, -1, 0);
        if(dir == 3)
            m = eat(mind, utk, 0, -1);
        if(m)
            return utk + 1;
        return utk + 2;
    }

    @Override
    public boolean end() {
        return true;
    }

    private boolean eat(MindEngine mind, int utk, int x, int y){
        int xc = mind.bot.getXW(mind.bot.x + x);
        int yc = mind.bot.getYW(mind.bot.y + y);

        Cell cell = mind.bot.world.getCell(xc, yc);
        if(!(cell instanceof Bot))
            return false;
        if(mind.bot.isMy(((Bot)cell)))
            return false;

        mind.bot.health += 10;
        mind.bot.world.setCell(null, xc, yc);

        return true;
    }
}

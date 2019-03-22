package com.wdeath.game.evol.gen.gens;

import com.wdeath.game.evol.Bot;
import com.wdeath.game.evol.MainApp;
import com.wdeath.game.evol.gen.MindEngine;

public class Gemmation extends Mind {

    @Override
    public int process(MindEngine mind, int utk) {
        mind.bot.health -= 20;
        if(mind.bot.health <= 0)
            return utk + 1;
        if(!mind.bot.isFreeDirection())
            return utk + 1;

        int dir = MainApp.rnd.nextInt(4);
        boolean n = false;
        int s = 0;
        while(!n && s < 4){
            if(!n && dir == 0)
                n = gem(mind,mind.bot.x + 1, mind.bot.y + 0);
            if(!n && dir == 1)
                n = gem(mind,mind.bot.x + 0, mind.bot.y + 1);
            if(!n && dir == 2)
                n = gem(mind,mind.bot.x + -1, mind.bot.y + 0);
            if(!n && dir == 3)
                n = gem(mind, mind.bot.x + 0, mind.bot.y + -1);
            dir++;
            if(dir >= 4)
                dir = 0;
            s++;
        }
        return utk + 2;
    }


    private boolean gem(MindEngine mind, int xc, int yc){
        xc = mind.bot.getXW(xc);
        yc = mind.bot.getYW(yc);
        if(!mind.bot.isFree(xc, yc))
            return false;
        mind.bot.health /= 2;
        Bot b = new Bot(mind.bot.world);
        MindEngine mindClone = mind.clone(MainApp.rnd, 0.25f);
        b.mind = mindClone;
        b.health = mind.bot.health;
        mind.bot.world.setCell(b, xc, yc);
        return true;
    }

    @Override
    public boolean end() {
        return true;
    }
}

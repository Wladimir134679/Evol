package com.wdeath.game.evol.gen.gens;

import com.wdeath.game.evol.gen.MindEngine;

public class EnergyCheck extends Mind {
    @Override
    public int process(MindEngine mind, int utk) {
        int t = mind.getUTKW(utk + 1);
        int en = 8 * t;
        mind.bot.health -= 2;
        if(mind.bot.health < en){
            return mind.getUTKW(utk + mind.mind[mind.getUTKW(utk + 2)]);
        }else{
            return mind.getUTKW(utk + mind.mind[mind.getUTKW(utk + 3)]);
        }
    }

    @Override
    public boolean end() {
        return false;
    }
}

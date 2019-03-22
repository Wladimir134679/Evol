package com.wdeath.game.evol.gen.gens;

import com.wdeath.game.evol.gen.MindEngine;

public class Photosynthesis extends Mind {
    @Override
    public int process(MindEngine mind, int utk) {
        mind.bot.health += 5;
        return utk + 1;
    }

    @Override
    public boolean end() {
        return false;
    }
}

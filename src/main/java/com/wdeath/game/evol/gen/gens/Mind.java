package com.wdeath.game.evol.gen.gens;

import com.wdeath.game.evol.gen.MindEngine;

public abstract class Mind {

    public abstract int process(MindEngine mind, int utk);
    public abstract boolean end();
}

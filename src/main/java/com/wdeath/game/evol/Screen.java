package com.wdeath.game.evol;

import com.wdeath.game.GameCanvas;
import com.wdeath.game.GameScreen;
import com.wdeath.game.evol.gen.MindEngine;

import java.util.Random;

public class Screen implements GameScreen {

    public static final int WIDTH_CELL = 4, HEIGHT_CELL = 4;

    public World world;

    @Override
    public void open() {
        int w = MainApp.APP.getConfig().width / WIDTH_CELL;
        int h = MainApp.APP.getConfig().height / HEIGHT_CELL;
        world = new World(w, h);
        Random rnd = new Random(2);
        for(int i = 0; i < (world.w / 2) * (world.h / 2); i ++) {
            Bot b = new Bot(world);
            int x = rnd.nextInt(world.w);
            int y = rnd.nextInt(world.h);
            b.setPosition(x, y);
            for(int m = 0; m < b.mind.mind.length; m++){
                b.mind.mind[m] = 1;
            }
            int num = rnd.nextInt(MindEngine.MAX / 2);
            for(int n = 0; n < num; n++) {
                int mi = rnd.nextInt(MindEngine.MAX);
                int p = rnd.nextInt(MindEngine.MAX);
                b.mind.mind[mi] = p;
            }
        }
        world.copyByf();
    }

    @Override
    public void draw(GameCanvas canvas) {
        world.draw(canvas.getGraphics());
    }

    @Override
    public void update(float delta) {
        world.copyCells();
        world.update();
        world.copyByf();
    }

    @Override
    public void close() {

    }
}

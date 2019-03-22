package com.wdeath.game.evol;

import com.wdeath.game.AppConfig;
import com.wdeath.game.ApplicationGame;
import com.wdeath.game.evol.gen.MindEngine;
import com.wdeath.game.evol.gen.gens.Mind;

import java.util.Random;

public class MainApp {

    public static Random rnd;
    public static ApplicationGame APP;

    public static void main(String[] args) {
        rnd = new Random(2);
        MindEngine.init();
        System.out.println("Start Game");
        AppConfig config = new AppConfig();
        config.width = 800;
        config.height = 600;
        config.title = "Эволюция";
        Screen screen = new Screen();
        APP = new ApplicationGame(config, screen);
        APP.start();
    }
}

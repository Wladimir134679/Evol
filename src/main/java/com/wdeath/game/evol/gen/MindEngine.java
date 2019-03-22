package com.wdeath.game.evol.gen;

import com.wdeath.game.evol.Bot;
import com.wdeath.game.evol.gen.gens.*;

import java.util.HashMap;
import java.util.Random;

public class MindEngine {

    public static HashMap<Integer, Mind> minds;

    public static void init(){
        minds = new HashMap<>();
        minds.put(0, new Move());
        minds.put(1, new Photosynthesis());
        minds.put(2, new Eat());
        minds.put(3, new Watch());
        minds.put(4, new EnergyCheck());
        minds.put(5, new Gemmation());
    }

    public static final int MAX = 64;

    public int utk = 0;
    public int[] mind;
    public Bot bot;
    public int currentProcess = 0;
    private boolean isEnd = false;

    public MindEngine(){
        mind = new int[MAX];
    }

    public void process(){
        currentProcess = 0;
        while(!isEnd && currentProcess < 15){
            currentProcess++;

            utk = getUTKW(utk);
            Mind mindM = minds.get(mind[utk]);
            if(mindM == null) {
                utk = getUTKW(utk + mind[utk]);
                continue;
            }
            utk = mindM.process(this, utk);
            isEnd = mindM.end();
        }
    }

    public MindEngine clone(Random rnd, float f){
        int[] mindC = new int[MAX];
        for(int i = 0; i < mindC.length; i++){
            mindC[i] = mind[i];
        }
        MindEngine mindE = new MindEngine();
        mindE.mind = mindC;
        mindE.bot = bot;
        if(f != 0){
            float rndF = rnd.nextFloat();
            if(rndF < f){
                int p = rnd.nextInt(MAX);
                int i = rnd.nextInt(MAX);
                mindC[i] = p;
            }
        }
        return mindE;
    }

    public int getUTKW(int u){
        if(u >= MAX)
            return u - MAX;
        if(u < 0)
            return MAX + u;
        return utk;
    }
}

package lesson_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Race {

    private ArrayList<Stage> stages;
    private CyclicBarrier barrier;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(int competitorsAmount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.barrier = new CyclicBarrier(competitorsAmount + 1);
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }
}
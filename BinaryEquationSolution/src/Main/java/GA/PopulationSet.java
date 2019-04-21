package GA;

import UI.BGCanvas;

import java.text.DecimalFormat;
import java.util.Observable;

public class PopulationSet extends Observable implements Runnable{

    private boolean done = false; // Set true to exit (i.e. stop) the simulation
    private boolean paused = false;
    private boolean running = false;
    public static int stopPec = 1;
    private int stopCount = 0;
//    private int MAX_FRAME_SIZE = 50; // How big is the simulation frame
    public static int MAX_GENERATION = 10000; // How many generations will we calculate before we're through?
    private int genCount = 0; // the count of the most recent generation
//    int ruleNum; //select a rule

    public Population population = null;

    public PopulationSet() {
//        int[] array1 = {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};
//        Individual.range = array1;
//        Individual.calculateCutIndex();
        population = new Population();
        population.generateIndividuals();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        PopulationSet ffSim = new PopulationSet();
//        ffSim.runSim(); // Perform a test run of the simulation

    }

    public void showSim() {

        DecimalFormat df = new DecimalFormat("0.0000");
        GA ga = new GA();
        population.sort();
        double preAvr = population.getAverage();
//        if (true) {
//            System.out.println(genCount + "\t generation: Average: " + df.format(population.getAverage()) + "\t . Best: " + df.format(population.getBest())
//                    + "\t individual: " + population.getIndividuals()[0]);
//        }
        Population nextPopulation = ga.evolve(population);
        double curAvr = nextPopulation.getAverage();

        if (Math.abs(curAvr - preAvr) < stopPec * 1.0 / 100) {
            stopCount++;
            if (stopCount >= 500) {
                setDone(true);
            }
        } else {
            stopCount = 0;
        }
        setChanged();
        notifyObservers(population);
        genCount++;

        population = nextPopulation;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        running = true;
        System.out.println("run" );

        while(!done) {
            if (!paused) {
                showSim();
                delay(10L);
            } else {
                delay(10L);
            }
            if (genCount > MAX_GENERATION) done = true;
        }
        System.out.println("framefluidset while is done" );
        running = false;
        done = false;
    }

    private void delay(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void cancel() {

    }

    public int getGenCount() {
        return genCount;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isDone() {
        return done;
    }

    public void setRunning(boolean bl) {
        running = bl;
        //run();
    }

    public void setPaused(boolean bl) {
        paused = bl;

    }

    public void setDone(boolean bl) {
        done = bl;

    }
}

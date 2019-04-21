package GA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Population {

    private Individual[] individuals;
    private int size = 1000;
    private int genCount;

    public Population() {
        individuals = new Individual[size];
    }

    public Population(Individual[] individuals) {
        this.individuals = individuals;
    }

    public double getAverage() {
        double rst = 0;
        for (int i = 0; i < size; i++) {
            rst += individuals[i].getFitness();
        }
        return rst / size;
    }

    public double getBest() {
        return individuals[0].getFitness();
    }

    public int size() {
        return size;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    //-----helper functions

    /**
     * Generate the first generation of Individuals
     *
     */
    public void generateIndividuals() {
        generateIndividuals(size);
    }

    public void sort() {
        Arrays.sort(individuals, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                double rst =  (o2.getFitness() - o1.getFitness());
                if (rst > 0.000001) return 1;
                else if (rst < -0.000001) return -1;
                else return 0;
            }
        });
    }
    public void generateIndividuals(int size) {
        individuals = new Individual[size];
        for (int i = 0; i < size; i++) {
            individuals[i] = getRandomIndividual();
        }
        setGenCount(0);
    }
//---------
    private static class comparator implements Comparator<Individual> {

        @Override
        public int compare(Individual o1, Individual o2) {
            return (int) (o2.getFitness() - o1.getFitness());
        }
    }

    /**
     * Generate a random Individual object
     * @return The reference of Individual object with a random x,and y
     */
    public Individual getRandomIndividual() {
        Random random = new Random();
        int[][] rst = new int[Individual.range.length][31];
        for (int i = 0; i < Individual.range.length; i++) {
            int[] x = new int[31];
            int NumOfIndex = random.nextInt(x.length - 10);
            for (int j = 0; j < NumOfIndex; j++) {
                int nextIndex = Individual.cutIndex[i] + random.nextInt(x.length - Individual.cutIndex[i]);

                x[nextIndex] = 1;
            }
            if (!Individual.isValid(i, x)) {
                i--;
                continue;
            }
            rst[i] = x;
        }
        Individual individual = new Individual(rst);
        return individual;
    }

    public int getGenCount() {
        return genCount;
    }

    public void setGenCount(int input) {
        this.genCount = input;
    }
}

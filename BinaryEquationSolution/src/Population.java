import java.util.Arrays;
import java.util.Comparator;

public class Population {

    private Individual[] individuals;
    private int size = 1000;

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
            Individual individual = new Individual();
            individuals[i] = individual.getRandomIndividual();
        }
    }
//---------
    private static class comparator implements Comparator<Individual> {

        @Override
        public int compare(Individual o1, Individual o2) {
            return (int) (o2.getFitness() - o1.getFitness());
        }
    }
}

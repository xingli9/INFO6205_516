package GA;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GA {
    private final double CROSS_RATE = 0.5;
    private final double MUTATE_RATE = 0.002;
    private final double MUTATE_RANGE = 0.1;
    private final double SELECT_RATE = 0.05;

    Random random = new Random();

    public Population evolve(Population population) {
        Individual[] individuals = population.getIndividuals();
        reorder(individuals);
        int length = individuals.length;
        for (int i = length / 2; i < length; i += 2) {
            int i1Index = random.nextInt(length / 2);
            int i2Index = random.nextInt(length / 2);
            Individual[] newIndividuals = crossOver(individuals[i1Index], individuals[i2Index]);
            individuals[i] = newIndividuals[0];
            individuals[i + 1] = newIndividuals[1];
            if (i > length / 2 + 2&& Math.random() < MUTATE_RATE * 2) {
                int k = 0;
                if (Math.random() < 0.5) {
                    k = 1;
                }
                k += i - length / 2;
                individuals[k] = mutate(individuals[k].getXY());
            }
        }
        Population newPopulation = new Population(individuals);
        newPopulation.setGenCount(population.getGenCount() + 1);
        return newPopulation;
    }
    //---helper functions
    private void reorder(Individual[] individuals) {
        random = new Random();
        int length = individuals.length;
        for (int i = 0; i < length * SELECT_RATE; i++) {
            int frontIndex = random.nextInt(length / 2);
            if (frontIndex < 2 ) {
                i--;
                continue;
            }
            int backIndex = length / 2 + random.nextInt(length / 2);
            swap(individuals, frontIndex, backIndex);
        }
    }


    private void swap(Individual[] individuals, int left, int right) {
        Individual temp = individuals[left];
        individuals[left] = individuals[right];
        individuals[right] = temp;
    }

    public Individual[] crossOver(Individual i1, Individual i2) {
        Individual[] rst = new Individual[2];
        int[][] i1XY = i1.getXY();
        int[][] i2XY = i2.getXY();
        for (int j = 0; j < i1XY.length; j++) {
            int[] i1x = i1XY[j].clone();
            int[] i2x = i2XY[j].clone();
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < (int) (i1XY.length * CROSS_RATE); i++) {
                int x = random.nextInt(i1XY.length);
                if (set.contains(x)) {
                    i--;
                    continue;
                }
                int k = i1XY[j][x];
                i1XY[j][x] = i2XY[j][x];
                i2XY[j][x] = k;
                set.add(x);
            }
            if (!Individual.isValid(j, i1XY[j]) || !Individual.isValid(j, i2XY[j])) {
                i1XY[j] = i1x.clone();
                i2XY[j] = i2x.clone();
                j--;
                continue;
            }
        }

        rst[0] = new Individual(i1XY);
        rst[1] = new Individual(i2XY);
        return rst;
    }


    public Individual mutate(int[][] input) {
        for (int j = 0; j < input.length; j++) {
            int[] temp = input[j];
            for (int i = Individual.cutIndex[j]; i < input[0].length; i++) {
                if (Math.random() < MUTATE_RANGE) {
                    input[j][i] = Math.abs(input[j][i] - 1);
                }
            }
            if (!Individual.isValid(j, input[j])) {
                input[j] = temp.clone();
                j--;
                continue;
            }
        }
        return new Individual(input);
    }
}

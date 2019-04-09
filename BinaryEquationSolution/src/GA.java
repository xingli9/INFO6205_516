import java.util.Random;

public class GA {
    private final double CROSS_RATE = 0.5;
    private final double MUTATE_RATE = 0.002;
    private final double MUTATE_RANGE = 0.1;
    private final double SELECT_RATE = 0.01;

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
            if (Math.random() < MUTATE_RATE * 2) {
                int k = 0;
                if (Math.random() < 0.5) {
                    k = 1;
                }
                k += i - length / 2;
                individuals[k] = mutate(individuals[k].getXY());
            }
        }
        return new Population(individuals);
    }


    //---helper functions
    private void reorder(Individual[] individuals) {
        random = new Random();
        int length = individuals.length;
        for (int i = 0; i < length * SELECT_RATE; i++) {
            int frontIndex = random.nextInt(length / 2);
            int backIndex = length / 2 + random.nextInt(length / 2);
            swap(individuals, frontIndex, backIndex);
        }
    }


    private void swap(Individual[] individuals, int left, int right) {
        Individual temp = individuals[left];
        individuals[left] = individuals[right];
        individuals[right] = temp;
    }

//    private Individual[] crossOver(Individual i1, Individual i2) {
//        Individual[] rst = new Individual[2];
//        int[][] i1XY = i1.getXY();
//        int[][] i2XY = i2.getXY();
//
//        for (int i = 0; i < i1XY[0].length; i++) {
//            if (Math.random() < CROSS_RATE) {
//                int k = i1XY[0][i];
//                i1XY[0][i] = i2XY[0][i];
//                i2XY[0][i] = k;
//            }
//        }
//        for (int i = 0; i < i1XY[1].length; i++) {
//            if (Math.random() < CROSS_RATE) {
//                int k = i1XY[1][i];
//                i1XY[1][i] = i2XY[1][i];
//                i2XY[1][i] = k;
//            }
//        }
//        rst[0] = new Individual(i1XY[0], i1XY[1]);
//        rst[1] = new Individual(i2XY[0], i2XY[1]);
//        return rst;
//    }

    private Individual[] crossOver(Individual i1, Individual i2) {
        Individual[] rst = new Individual[2];
        int[][] i1XY = i1.getXY();
        int[][] i2XY = i2.getXY();

        int begin = (int)CROSS_RATE * i1XY[0].length;
        for (int i = begin; i < i1XY[0].length; i++) {

                int k = i1XY[0][i];
                i1XY[0][i] = i2XY[0][i];
                i2XY[0][i] = k;

        }
        begin = (int)CROSS_RATE * i1XY[1].length;
        for (int i = begin; i < i1XY[1].length; i++) {

                int k = i1XY[1][i];
                i1XY[1][i] = i2XY[1][i];
                i2XY[1][i] = k;

        }
        rst[0] = new Individual(i1XY[0], i1XY[1]);
        rst[1] = new Individual(i2XY[0], i2XY[1]);
        return rst;
    }

    private Individual mutate(int[][] input) {
        for (int i = 0; i < input[0].length; i++) {
            if (Math.random() < MUTATE_RANGE) {
                input[0][i] = Math.abs(input[0][i] - 1);
            }
        }
        for (int i = 0; i < input[1].length; i++) {
            if (Math.random() < MUTATE_RANGE) {
                input[1][i] = Math.abs(input[1][i] - 1);
            }
        }
        return new Individual(input[0], input[1]);
    }
}

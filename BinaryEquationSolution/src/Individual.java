import java.util.Random;

public class Individual {
    private int[] x;
    private int[] y;
    private int decimal = 5;
    private double fitness;


    public Individual() {
        this.x = new int[17];
        this.y = new int[17];
    }

    public Individual(int[] x, int[] y) {
        this.x = x;
        this.y = y;
        calculateFitness();
    }

    /**
     * get the decimal value of x and y in this individual
     * @return
     */
    public double[] getValue() {
        double xValue = twoToTen(this.x);
        double yValue = twoToTen(this.y);
        double[] rst = {xValue, yValue};
        return rst;
    }

    /**
     * Generate a random Individual object
     * @return The reference of Individual object with a random x,and y
     */
    public Individual getRandomIndividual() {
        Individual individual = new Individual();
        Random random = new Random();
        int NumOfIndex = random.nextInt(x.length);
        for (int i = 0; i < NumOfIndex; i++) {
            int nextIndex = random.nextInt(NumOfIndex);
            individual.x[nextIndex] = 1;
        }
        NumOfIndex = random.nextInt(y.length);
        for (int i = 0; i < NumOfIndex; i++) {
            int nextIndex = random.nextInt(NumOfIndex);
            individual.y[nextIndex] = 1;
        }
        calculateFitness();
        return individual;
    }

    public double getFitness() {
        return fitness;
    }

    public int[][] getXY() {
        int[][] rst = new int[2][];
        rst[0] = this.x;
        rst[1] = this.y;
        return rst;
    }

    //---helper functions

    /**
     *
     * @param input is a int[] that represent a binary value
     * @return return the decimal value of the input
     */
    private double twoToTen(int[] input) {
        double rst = 0;
        int l = input.length;
        for (int i = 0; i < input.length; i++) {
            rst += input[i] * Math.pow(2,l - 1) / Math.pow(10, decimal);
        }
        return rst;
    }

    private void caculateFitness() {

    }

    /**
     * Calculate the fitness value
     */
    private void calculateFitness() {
        this.fitness = Fitness.calculateFitness(this);
    }

}

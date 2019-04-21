package GA;

import java.util.Random;
import java.text.DecimalFormat;
public class Individual {
    private final int[][] variables;
    private final double fitness;
    public static int decimal = 4;
    public static int[] range;
    public static int[] cutIndex;



    public Individual(int[][] input) {
        this.variables = input;
        this.fitness = calculateFitness();
    }

    /**
     * get the decimal value of x and y in this individual
     * @return
     */
    public double[] getValue() {
        double[] rst = new double[variables.length];
        for (int i = 0; i < variables.length; i++) {
            rst[i] = twoToTen(variables[i]);
        }
        return rst;
    }



    public double getFitness() {
        return fitness;
    }

    public int[][] getXY() {
        int[][] rst = new int[variables.length][];
        for (int i = 0; i < rst.length; i++) {
            rst[i] = variables[i].clone();
        }
        return rst;
    }

    //---helper functions

    /**
     *
     * @param input is a int[] that represent a binary value
     * @return return the decimal value of the input
     */
    private static double twoToTen(int[] input) {
        String Sinput = "";
        for (int i = 0; i < input.length; i++) {
            Sinput += input[i];
        }
        int rstInt = Integer.parseInt(Sinput, 2);
        double rst = 1.0 * rstInt / Math.pow(10, decimal);
        return rst;



//        double rst = 0;
//        int l = input.length;
//        for (int i = 0; i < input.length; i++) {
//            rst += input[i] * Math.pow(2,l - 1) / Math.pow(10, decimal);
//            l--;
//        }
    }


    /**
     * Calculate the fitness value
     */
    private double calculateFitness() {
        return Fitness.calculateFitness(this.getValue());
    }

    public static void main(String[] args) {

//        int[] nub1 = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
//        double rst =  individual.twoToTen(nub1);
//        System.out.println(rst);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0000");
        String rst = "fitness: " + df.format(fitness);
        for (int i = 0; i < variables.length; i++) {
            rst += "\tx" + i + " : " + df.format(twoToTen(variables[i]));
        }
        return rst;
    }

    public static boolean isValid(int index, int[] input) {
        return range[index] >= twoToTen(input) ? true : false;
    }

    public static void calculateCutIndex() {
        cutIndex = new int[range.length];
        for (int i = 0; i < range.length; i++) {
            int k = 31;
            while (true) {
                double value = Math.pow(2, k) / Math.pow(10, decimal);
                if (value - range[i] < 0.0000001) break;
                k--;
             }
//             cutIndex[i] = 31 - k;
            cutIndex[i] = 0;
        }
    }

}

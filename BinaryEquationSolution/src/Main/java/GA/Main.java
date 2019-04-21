package GA;
import java.text.DecimalFormat;

public class Main {


    public static void main(String[] args){
        DecimalFormat df = new DecimalFormat("0.0000");
        int[] array1 = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000};
        Individual.range = array1;
        Individual.calculateCutIndex();
        Population population = new Population();
        population.generateIndividuals();
        int maxGeneration = 10000;
        GA ga = new GA();
        for (int i = 0; i < maxGeneration; i++) {
            //添加终止条件
            population.sort();
            if (i % 1 == 0) {
                System.out.println(i + "\t generation: Average: " + df.format(population.getAverage()) + "\t . Best: " + df.format(population.getBest())
                + "\t individual: " + population.getIndividuals()[0]);
            }
            population = ga.evolve(population);
        }

//        int[][] x = {{1}};
//        int[][] y = new int[1][];
//        y[0] = x[0].clone();
//        y[0][0] = 0;
//        System.out.println(x[0][0] + " : " + y[0][0]);

    }

}

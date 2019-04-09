public class Fitness {

    public static double calculateFitness(Individual individual) {
        double[] value = individual.getValue();
        double x = value[0];
        double y = value[1];
//        double rst = x * Math.sin(y) + y * Math.cos(x);
        double rst = 10 - (x - 5) * (x - 5) - (y - 5) * (y - 5);
        return rst;
    }
}

package GA;


public class Fitness {

    public static double calculateFitness(double[] x) {

//        double rst = x[0] * Math.sin(x[1]) + x[1] * Math.cos(x[0]);
        double rst = 10 - Math.pow((x[0] - 5), 2 )- Math.pow((x[1] - 5), 2);

//        double rst = Math.cos(x[5]) * Math.sqrt(x[6]) - 100*Math.sin(Math.pow(x[0],2) + Math.pow(x[1], 2)  - Math.cos(x[3])) * Math.sin(x[4]) + (x[7] * Math.sin(x[8]) - Math.abs(x[9]));
//        double rst = Math.cos(x[5]) * Math.sqrt(x[6]) - (x[0] * Math.sin(x[1]) + x[2] * Math.cos(x[3])) * Math.sin(x[4]) + (x[7] * Math.sin(x[8]) - Math.abs(x[9]));
//        double rst = 100*Math.sin(x[0] + x[1] +x[2] +x[3] +x[4] +x[5] +x[6] +x[7] +x[8] +x[9]);
        return rst;
    }


}

public class Main {


    public static void main(String[] args){

        Population population = new Population();
        population.generateIndividuals();
        int maxGeneration = 10000;
        GA ga = new GA();
        for (int i = 0; i < maxGeneration; i++) {
            //添加终止条件
            if (i % 10 == 0) {
                System.out.println(i + "\t generation: Average: " + population.getAverage() + "\t . Best: " + population.getBest()
                + "\t xvalue: " + population.getIndividuals()[1].getValue()[0]);
            }
            population.sort();
            population = ga.evolve(population);
        }

    }

}

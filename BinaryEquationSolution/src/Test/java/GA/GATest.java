package GA;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GATest {
    Population population = new Population();
    GA ga = new GA();
    @Before
    public void setUp() throws Exception {
        int[] array1 = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000};
        Individual.range = array1;
        Individual.calculateCutIndex();
        population.generateIndividuals(1000);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void evolve() {
        double preAva = population.getAverage();
        Population newPopulation = ga.evolve(population);
        double curAva = newPopulation.getAverage();
        assertNotEquals(curAva,preAva);

    }

    @Test
    public void crossOver() {
        int[] x1 = {0,0,0,1,0,0,1,1,1,0,0,0,1,0,0,0,0}; // 1
        int[] x2 = {0,0,1,0,0,1,1,1,0,0,0,1,0,0,0,0,0}; // 2
        int[] x5 = {0,1,1,0,0,0,0,1,1,0,1,0,1,0,0,0,0}; // 5
        int[] x6 = {0,1,1,1,0,1,0,1,0,0,1,1,0,0,0,0,0}; // 6
        int[][] matrix = {x1,x2};
        int[][] matrix1 = {x5,x6};
        Individual individual1 = new Individual(matrix);
        Individual individual2 = new Individual(matrix1);
        Individual[] newIndividual = ga.crossOver(individual1,individual2);
        assertNotEquals(individual1.getFitness(),newIndividual[0]);
        assertNotEquals(individual1.getFitness(),newIndividual[1]);
        assertNotEquals(individual2.getFitness(),newIndividual[0]);
        assertNotEquals(individual2.getFitness(),newIndividual[1]);

    }

    @Test
    public void mutate() {
        int[] x1 = {0,0,0,1,0,0,1,1,1,0,0,0,1,0,0,0,0}; // 1
        int[] x2 = {0,0,1,0,0,1,1,1,0,0,0,1,0,0,0,0,0}; // 2
        int[][] matrix = {x1,x2};
        Individual individual1 = new Individual(matrix);
        Individual newIndividual = ga.mutate(matrix);
        assertNotEquals(individual1.getFitness(),newIndividual.getFitness());

    }

}
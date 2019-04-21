package GA;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FitnessTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calculateFitness() {
        int[] x = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
        int[] y = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
        int[][] matrix = {x,y};
        Individual individual = new Individual(matrix);
        assertEquals(5.17, individual.getFitness(), 0.1);
        int[] x1 = {0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] y1 = {0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        matrix[0] = x1;
        matrix[1] = y1;
        individual = new Individual(matrix);
        assertEquals(9.99, individual.getFitness(), 0.1);

    }
}

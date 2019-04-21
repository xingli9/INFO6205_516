package GA;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndividualTest {
    Individual[] individuals;

    @Before
    public void setUp() throws Exception {
        individuals = generateIndeviduals();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getValue() {
        assertEquals(1, individuals[0].getValue()[0], 0.01);
        assertEquals(1, individuals[0].getValue()[1], 0.01);

        assertEquals(2, individuals[1].getValue()[0], 0.01);
        assertEquals(2, individuals[1].getValue()[1], 0.01);

        assertEquals(3, individuals[2].getValue()[0], 0.01);
        assertEquals(3, individuals[2].getValue()[1], 0.01);

        assertEquals(4, individuals[3].getValue()[0], 0.01);
        assertEquals(4, individuals[3].getValue()[1], 0.01);

        assertEquals(5, individuals[4].getValue()[0], 0.01);
        assertEquals(5, individuals[4].getValue()[1], 0.01);

        assertEquals(6, individuals[5].getValue()[0], 0.01);
        assertEquals(6, individuals[5].getValue()[1], 0.01);
    }

    @Test
    public void getFitness() {
        assertEquals(-22, individuals[0].getFitness(), 0.01);
        assertEquals(-8, individuals[1].getFitness(), 0.01);
        assertEquals(2, individuals[2].getFitness(), 0.01);
        assertEquals(8, individuals[3].getFitness(), 0.01);
        assertEquals(10, individuals[4].getFitness(), 0.01);
        assertEquals(8, individuals[5].getFitness(), 0.01);
    }

    @Test
    public void main() {


    }

    private Individual[] generateIndeviduals() {
        int[] x1 = {0,0,0,1,0,0,1,1,1,0,0,0,1,0,0,0,0}; // 1
        int[] x2 = {0,0,1,0,0,1,1,1,0,0,0,1,0,0,0,0,0}; // 2
        int[] x3 = {0,0,1,1,1,0,1,0,1,0,0,1,1,0,0,0,0}; // 3
        int[] x4 = {0,1,0,0,1,1,1,0,0,0,1,0,0,0,0,0,0}; // 4
        int[] x5 = {0,1,1,0,0,0,0,1,1,0,1,0,1,0,0,0,0}; // 5
        int[] x6 = {0,1,1,1,0,1,0,1,0,0,1,1,0,0,0,0,0}; // 6
        int[][] points = {x1, x2, x3, x4, x5, x6};
        Individual[] individuals = new Individual[6];
        for (int i = 0; i < points.length; i++) {
            int[][] matrix = {points[i], points[i]};
            individuals[i] = new Individual(matrix);
        }
        return individuals;
    }
}

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PercolationTest {

    @Test
    public void PercolationNEqualOne()
    {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.percolates());

        percolation.open(1, 1);

        assertTrue(percolation.percolates());
    }

    @Test
    public void PercolationNEqualTwo()
    {
        Percolation percolation = new Percolation(2);
        assertFalse(percolation.percolates());

        percolation.open(1, 2);
        assertFalse(percolation.percolates());

        percolation.open(2, 2);

        assertTrue(percolation.percolates());
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
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

    @Test
    public void NotConnected()
    {
        Percolation percolation = new Percolation(3);
        percolation.open(1,3);
        percolation.open(2,3);
        percolation.open(3,1);
        percolation.open(3,3);

        assertTrue(percolation.isFull(3,3));
        assertTrue(percolation.isFull(3,1));
    }

    @Test
    public void myinput()
    {
        In in = new In("myinput.txt");      // input file
        int n = in.readInt();         // n-by-n percolation system

        Percolation perc = new Percolation(n);

        // turn on animation mode
        StdDraw.enableDoubleBuffering();
        PercolationVisualizer.draw(perc, n);
        StdDraw.show();
        StdDraw.pause(100);

        // repeatedly read in sites to open and draw resulting system
        int counter = 0;
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
            PercolationVisualizer.draw(perc, n);
            StdDraw.show();
            StdDraw.pause(100);
            counter++;
        }

        StdDraw.show();

        //assertFalse(perc.isFull(3, 1));
        StdDraw.pause(10000000);
    }

    @Test
    public void input20()
    {
        In in = new In("input20.txt");      // input file
        int n = in.readInt();         // n-by-n percolation system

        Percolation perc = new Percolation(n);

        // turn on animation mode
        StdDraw.enableDoubleBuffering();
        PercolationVisualizer.draw(perc, n);
        StdDraw.show();
        StdDraw.pause(100);

        // repeatedly read in sites to open and draw resulting system
        int counter = 0;
        while (!in.isEmpty() && counter < 231) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
            PercolationVisualizer.draw(perc, n);
            StdDraw.show();
            StdDraw.pause(100);
            counter++;
        }

        assertEquals(231, counter);
        assertFalse(perc.isFull(18, 1));
    }
}

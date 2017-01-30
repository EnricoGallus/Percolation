import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by enrico on 28.01.17.
 * Corner cases:
 * By convention, the row and column indices are integers
 * between 1 and n, where (1, 1) is the upper-left site:
 * Throw a java.lang.IndexOutOfBoundsException if any argument to
 * open(), isOpen(), or isFull() is outside its prescribed range.
 * The constructor should throw a java.lang.IllegalArgumentException if n ≤ 0.
 *
 * Performance requirements:
 * The constructor should take time proportional to n2;
 * all methods should take constant time plus a constant number of calls to the
 * union–find methods union(), find(), connected(), and count()
 */
public class Percolation {
    private int size;
    private boolean[][] opened;
    private int openCount = 0;
    private int top = 0;
    private int bottom;
    private WeightedQuickUnionUF unionUF;


    /**
     * constructor to initialize the
     * @param n
     */
    public Percolation(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("n needs to be greater or equal to 1");
        }

        size = n;
        opened = new boolean[n][n];
        bottom = size * size + 1;
        unionUF = new WeightedQuickUnionUF(size * size + 2);
    }

    /**
     * open site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col) {

        throwExIfOutOfBounds(row, col);
        if (isOpen(row, col)) {
            return;
        }

        opened[row - 1][col - 1] = true;
        openCount++;
        int indexCurrentElement = getQFOneDimensionIndex(row, col);

        if (row == 1) { // connect to top if first row
            unionUF.union(indexCurrentElement, top);
        }
        else if (row == size) { // connect to bottom if last row
            unionUF.union(indexCurrentElement, bottom);
        }

        // connect to adjacent
        if (col > 1 && isOpen(row, col - 1)) { // left
            unionUF.union(indexCurrentElement, getQFOneDimensionIndex(row, col - 1));
        }
        if (col < size && isOpen(row, col + 1)) { // right
            unionUF.union(indexCurrentElement, getQFOneDimensionIndex(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            unionUF.union(indexCurrentElement, getQFOneDimensionIndex(row - 1, col));
        }
        if (row < size && isOpen(row + 1, col)) {
            unionUF.union(indexCurrentElement, getQFOneDimensionIndex(row + 1, col));
        }
    }

    /**
     * is site (row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {

        throwExIfOutOfBounds(row, col);

        return opened[row - 1][col - 1];

    }

    /**
     * is site (row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {

        throwExIfOutOfBounds(row, col);

        return unionUF.connected(top, getQFOneDimensionIndex(row, col));
    }

    /**
     * number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return openCount;
    }

    /**
     * does the system percolate?
     * @return
     */
    public boolean percolates() {

        return unionUF.connected(top, bottom);
    }

    /**
     * test client (optional)
     * @param args
     */
    public static void main(String[] args) {
    }

    private void throwExIfOutOfBounds(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IndexOutOfBoundsException("row (" + row + ") and col ( " + col + ") needs to be between 1 and " + size);
        }
    }

    private int getQFOneDimensionIndex(int row, int col) {
        return size * (row - 1) + col;
    }
}

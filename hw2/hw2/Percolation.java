/**********************************************************************************************
 *   refers to : http://www.cnblogs.com/anne-vista/p/4841732.html
 *   Date: 05/31/2018
 *   Union-Find Assignment
 *   Percolation Reference Version
 *   Edited:
 *   1.refactoring open() to delete redundant code
 *     thus decrease the total call times of find();
 *   2.add Opened sites count instance variable and method
 *   3.more terse and tidy open() method THAN Percolation Version 2.*
 *
 *   Note: this version DO NOT use Top&Bottom Virtual Site
 *   The FASTEST runtime version
 *   API using by PercolationVisualizer and PercolationStats
 *********************************************************************************************/
package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationRefer {
    private boolean[] open; //blocked: false, open: true
    private boolean[] connectTop;
    private boolean[] connectBottom;
    private int N; //create N-by-N grid
    private WeightedQuickUnionUF uf;
    private boolean percolateFlag;
    private int nOS;                        //number of Open Sites
    
    public PercolationRefer(int N)  {             // create N-by-N grid, with all sites blocked
        if (N <= 0) {
            throw new IllegalArgumentException("N must be bigger than 0");
        }
        this.N = N;
        uf = new WeightedQuickUnionUF(N*N);
        open = new boolean[N*N];
        connectTop = new boolean[N*N];
        connectBottom = new boolean[N*N];
        
        for (int i = 0; i < N*N; i++) {
            open[i] = false;
            connectTop[i] = false;
            connectBottom[i] = false;
        }
        percolateFlag = false;
    }
    
    public void open(int i, int j)  {        // open site (row i, column j) if it is not open already
        validateIJ(i, j);
        int index = xyTo1D(i, j);
        open[index] = true;  //open
        nOS++;
        boolean top = false;
        boolean bottom = false;
        
        if (i < N && open[index+N]) {
            if (connectTop[uf.find(index+N)]) {
                top = true;
            }
            if (connectBottom[uf.find(index+N)]) {
                bottom = true;
            }
            uf.union(index, index+N);
        }
        if (i > 1 && open[index-N]) {
            if (connectTop[uf.find(index-N)]) {
                top = true;
            }
            if (connectBottom[uf.find(index-N)]) {
                bottom = true;
            }
            uf.union(index, index-N);
        }
        if (j < N && open[index+1]) {
            if (connectTop[uf.find(index+1)]) {
                top = true;
            }
            if (connectBottom[uf.find(index+1)]) {
                bottom = true;
            }
            uf.union(index, index+1);
        }
        if (j > 1 && open[index-1]) {
            if (connectTop[uf.find(index-1)]) {
                top = true;
            }
            if (connectBottom[uf.find(index-1)]) {
                bottom = true;
            }
            uf.union(index, index-1);
        }
        if(i == 1) {
            top = true;
        }
        if(i == N){
            bottom = true;
        }
        connectTop[uf.find(index)] = top;
        connectBottom[uf.find(index)] = bottom;
        if( top &&  bottom) {
            percolateFlag = true;
        }
    }
    
    private int xyTo1D(int i, int j) {
        validateIJ(i, j);
        return j + (i-1) * N -1;
    }
    
    private void validateIJ(int i, int j) {
        if (!(i >= 1 && i <= N && j >= 1 && j <= N)) {
            throw new IndexOutOfBoundsException("Index is not betwwen 1 and N");
        }
    }
    
    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        validateIJ(i, j);
        return open[xyTo1D(i, j)];
    }
    
    /*A full site is an open site that can be connected to an open site in the top row
     * via a chain of neighboring (left, right, up, down) open sites.
     */
    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        validateIJ(i, j);
        return connectTop[uf.find(xyTo1D(i, j))];
    }
    
    /* Introduce 2 virtual sites (and connections to top and bottom).
     * Percolates iff virtual top site is connected to virtual bottom site.
     */
    public boolean percolates()  {           // does the system percolate?
        return percolateFlag;
    }
    public int numberOfOpenSites() {      // number of open sites
        return nOS;
    }
    public static void main(String[] args) { // test client (optional)
    }
}


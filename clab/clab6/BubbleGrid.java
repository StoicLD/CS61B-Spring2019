import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        // 不能对grid数组造成破坏
        if(grid.length == 0 || grid[0].length == 0 || grid[0].length != grid.length)
            return null;
        WeightedQuickUnionUF union = new WeightedQuickUnionUF(grid.length * grid[0].length);

        return null;
    }
}

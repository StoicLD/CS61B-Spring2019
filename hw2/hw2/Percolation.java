package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

//Full site is also an Open site
public class Percolation {
    private WeightedQuickUnionUF siteUnion;
    //只有block和open两种状态，full只要看是否与virtual top point连接就可以了
    private Status[] siteStatus;
    private int gridSize;
    private int openSiteNumber;
    private final int topVirtualIndex;       //顶部虚拟节点的index
    private final int bottomVirtualIndex;    //底部虚拟节点的index
    private boolean isPercolate;

    // create N-by-N grid, with all sites initially blocked
    // O(N2)
    public Percolation(int N)
    {
        if(N <= 0)
        {
            throw new IllegalArgumentException();
        }
        siteUnion = new WeightedQuickUnionUF(N * N + 2);
        siteStatus = new Status[N * N + 2];
        gridSize = N;

        Arrays.fill(siteStatus, Status.blocked);    // O(N2)
        //index 为 N的节点为上节点（virtual），连接第一排
        siteStatus[N * N] = Status.open;
        topVirtualIndex = N * N;
        bottomVirtualIndex = N * N + 1;
        openSiteNumber = 0;
        isPercolate = false;
    }

    public int xyToIndex(int row, int col)
    {
        return row * gridSize + col;
    }

    public boolean checkIndex(int index)
    {
        return index >= 0 && index <= gridSize * gridSize - 1;
    }

    //四周如果有open的就Union起来
    public void changeStatus(int originIndex, int otherIndex)
    {
        if(checkIndex(otherIndex))
        {
            if (isOpen(otherIndex)) {
                // 只要union了就完事了
                siteUnion.union(otherIndex, originIndex);
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if(row < 0 || col < 0 || row >= gridSize || col >= gridSize ) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyToIndex(row, col);
        if(isOpen(row, col)) {
            return;
        }

        siteStatus[index] = Status.open;
        //最上面一排
        if(row == 0) {
            siteUnion.union(index, topVirtualIndex);
        }

        int topIndex = xyToIndex(row - 1, col);
        int bottomIndex = xyToIndex(row + 1, col);
        int rightIndex = xyToIndex(row, col + 1);
        int leftIndex = xyToIndex(row, col - 1);

        changeStatus(index, topIndex);
        changeStatus(index, bottomIndex);
        changeStatus(index, rightIndex);
        changeStatus(index, leftIndex);
        //最下面一排，问题在于如何防止倒灌？
        if(row == gridSize - 1) {
            if(siteUnion.connected(index, topVirtualIndex)) {
                //表明上下连通了
                isPercolate = true;
            }
            siteUnion.union(index, bottomVirtualIndex);
        }

        openSiteNumber++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        int index = xyToIndex(row, col);
        return isOpen(index);
    }

    public boolean isOpen(int index)
    {
        if(checkIndex(index)) {
            return siteStatus[index] == Status.open;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        int index = xyToIndex(row, col);
        return isFull(index);
    }

    //index版本的isFull
    public boolean isFull(int index)
    {
        if(checkIndex(index)) {
            return (siteStatus[index] == Status.open) && siteUnion.connected(index, topVirtualIndex);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // number of open sites
    public int numberOfOpenSites()
    {
        return openSiteNumber;
    }
    // does the system percolate?
    public boolean percolates()
    {
        return isPercolate;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args)
    {
    }

    enum Status
    {
        blocked,
        open
    }
}

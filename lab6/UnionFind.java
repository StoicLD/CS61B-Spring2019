import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

public class UnionFind {

    // TODO - Add instance variables?
    private int[] parent;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        parent = new int[n];
        Arrays.fill(parent, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if(vertex >= parent.length || vertex < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        int root = find(v1);
        if(parent[root] > 0)
            throw new NotImplementedException();
        return -parent[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int root1 = find(v1);
        int root2 = find(v2);
        if(root1 == root2) {
            return;
        }
        int size1 = sizeOf(root1);
        int size2 = sizeOf(root2);
        if(size1 > size2) {
            //先把size增大
            parent[root1] = parent[root1] + parent[root2];
            parent[root2] = root1;
        }
        else{
            parent[root2] = parent[root2] + parent[root1];
            parent[root1] = root2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int root = vertex;
        while(parent[root] >= 0)
        {
            root = parent[root];
        }
        return root;
    }

    public int[] getParent() {
        int[] result = Arrays.copyOf(parent, parent.length);
        return result;
    }
}

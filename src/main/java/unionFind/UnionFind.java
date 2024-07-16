package unionFind;

public class UnionFind {
    private int[] parents;
    private int count;

    public UnionFind(int n) {
        count = n;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public int find(int x) {
        return parents[x] == x ? x : (parents[x] = find(parents[x]));
    }

    public boolean union(int x, int y) {
        int px = find(x), py = find(y);

        if (px == py) {
            return false;
        }

        parents[px] = py;
        count--;

        return true;
    }

    public boolean isConnected() {
        return count == 1;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}

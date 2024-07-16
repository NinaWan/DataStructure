package unionFind;

public class UnionFindWithRank {
    private int[] parents;
    private int[] ranks;
    private int count;

    public UnionFindWithRank(int n) {
        count = n;
        parents = new int[n];
        ranks = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public int find(int x) {
        while (parents[x] != x) {
            parents[x] = parents[parents[x]];
            x = parents[x];
        }
        return x;
    }

    public boolean union(int x, int y) {
        int px = find(x), py = find(y);

        if (px == py) {
            return false;
        }

        if (ranks[px] < ranks[py]) {
            parents[px] = py;
        } else if (ranks[px] > ranks[py]) {
            parents[py] = px;
        } else {
            parents[px] = py;
            ranks[py]++;
        }

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

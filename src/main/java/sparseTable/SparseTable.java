package sparseTable;

public abstract class SparseTable {
    final int[][] lookup;
    final int n;
    final int m;

    public SparseTable(int n) {
        this.n = n;
        this.m = (int) (Math.log(n) / Math.log(2)) + 1;
        lookup = new int[n][m];
    }

    public SparseTable(int[] nums) {
        this.n = nums.length;
        m = (int) (Math.log(n) / Math.log(2)) + 1;
        lookup = new int[n][m];
        build(nums);
    }

    private void build(int[] nums) {
        // initialization
        for (int i = 0; i < n; i++) {
            lookup[i][0] = nums[i];
        }

        // bottom-up
        for (int j = 1; j < m; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                lookup[i][j] = f(lookup[i][j - 1], lookup[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    abstract int f(int a, int b);

    public abstract int query(int l, int r);
}

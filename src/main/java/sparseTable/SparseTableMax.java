package sparseTable;

public class SparseTableMax extends SparseTable {

    public SparseTableMax(int n) {
        super(n);
    }

    public SparseTableMax(int[] nums) {
        super(nums);
    }

    public int query(int l, int r) {
        int j = (int) (Math.log(r - l + 1) / Math.log(2));
        return f(lookup[l][j], lookup[r - (1 << j) + 1][j]);
    }

    int f(int a, int b) {
        return Math.max(a, b);
    }
}

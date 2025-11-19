package sparseTable;

public class SparseTableMin extends SparseTable {

    public SparseTableMin(int n) {
        super(n);
    }

    public SparseTableMin(int[] nums) {
        super(nums);
    }

    public int query(int l, int r) {
        int j = (int) (Math.log(r - l + 1) / Math.log(2));
        return f(lookup[l][j], lookup[r - (1 << j) + 1][j]);
    }

    int f(int a, int b) {
        return Math.min(a, b);
    }
}

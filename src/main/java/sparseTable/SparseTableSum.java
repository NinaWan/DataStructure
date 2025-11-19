package sparseTable;

public class SparseTableSum extends SparseTable {

    public SparseTableSum(int n) {
        super(n);
    }

    public SparseTableSum(int[] nums) {
        super(nums);
    }

    public int query(int l, int r) {
        int sum = 0;
        for (int j = (int) (Math.log(r - l + 1) / Math.log(2)); j >= 0; j--) {
            if ((1 << j) <= r - l + 1) {
                sum += lookup[l][j];
                l += 1 << j;
            }
        }
        return sum;
    }

    int f(int a, int b) {
        return a + b;
    }
}

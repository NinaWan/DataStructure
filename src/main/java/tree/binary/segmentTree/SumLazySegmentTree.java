package tree.binary.segmentTree;

public class SumLazySegmentTree {
    private int[] nodes;
    private int[] lazy;
    private int n;

    public SumLazySegmentTree(int[] nums) {
        n = nums.length;
        nodes = new int[4 * n];
        lazy = new int[4 * n];
        build(nums, 0, 0, n - 1);
    }


//    public void update(int[] nums, int i, int val) {
//        if (i < 0 || i >= n) {
//            return;
//        }
//
//        update(i, val - nums[i], 0, n - 1, 0);
//        nums[i] = val;
//    }
//
//    private void update(int i, int diff, int l, int r, int j) {
//        if (i < l || i > r) {
//            return;
//        }
//
//        nodes[j] += diff;
//
//        if (l != r) {
//            int mid = l + (r - l) / 2;
//            update(i, diff, l, mid, 2 * j + 1);
//            update(i, diff, mid + 1, r, 2 * j + 2);
//        }
//    }

    // add diff to nums[l...r]
    public void updateRange(int ql, int qr, int diff) {
        updateRange(0, 0, n - 1, ql, qr, diff);
    }

    private void updateRange(int i, int l, int r, int ql, int qr, int diff) {
        if (lazy[i] != 0) { // handle the delayed updates before processing
            nodes[i] += (r - l + 1) * lazy[i];

            if (l != r) {
                lazy[2 * i + 1] += lazy[i];
                lazy[2 * i + 2] += lazy[i];
            }

            lazy[i] = 0;
        }

        if (l > qr || r < ql) { // not in update range
            return;
        }

        if (l >= ql && r <= qr) { // fully in update range
            nodes[i] += (r - l + 1) * diff;
            if (l != r) {
                lazy[2 * i + 1] += diff;
                lazy[2 * i + 2] += diff;
            }
        } else {
            int mid = l + (r - l) / 2;
            updateRange(2 * i + 1, l, mid, ql, qr, diff);
            updateRange(2 * i + 2, mid + 1, r, ql, qr, diff);
            nodes[i] = nodes[2 * i + 1] + nodes[2 * i + 2];
        }
    }

    public int sum(int ql, int qr) {
        if (ql < 0 || qr >= n || ql > qr) {
            throw new RuntimeException("Invalid query range!");
        }
        return sum(0, 0, n - 1, ql, qr);
    }

    private int sum(int i, int l, int r, int ql, int qr) {
        if (lazy[i] != 0) { // handle the delayed updates before processing
            nodes[i] += (r - l + 1) * lazy[i];

            if (l != r) {
                lazy[2 * i + 1] += lazy[i];
                lazy[2 * i + 2] += lazy[i];
            }

            lazy[i] = 0;
        }

        if (ql <= l && qr >= r) {
            return nodes[i];
        }

        if (r < ql || l > qr) {
            return 0;
        }

        int mid = l + (r - l) / 2;
        return sum(2 * i + 1, l, mid, ql, qr) + sum(2 * i + 2, mid + 1, r, ql, qr);
    }

    private void build(int[] nums, int i, int l, int r) {
        if (l == r) {
            nodes[i] = nums[l];
        } else {
            int mid = l + (r - l) / 2;
            build(nums, 2 * i + 1, l, mid);
            build(nums, 2 * i + 2, mid + 1, r);
            nodes[i] = nodes[2 * i + 1] + nodes[2 * i + 2];
        }
    }
}
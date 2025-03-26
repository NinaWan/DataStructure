package tree.binary.segmentTree;

public class MaxLazySegmentTree {
    private int[] nodes;
    private int[] lazy;
    private int n;

    public MaxLazySegmentTree(int[] nums) {
        n = nums.length;
        nodes = new int[4 * n];
        lazy = new int[4 * n];
        build(nums, 0, 0, n - 1);
    }

    // add diff to nums[l, r]
    public void updateRange(int ql, int qr, int diff) {
        updateRange(0, 0, n - 1, ql, qr, diff);
    }

    private void updateRange(int i, int l, int r, int ql, int qr, int diff) {
        if (lazy[i] != 0) { // handle the delayed updates before processing
            nodes[i] += lazy[i];
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
            nodes[i] += diff; // add diff to the original maximum value
            if (l != r) {
                lazy[2 * i + 1] += diff;
                lazy[2 * i + 2] += diff;
            }
        } else {
            int mid = l + (r - l) / 2;
            updateRange(2 * i + 1, l, mid, ql, qr, diff);
            updateRange(2 * i + 2, mid + 1, r, ql, qr, diff);
            nodes[i] = Math.max(nodes[2 * i + 1], nodes[2 * i + 2]);
        }
    }

    public int max(int ql, int qr) {
        if (ql < 0 || qr >= n || ql > qr) {
            throw new RuntimeException("Invalid query range!");
        }
        return max(0, 0, n - 1, ql, qr);
    }

    private int max(int i, int l, int r, int ql, int qr) {
        if (lazy[i] != 0) { // handle the delayed updates before processing
            nodes[i] += lazy[i];
            if (l != r) {
                lazy[2 * i + 1] += lazy[i];
                lazy[2 * i + 2] += lazy[i];
            }
            lazy[i] = 0;
        }

        if (ql <= l && r <= qr) { // fully in query range
            return nodes[i];
        }

        if (r < ql || l > qr) {
            return 0;
        }

        int mid = l + (r - l) / 2;
        return Math.max(max(2 * i + 1, l, mid, ql, qr), max(2 * i + 2, mid + 1, r, ql, qr));
    }

    private void build(int[] nums, int i, int l, int r) {
        if (l == r) {
            nodes[i] = nums[l];
        } else {
            int mid = l + (r - l) / 2;
            build(nums, 2 * i + 1, l, mid);
            build(nums, 2 * i + 2, mid + 1, r);
            nodes[i] = Math.max(nodes[2 * i + 1], nodes[2 * i + 2]);
        }
    }
}
package tree.binary.binaryIndexedTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryIndexedTree {
    private int[] tree;

    public BinaryIndexedTree(int[] nums) {
        int n = nums.length;
        tree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            add(i, nums[i - 1]);
        }
    }

//    public BinaryIndexedTree(int[] nums) {
//        int n = nums.length;
//        tree = new int[n + 1];
//        for (int i = 1; i < tree.length; i++) {
//            tree[i] += nums[i - 1];
//            if (i + lowbit(i) < tree.length) {
//                tree[i + lowbit(i)] += tree[i];
//            }
//        }
//    }

    public int presum(int n) {
        int sum = 0, i = n;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

//    public int rangeSum(int l, int r) {
//        return presum(r) - presum(l - 1);
//    }

    public void add(int i, int val) { // add val to tree[i] and its ancestors
        while (i < tree.length) {
            tree[i] += val;
            i += lowbit(i);
        }
    }

//    public void rangeAdd(int l, int r, int val) {
//        add(l, val);
//        add(r + 1, -val);
//    }

    private int lowbit(int n) {
        return n & (-n);
    }

    public List<Integer> getChildren(int parent) {
        List<Integer> children = new ArrayList<>();
        int x = parent - 1, l = parent - lowbit(parent) + 1;
        while (x >= l) {
            children.add(x);
            x -= lowbit(x);
        }
        return children;
    }

    public int getParent(int child) {
        return child + lowbit(child) < tree.length ? child + lowbit(child) : -1;
    }
}

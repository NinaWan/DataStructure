package tree.binary.binarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public BinarySearchTree(int[] nums) {
        for (int num : nums) {
            insert(num);
        }
    }

    public boolean search(int val) {
        return search(root, val);
    }

    private boolean search(Node node, int val) {
        if (node == null) {
            return false;
        }

        if (node.val == val) {
            return true;
        }

        return val < node.val ? search(node.left, val) : search(node.right, val);
    }

    public void insert(int val) {
        root = insert(root, val);
    }

    private Node insert(Node node, int val) {
        if (node == null) {
            size++;
            return new Node(val);
        }

        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        }

        return node;
    }

    public boolean delete(int val) {
        if (!search(val)) {
            return false;
        }

        root = delete(root, val);

        return true;
    }

    private Node delete(Node node, int val) {
        if (node == null) {
            return null;
        }

        if (val < node.val) {
            node.left = delete(node.left, val);
            return node;
        } else if (val > node.val) {
            node.right = delete(node.right, val);
            return node;
        }

        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }

        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }

        Node rightMin = findMin(node.right);
        rightMin.right = deleteMin(node.right);
        rightMin.left = node.left;

        node.left = null;
        node.right = null;

        return rightMin;
    }

    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return findMin(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }

        node.left = deleteMin(node.left);

        return node;
    }

    public List<Integer> toSortedList() {
        List<Integer> sortedList = new ArrayList<>(size);
        toSortedList(root, sortedList);
        return sortedList;
    }

    private void toSortedList(Node node, List<Integer> sortedList) {
        if (node == null) {
            return;
        }

        toSortedList(node.left, sortedList);
        sortedList.add(node.val);
        toSortedList(node.right, sortedList);
    }

    public int getSize() {
        return size;
    }

    private class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

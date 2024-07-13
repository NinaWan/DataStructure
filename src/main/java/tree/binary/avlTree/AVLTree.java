package tree.binary.avlTree;

public class AVLTree {
    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public AVLTree(int[] nums) {
        for (int num : nums) {
            insert(num);
        }
    }

    public boolean search(int val) {
        return search(val, root);
    }

    private boolean search(int val, Node node) {
        if (node == null) {
            return false;
        }

        return val == node.val || (val < node.val ? search(val, node.left) : search(val, node.right));
    }

    public void insert(int val) {
        root = insert(val, root);
    }

    private Node insert(int val, Node node) {
        if (node == null) {
            size++;
            return new Node(val);
        }

        if (val < node.val) {
            node.left = insert(val, node.left);

            if (getHeight(node.left) - getHeight(node.right) > 1) { // rebalance
                node = val < node.left.val ? rightRotate(node) : leftRightRotate(node);
            }
        } else if (val > node.val) {
            node.right = insert(val, node.right);

            if (getHeight(node.right) - getHeight(node.left) > 1) { // rebalance
                node = val > node.right.val ? leftRotate(node) : rightLeftRotate(node);
            }
        }

        // update height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        return node;
    }

    public boolean delete(int val) {
        if (!search(val)) {
            return false;
        }

        root = delete(val, root);

        return true;
    }

    private Node delete(int val, Node node) {
        if (node == null) {
            return null;
        }

        if (val < node.val) {
            node.left = delete(val, node.left);
        } else if (val > node.val) {
            node.right = delete(val, node.right);
        } else {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                node = right;
            } else if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                node = left;
            } else {
                Node rightMin = findMin(node.right);
                node.val = rightMin.val;
                rightMin.right = delete(rightMin.val, node.right);
            }
        }

        if (node == null) {
            return null;
        }

        // update height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // check balance factor and rebalance if needed
        int bf = getBalanceFactor(node);
        if (bf > 1) {
            return getBalanceFactor(node.left) >= 0 ? rightRotate(node) : leftRightRotate(node);
        } else if (bf < -1) {
            return getBalanceFactor(node.right) <= 0 ? leftRotate(node) : rightLeftRotate(node);
        }

        return node;
    }

    private int getBalanceFactor(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    public int getSize() {
        return size;
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

    private Node leftRotate(Node node) {
        Node rNode = node.right;
        node.right = rNode.left;
        rNode.left = node;

        // update heights
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        rNode.height = Math.max(getHeight(rNode.left), getHeight(rNode.right)) + 1;

        return rNode;
    }

    private Node rightRotate(Node node) {
        Node lNode = node.left;
        node.left = lNode.right;
        lNode.right = node;

        // update heights
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        lNode.height = Math.max(getHeight(lNode.left), getHeight(lNode.right)) + 1;

        return lNode;
    }

    private Node rightLeftRotate(Node node) {
        // right rotate node's right child
        node.right = rightRotate(node.right);

        // left rotate node
        return leftRotate(node);
    }

    private Node leftRightRotate(Node node) {
        // left rotate node's left child
        node.left = leftRotate(node.left);

        // right rotate node
        return rightRotate(node);
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        return Math.abs(getBalanceFactor(node)) < 2 && isBalanced(node.left) && isBalanced(node.right);
    }

    private class Node {
        private int val;
        private int height;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
            this.height = 1;
        }
    }
}

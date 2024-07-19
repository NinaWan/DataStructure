package tree.binary;

import java.util.*;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {

    }

    public BinaryTree(int[] nums) {
        for (int num : nums) {
            insert(num);
        }
    }

    public TreeNode insert(int val) {
        if (root == null) {
            return root = new TreeNode(val);
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null) {
                cur.left = new TreeNode(val);
                break;
            }
            queue.offer(cur.left);

            if (cur.right == null) {
                cur.right = new TreeNode(val);
                break;
            }
            queue.offer(cur.right);
        }

        return root;
    }

    public List<TreeNode> preorder_iterative() {
        if (root == null) {
            return Collections.emptyList();
        }

        List<TreeNode> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    public List<TreeNode> preorder_recursive() {
        List<TreeNode> res = new ArrayList<>();
        preorder_recursive(root, res);
        return res;
    }

    private void preorder_recursive(TreeNode node, List<TreeNode> res) {
        if (node == null) {
            return;
        }

        res.add(node);
        preorder_recursive(node.left, res);
        preorder_recursive(node.right, res);
    }

    public List<TreeNode> inorder_iterative() {
        if (root == null) {
            return Collections.emptyList();
        }

        List<TreeNode> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                res.add(stack.peek());
                cur = stack.pop().right;
            }
        }
        return res;
    }

    public List<TreeNode> inorder_recursive() {
        List<TreeNode> res = new ArrayList<>();
        inorder_recursive(root, res);
        return res;
    }

    private void inorder_recursive(TreeNode node, List<TreeNode> res) {
        if (node == null) {
            return;
        }

        inorder_recursive(node.left, res);
        res.add(node);
        inorder_recursive(node.right, res);
    }

    public List<TreeNode> postorder_iterative() {
        if (root == null) {
            return Collections.emptyList();
        }

        List<TreeNode> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
            res.add(0, cur);
        }
        return res;
    }

    public List<TreeNode> postorder_recursive() {
        List<TreeNode> res = new ArrayList<>();
        postorder_recursive(root, res);
        return res;
    }

    private void postorder_recursive(TreeNode node, List<TreeNode> res) {
        if (node == null) {
            return;
        }

        postorder_recursive(node.left, res);
        postorder_recursive(node.right, res);
        res.add(node);
    }

    public List<TreeNode> bfs() {
        if (root == null) {
            return Collections.emptyList();
        }

        List<TreeNode> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            res.add(cur);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

        return res;
    }

    public void delete(int val) {
        delete(root, val);
    }

    private void delete(TreeNode node, int val) {
        if (root == null) {
            return;
        }

        if (root.val == val && root.left == null && root.right == null) {
            root = null;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode last = root, toDelete = null;
        while (!queue.isEmpty()) {
            last = queue.poll();
            if (last.val == val) {
                toDelete = last;
            }
            if (last.left != null) {
                queue.offer(last.left);
            }
            if (last.right != null) {
                queue.offer(last.right);
            }
        }

        if (toDelete != null) {
            toDelete.val = last.val;
            deleteLast(last);
        }
    }

    private void deleteLast(TreeNode last) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (cur.left == last) {
                    cur.left = null;
                    return;
                }
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                if (cur.right == last) {
                    cur.right = null;
                    return;
                }
                queue.offer(cur.right);
            }
        }
    }

    public boolean search(int val) {
        return search(root, val);
    }

    private boolean search(TreeNode node, int val) {
        if (node == null) {
            return false;
        }

        return node.val == val || search(node.left, val) || search(node.right, val);
    }

    public class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public int getVal() {
            return val;
        }
    }
}

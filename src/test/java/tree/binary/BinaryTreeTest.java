package tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    private BinaryTree tested;
    private int[] nums;
    private List<Integer> expected_preorder;
    private List<Integer> expected_inorder;
    private List<Integer> expected_postorder;
    private List<Integer> expected_bfs;

    @BeforeEach
    void setup() {
        nums = new int[]{98, 60, 1, 13, 91, 22, 7, 79, 62, 28};
        tested = new BinaryTree(nums);
        expected_preorder = List.of(98, 60, 13, 79, 62, 91, 28, 1, 22, 7);
        expected_inorder = List.of(79, 13, 62, 60, 28, 91, 98, 22, 1, 7);
        expected_postorder = List.of(79, 62, 13, 28, 91, 60, 22, 7, 1, 98);
        expected_bfs = List.of(98, 60, 1, 13, 91, 22, 7, 79, 62, 28);
    }

    @Test
    void search() {
        assertFalse(tested.search(5));
        assertTrue(tested.search(13));
    }

    @Test
    void insert() {
        assertFalse(tested.search(5));
        tested.insert(5);
        assertTrue(tested.search(5));
    }

    @Test
    void delete() {
        assertTrue(tested.search(13));
        tested.delete(13);
        assertFalse(tested.search(13));
    }

    @Test
    void preorder_iterative() {
        assertIterableEquals(expected_preorder, tested.preorder_iterative().stream().map(BinaryTree.TreeNode::getVal).collect(Collectors.toList()));
    }

    @Test
    void preorder_recursive() {
        assertIterableEquals(expected_preorder, tested.preorder_recursive().stream().map(BinaryTree.TreeNode::getVal).collect(Collectors.toList()));
    }

    @Test
    void inorder_iterative() {
        assertIterableEquals(expected_inorder, tested.inorder_iterative().stream().map(BinaryTree.TreeNode::getVal).collect(Collectors.toList()));
    }

    @Test
    void inorder_recursive() {
        assertIterableEquals(expected_inorder, tested.inorder_recursive().stream().map(BinaryTree.TreeNode::getVal).collect(Collectors.toList()));
    }

    @Test
    void postorder_iterative() {
        assertIterableEquals(expected_postorder, tested.postorder_iterative().stream().map(BinaryTree.TreeNode::getVal).collect(Collectors.toList()));
    }

    @Test
    void postorder_recursive() {
        assertIterableEquals(expected_postorder, tested.postorder_recursive().stream().map(BinaryTree.TreeNode::getVal).collect(Collectors.toList()));
    }

    @Test
    void bfs() {
        assertIterableEquals(expected_bfs, tested.bfs().stream().map(BinaryTree.TreeNode::getVal).collect(Collectors.toList()));
    }
}
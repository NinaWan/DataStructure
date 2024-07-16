package tree.binary.avlTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    private AVLTree tested;
    private int[] nums;

    @BeforeEach
    void setup() {
        nums = new int[]{25, 90, 1, 63, 23, 96, 24, 85, 8, 56};
        tested = new AVLTree(nums);
    }

    @Test
    void search() {
        assertTrue(tested.search(96));
        assertFalse(tested.search(20));
    }

    @Test
    void insert() {
        assertEquals(nums.length, tested.getSize());
        assertFalse(tested.search(5));
        assertTrue(tested.isBalanced());
        tested.insert(5);
        assertTrue(tested.search(5));
        assertEquals(nums.length + 1, tested.getSize());
        assertTrue(tested.isBalanced());
    }

    @Test
    void delete() {
        assertEquals(nums.length, tested.getSize());
        assertTrue(tested.search(24));
        assertTrue(tested.isBalanced());
        tested.delete(24);
        assertFalse(tested.search(24));
        assertEquals(nums.length - 1, tested.getSize());
        assertTrue(tested.isBalanced());
    }
}
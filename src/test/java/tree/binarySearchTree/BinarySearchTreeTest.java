package tree.binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private BinarySearchTree tested;
    private int[] nums;

    @BeforeEach
    void setup() {
        nums = new int[]{15, 61, 34, 11, 38, 17, 47, 57, 80, 81};
        tested = new BinarySearchTree(nums);
    }

    @Test
    void search() {
        assertTrue(tested.search(11));
        assertFalse(tested.search(20));
    }

    @Test
    void insert() {
        assertFalse(tested.search(5));
        tested.insert(5);
        assertTrue(tested.search(5));
    }

    @Test
    void delete() {
        assertTrue(tested.search(17));
        tested.delete(17);
        assertFalse(tested.search(17));
    }

    @Test
    void toSortedList() {
        assertIterableEquals(List.of(11, 15, 17, 34, 38, 47, 57, 61, 80, 81), tested.toSortedList());
    }
}
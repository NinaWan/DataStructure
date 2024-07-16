package tree.binary.binaryIndexedTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class BinaryIndexedTreeTest {
    private BinaryIndexedTree tested;
    private int[] nums = {9, 19, 3, 10, 18, 12, 15, 5};

    @BeforeEach
    public void setup() {
        tested = new BinaryIndexedTree(nums);
    }

    @Test
    void presum() {
        assertEquals(41, tested.presum(4));
        assertEquals(91, tested.presum(8));
    }

    @Test
    void add() {
        tested.add(4, 1);
        assertEquals(92, tested.presum(8));
        assertEquals(31, tested.presum(3));
    }

    @Test
    void getChildren() {
        assertIterableEquals(List.of(7, 6, 4), tested.getChildren(8));
        assertEquals(0, tested.getChildren(1).size());
    }

    @Test
    void getParent() {
        assertEquals(-1, tested.getParent(8));
        assertEquals(4, tested.getParent(2));
    }
}
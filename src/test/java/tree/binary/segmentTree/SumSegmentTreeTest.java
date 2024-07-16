package tree.binary.segmentTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumSegmentTreeTest {
    private SumSegmentTree tested;
    private int[] nums;

    @BeforeEach
    void setup() {
        nums = new int[]{6, 25, 49, 97, 82, 41, 29, 32, 48, 23};
        tested = new SumSegmentTree(nums);
    }

    @Test
    void sum() {
        assertEquals(220, tested.sum(3, 5));
        assertEquals(432, tested.sum(0, nums.length - 1));
    }

    @Test
    void update() {
        tested.update(nums, 6, 50);
        assertEquals(50, nums[6]);
        assertEquals(453, tested.sum(0, nums.length - 1));
    }
}
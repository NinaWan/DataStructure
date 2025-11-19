package sparseTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SparseTableMaxTest {
    private int[] nums;
    private SparseTableMax tested;

    @BeforeEach
    void setup() {
        nums = new int[]{75, 7, 31, 61, 76, 93, 49, 14, 19, 79, 21, 97, 34, 31, 80, 73, 57, 33, 71, 34};
        tested = new SparseTableMax(nums);
    }

    @Test
    void query() {
        assertEquals(93, tested.query(4, 9));
    }
}
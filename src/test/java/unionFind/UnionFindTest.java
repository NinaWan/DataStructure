package unionFind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnionFindTest {
    private UnionFind tested;
    private int n;

    @BeforeEach
    void setup() {
        n = 10;
        tested = new UnionFind(n);
    }

    @Test
    void union() {
        assertTrue(tested.union(1, 2));
        assertFalse(tested.union(2, 1));
    }

    @Test
    void find() {
        for (int i = 0; i < n; i++) {
            assertEquals(i, tested.find(i));
        }
        assertTrue(tested.union(1, 2));
        assertEquals(2, tested.find(1));
    }

    @Test
    void isConnected() {
        assertFalse(tested.isConnected());
        for (int i = 0; i < n - 1; i++) {
            assertFalse(tested.isConnected(i, i + 1));
            assertTrue(tested.union(i, i + 1));
            assertTrue(tested.isConnected(i, i + 1));
        }
        assertTrue(tested.isConnected());
    }
}
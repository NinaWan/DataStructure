package tree.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    private Trie tested;
    private List<String> words;

    @BeforeEach
    void setUp() {
        words = Arrays.asList("app", "apple", "apply", "but");
        tested = buildTree(words);
    }

    @Test
    void insert() {
        tested.insert("button");
        tested.insert("app");
    }

    @Test
    void search() {
        assertTrue(tested.search("app"));
        assertFalse(tested.search("button"));
        assertFalse(tested.search("ap"));
    }

    @Test
    void startsWith() {
        assertTrue(tested.startsWith("ap"));
        assertTrue(tested.startsWith("app"));
        assertFalse(tested.search("butt"));
    }

    @Test
    void delete() {
        tested.delete("but");
        assertFalse(tested.search("but"));

        tested.delete("ap");
        assertTrue(tested.startsWith("ap"));

        tested.delete("app");
        assertFalse(tested.search("app"));
        assertTrue(tested.startsWith("app"));
        assertTrue(tested.search("apple"));
        assertTrue(tested.search("apply"));

        tested.insert("app");
        tested.delete("apple");
        assertTrue(tested.search("app"));
        assertTrue(tested.search("apply"));
        assertFalse(tested.search("apple"));
    }

    private Trie buildTree(List<String> words) {
        Trie tree = new Trie();
        for (String word : words) {
            tree.insert(word);
        }
        return tree;
    }
}
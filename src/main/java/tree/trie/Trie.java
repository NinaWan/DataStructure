package tree.trie;

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }

            node = node.children[c - 'a'];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }

            node = node.children[c - 'a'];
        }

        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }

            node = node.children[c - 'a'];
        }

        return true;
    }

    public boolean delete(String word) {
        TrieNode node = root, lastCommonNode = null;
        char lastChar = 'a';

        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }

            int childCount = 0;
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    childCount++;
                }
            }

            if (childCount > 1 || (childCount == 1 && node.isEnd)) {
                lastCommonNode = node;
                lastChar = c;
            }

            node = node.children[c - 'a'];
        }

        int childCount = 0;
        for (TrieNode next : node.children) {
            if (next != null) {
                childCount++;
            }
        }

        if (childCount > 0) { // deleted word is a prefix of other words
            node.isEnd = false;
        } else if (lastCommonNode != null) { // share common prefix with other words
            lastCommonNode.children[lastChar - 'a'] = null;
        } else { // not overlapped with any other word, delete all nodes
            root.children[word.charAt(0) - 'a'] = null;
        }

        return true;
    }

    public static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }
}
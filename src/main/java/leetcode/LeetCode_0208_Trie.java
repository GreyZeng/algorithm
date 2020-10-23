package leetcode;

//Implement a trie with insert, search, and startsWith methods.
//
//        Example:
//
//        Trie trie = new Trie();
//
//        trie.insert("apple");
//        trie.search("apple");   // returns true
//        trie.search("app");     // returns false
//        trie.startsWith("app"); // returns true
//        trie.insert("app");
//        trie.search("app");     // returns true
//        Note:
//
//        You may assume that all inputs are consist of lowercase letters a-z.
//        All inputs are guaranteed to be non-empty strings.

public class LeetCode_0208_Trie {
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    public static class Trie {
        public static class Node {
            public boolean end;
            public Node[] next;

            public Node() {
                end = false;
                next = new Node[26];
            }
        }

        private Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            char[] chars = word.toCharArray();
            int index = chars[0] - 'a';
            Node next;
            if (root.next[index] != null) {
                next = root.next[index];
            } else {
                next = new Node();
                root.next[index] = next;
            }
            for (int i = 1; i < chars.length; i++) {
                if (next.next[chars[i] - 'a'] != null) {
                    next = next.next[chars[i] - 'a'];
                } else {
                    Node n = new Node();
                    next.next[chars[i] - 'a'] = n;
                    next = n;
                }
            }
            next.end = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (word == null || word.isEmpty()) {
                return false;
            }
            char[] chars = word.toCharArray();
            if (root.next[chars[0] - 'a'] == null) {
                return false;
            }
            Node c = root.next[chars[0] - 'a'];
            for (int i = 1; i < chars.length; i++) {
                if (c.next[chars[i] - 'a'] == null) {
                    return false;
                }
                c = c.next[chars[i] - 'a'];
            }
            return c.end;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.isEmpty()) {
                return false;
            }
            char[] chars = prefix.toCharArray();
            if (root.next[chars[0] - 'a'] == null) {
                return false;
            }
            Node c = root.next[chars[0] - 'a'];
            for (int i = 1; i < chars.length; i++) {
                if (c.next[chars[i] - 'a'] == null) {
                    return false;
                }
                c = c.next[chars[i] - 'a'];
            }
            return true;
        }
    }


}

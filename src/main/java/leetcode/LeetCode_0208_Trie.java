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
        public Node root;

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
            if (null == word || word.length() < 1) {
                return;
            }
            Node c = root;
            char[] str = word.toCharArray();
            c.p++;
            int n;
            for (char value : str) {
                n = value - 'a';
                if (c.next[n] == null) {
                    c.next[n] = new Node();
                }
                c.next[n].p++;
                c = c.next[n];
            }
            c.e++;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (null == word || word.length() < 1) {
                return false;
            }
            char[] str = word.toCharArray();
            Node c = root;
            int n;
            for (char v : str) {
                n = v - 'a';
                if (c.next[n] == null) {
                    return false;
                }
                c = c.next[n];
            }
            return c.e != 0;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (null == prefix || prefix.length() < 1) {
                return false;
            }
            char[] str = prefix.toCharArray();
            Node c = root;
            int n;
            for (char v : str) {
                n = v - 'a';
                if (c.next[n] == null) {
                    return false;
                }
                c = c.next[n];
            }
            return true;
        }


        public static class Node {
            public int p;
            public int e;
            public Node[] next;

            public Node() {
                next = new Node[26];
            }
        }
    }


}

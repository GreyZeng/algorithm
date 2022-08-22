package leetcode.medium;

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
// https://leetcode-cn.com/problems/implement-trie-prefix-tree/
public class LeetCode_0208_Trie {

    class Trie {
        class Node {
            int p;
            int e;
            Node[] nodes = new Node[26];
        }

        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            char[] str = word.toCharArray();
            Node cur = root;
            for (char c : str) {
                cur.p++;
                if (cur.nodes[c - 'a'] == null) {
                    cur.nodes[c - 'a'] = new Node();
                }
                cur = cur.nodes[c - 'a'];
            }
            cur.e++;
        }

        public boolean search(String word) {
            char[] str = word.toCharArray();
            Node cur = root;
            for (char c : str) {
                if (cur.nodes[c - 'a'] == null) {
                    return false;
                }
                cur = cur.nodes[c - 'a'];
            }
            return cur.e != 0;
        }

        public boolean startsWith(String prefix) {
            char[] str = prefix.toCharArray();
            Node cur = root;
            for (char c : str) {
                if (cur.nodes[c - 'a'] == null) {
                    return false;
                }
                cur = cur.nodes[c - 'a'];
            }
            return true;
        }
    }
}

package git.snippet.trie;

import java.util.HashMap;

// 前缀树
// 笔记：https://www.cnblogs.com/greyzeng/p/16647565.html
// p: 某个字符经过几次
// e: 以某个字符结尾有几次
// 头节点开始
//
// - 所有字符串有多少以某几个字符作为前缀
// - 某个字符串是否包含在其中
//
// 两种实现，如果字符固定， 可以用数组，如果字符不固定Hash表实现
//
//
// 需要注意前缀树的删除节点操作，有可能删除的节点后续的路没用了。（防止内存泄露）
// ab
// abcvdk
// 这两个字符串
// 如果要删掉abcvdk这个字符串，那么通向c v d k节点的路可以全部删除（p=0以下的节点都没有了）
public class Code_TrieTree {
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            nexts = new Node1[26];
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String word) {
            if (word == null || word.length() < 1) {
                return;
            }
            Node1 c = root;
            char[] str = word.toCharArray();
            c.pass++;
            for (char v : str) {
                int n = v - 'a';
                if (c.nexts[n] == null) {
                    c.nexts[n] = new Node1();
                }
                c.nexts[n].pass++;
                c = c.nexts[n];
            }
            c.end++;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            Node1 cur = root;
            cur.pass--;
            char[] str = word.toCharArray();
            for (char v : str) {
                int n = v - 'a';
                if (--cur.nexts[n].pass == 0) {
                    cur.nexts[n] = null;
                    return;
                }
                cur = cur.nexts[n];
            }
            cur.end--;
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null || word.length() < 1) {
                return 0;
            }
            Node1 cur = root;
            char[] str = word.toCharArray();
            for (char c : str) {
                int n = c - 'a';
                if (cur.nexts[n] == null) {
                    return 0;
                }
                cur = cur.nexts[n];
            }
            return cur.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null || pre.length() < 1) {
                return 0;
            }
            Node1 cur = root;
            char[] str = pre.toCharArray();
            for (char c : str) {
                int n = c - 'a';
                if (cur.nexts[n] == null) {
                    return 0;
                }
                cur = cur.nexts[n];
            }
            return cur.pass;
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null || word.length() < 1) {
                return;
            }
            char[] str = word.toCharArray();
            Node2 cur = root;
            cur.pass++;
            int n = 0;
            for (char v : str) {
                n = v;
                if (!cur.nexts.containsKey(n)) {
                    cur.nexts.put(n, new Node2());
                }
                cur.nexts.get(n).pass++;
                cur = cur.nexts.get(n);
            }
            cur.end++;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            char[] str = word.toCharArray();
            Node2 cur = root;
            cur.pass--;
            for (char v : str) {
                int n = v;
                if (--cur.nexts.get(n).pass == 0) {
                    cur.nexts.remove(n);
                    return;
                }
                cur = cur.nexts.get(n);
            }
            cur.end--;
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null || word.length() < 1) {
                return 0;
            }
            char[] str = word.toCharArray();
            Node2 cur = root;
            for (char v : str) {
                int n = v;
                if (!cur.nexts.containsKey(n)) {
                    return 0;
                }
                cur = cur.nexts.get(n);
            }
            return cur.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null || pre.length() < 1) {
                return 0;
            }
            char[] str = pre.toCharArray();
            Node2 cur = root;
            for (char v : str) {
                int n = v;
                if (!cur.nexts.containsKey(n)) {
                    return 0;
                }
                cur = cur.nexts.get(n);
            }
            return cur.pass;
        }
    }
}

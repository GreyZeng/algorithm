package leetcode.hard;

// https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/
public class LeetCode_1707_MaximumXORWithAnElementFromArray {
    // FIXME
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        Trie trie = new Trie(nums);
        for (int i = 0; i < queries.length; i++) {
            int v = queries[i][0];
            int limit = queries[i][1];
            res[i] = trie.get(v, limit);
        }
        return res;
    }


    public class Trie {
        Node head;

        public Trie(int[] arr) {
            head = new Node();
            for (int v : arr) {
                add(v);
            }
        }

        public int get(int v, int limit) {
            Node cur = head;
            int expect = 0;
            for (int i = 31; i >= 0; i--) {
                if (cur.min > limit) {
                    return -1;
                }
                int oneOrZero = i == 31 ? (v >>> i) & 1 : ((v >>> i) & 1) ^ 1;
                oneOrZero = cur.next[oneOrZero] != null ? oneOrZero : oneOrZero ^ 1;
                expect |= (oneOrZero << i);
                cur = cur.next[oneOrZero];
            }
            return expect ^ v;
        }

        public void add(int v) {
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                int oneOrZero = (v >>> i) & 1;
                if (cur.next[oneOrZero] != null) {
                    cur.min = Math.min(v, cur.min);
                } else {
                    Node node = new Node();
                    node.min = v;
                    cur.next[oneOrZero] = node;
                }
                cur = cur.next[oneOrZero];
            }
        }
    }

    public class Node {
        public Node[] next = new Node[2];
        public int min;
    }

}

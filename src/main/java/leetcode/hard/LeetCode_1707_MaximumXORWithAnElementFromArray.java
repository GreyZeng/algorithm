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
            if (head.min > limit) {
                return -1;
            }
            Node cur = head;
            int expect = 0;
            for (int i = 31; i >= 0; i--) {
                int oneOrZero = i == 31 ? (v >>> i) & 1 : ((v >>> i) & 1) ^ 1;
                oneOrZero = cur.next[oneOrZero] != null && cur.next[oneOrZero].min <= v ? oneOrZero : oneOrZero ^ 1;
                expect |= (oneOrZero << i);
                cur = cur.next[oneOrZero];
            }
            return expect ^ v;
        }

        public void add(int v) {
            Node cur = head;
            cur.min = Math.min(v, cur.min);
            for (int i = 31; i >= 0; i--) {
                int oneOrZero = (v >>> i) & 1;
                if (cur.next[oneOrZero] == null) {
                    Node node = new Node();
                    node.min = v;
                    cur.next[oneOrZero] = node;
                }
                cur = cur.next[oneOrZero];
                cur.min = Math.min(v, cur.min);
            }
        }
    }

    public class Node {
        public Node[] next = new Node[2];
        public int min;
    }

}

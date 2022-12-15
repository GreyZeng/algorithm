package 练习题.leetcode.hard;

//给定一个非负整数组成的数组nums。另有一个查询数组queries，其中queries[i]=[xi, mi]
//        第i个查询的答案是xi和任何nums数组中不超过mi的元素按位异或（XOR）得到的最大值
//        换句话说，答案是max(nums[j] XOR xi)，其中所有j均满足nums[j]<= mi。如果nums中的所有元素都大于mi，最终答案就是-1
//        返回一个整数数组answer作为查询的答案，其中answer.length==queries.length且answer[i]是第i个查询的答案
//        Leetcode题目：https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
// 前缀树的节点，保留最小值即可
public class LeetCode_1707_MaximumXORWithAnElementFromArray {
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

        public Trie(int[] nums) {
            head = new Node();
            for (int n : nums) {
                add(n);
            }
        }

        public int get(int v, int limit) {
            if (head.min > limit) {
                return -1;
            }
            Node cur = head;
            int expect = 0;
            for (int i = 30; i >= 0; i--) {
                int oneOrZero = ((v >> i) & 1) ^ 1;
                oneOrZero = (cur.next[oneOrZero] != null && cur.next[oneOrZero].min <= limit) ? oneOrZero : oneOrZero ^ 1;
                expect |= (oneOrZero << i);
                cur = cur.next[oneOrZero];
            }
            return expect ^ v;
        }

        public void add(int v) {

            Node cur = head;
            head.min = Math.min(v, head.min);
            for (int i = 30; i >= 0; i--) {
                int oneOrZero = (v >> i) & 1;
                if (cur.next[oneOrZero] == null) {
                    Node node = new Node();
                    cur.next[oneOrZero] = node;
                }
                cur = cur.next[oneOrZero];
                cur.min = Math.min(v, cur.min);
            }
        }
    }

    public class Node {
        public Node[] next;
        public int min;

        public Node() {
            min = Integer.MAX_VALUE;
            next = new Node[2];
        }
    }

}

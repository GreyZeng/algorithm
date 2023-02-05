package 练习题.leetcode.medium;

// 数组中所有数都异或起来的结果，叫做异或和。给定一个数组arr，想知道arr中哪两个数的异或结果最大，返回最大的异或结果
// https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
// 进阶：https://www.nowcoder.com/questionTerminal/43f62c52fbac47feaeabe40ac1ab9091
// 代码：NowCoder_MaxXorSubArray
// tips：前缀树优化
public class LeetCode_0421_MaximumXOROfTwoNumbersInAnArray {
    public static int findMaximumXOR(int[] nums) {
        return maxEor(nums, nums.length);
    }

    public static int maxEor(int[] eor, int n) {
        int max = 0;
        Trie trie = new Trie(eor);
        trie.add(eor[0]);
        for (int i = 1; i < n; i++) {
            max = Math.max(max, trie.get(eor[i]));
        }
        return max;
    }

    public static class Trie {

        public Node head;

        public Trie(int[] arr) {
            head = new Node();
            for (int eor : arr) {
                add(eor);
            }
        }

        public void add(int num) {
            Node cur = head;
            for (int bit = 31; bit >= 0; bit--) {
                int i = ((num >>> bit) & 1);
                if (cur.next[i] == null) {
                    cur.next[i] = new Node();
                }
                cur = cur.next[i];
            }
        }

        public int get(int eor) {
            int expect = 0;
            Node cur = head;
            for (int bit = 31; bit >= 0; bit--) {
                // 符号位期待一样的
                // 非符号位期待相反的
                int expectBit = bit == 31 ? ((eor >>> bit) & 1) : ((((eor >>> bit) & 1)) ^ 1);
                if (cur.next[expectBit] != null) {
                    expect = ((expectBit << bit) | expect);
                    cur = cur.next[expectBit];
                } else {
                    expectBit = (expectBit ^ 1);
                    cur = cur.next[expectBit];
                    expect = ((expectBit << bit) | expect);
                }
            }
            return expect ^ eor;
        }
    }

    public static class Node {

        public Node[] next = new Node[2];
    }
}

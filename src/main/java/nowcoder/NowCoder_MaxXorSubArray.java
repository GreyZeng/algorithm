/*
给定一个整型数组arr，其中可能有正、有负，有零，求其中子数组的最大异或和。
备注:
时间复杂度O(nlog2n)，额外空间复杂度O(nlog2n)。*/
package nowcoder;

import java.util.Scanner;

// https://www.nowcoder.com/questionTerminal/43f62c52fbac47feaeabe40ac1ab9091
public class NowCoder_MaxXorSubArray {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        
        System.out.println(maxEor(arr, n));
        in.close();
    }
    // FIXME
    // 最优解，前缀树加速O(N)
    public static int maxEor(int[] arr, int n) {
        int[] eor = new int[arr.length];
        int max = 0;
        eor[0] = arr[0];
        for (int i = 1; i < n; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        Trie trie = new Trie(eor);
        for (int i = 0; i < n; i++) {
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
            for (int bit = 31; bit > 0; bit--) {
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
            for (int bit = 31; bit > 0; bit--) {
                // 符号位期待一样的
                // 非符号位期待相反的
                int expectBit = bit == 31 ? ((eor >>> bit) & 1) : ((((eor >>> bit) & 1)) ^ 1);
                if (cur.next[expectBit] != null) {
                    expect = (expectBit << bit) | expect;
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

    // 暴力解法，超时，利用前缀异或和数组
    public static int maxEor1(int[] arr, int n) {
        int[] eor = new int[arr.length];
        int max = arr[0];
        eor[0] = arr[0];
        for (int i = 1; i < n; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        for (int i = 1; i < n; i++) {
            max = Math.max(max, eor[i]);
            for (int j = i; j < n; j++) {
                max = Math.max(max, eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

//    // O(N) 最优解
//    public static int maxEor(int[] arr, int n) {
//        if (arr == null || n == 0) {
//            return 0;
//        }
//        int[] eor = new int[n];
//        eor[0] = arr[0];
//        for (int i = 1; i < n; i++) {
//            eor[i] = eor[i - 1] ^ arr[i];
//        }
//        int max = Math.max(0, eor[0]);
//        Trie trie = new Trie();
//        trie.add(eor[0]);
//        for (int i = 0; i < n; i++) {
//            max = Math.max(max, trie.maxEor(eor[i]));
//            trie.add(eor[i]);
//        }
//        return max;
//    }
//
//    public static class Node {
//        Node[] next = new Node[2];
//    }
//
//    public static class Trie {
//        public Node head = new Node();
//
//        // 符号位, 保持一致
//        // 除符号位，从高到底依次期待和自己相反的那个数
//        // 给我一个num，我可以拿到这个num对应最大的异或和返回
//        public int maxEor(int num) {
//            Node cur = head;
//            int res = 0;
//            for (int i = 31; i >= 0; i--) {
//                int bit = (num >>> i) & 1;
//                // 最高位期待一样，其余位置期待相反
//                int except = (i == 31) ? bit : (bit ^ 1);
//                except = cur.next[except] != null ? except : (except ^ 1);
//                // ((bit ^ except) << i)  --> i位置异或好的结果
//                // 每次和res做或运算，走完32轮，res就把所有位置的异或结果收集到了
//                res |= ((bit ^ except) << i);
//                cur = cur.next[except];
//            }
//            return res;
//        }
//
//        // 将一个数加入前缀树
//        public void add(int num) {
//            // 需要从高到低获取每一个数的状态位
//            // int -> 32位
//            Node cur = head;
//            for (int i = 31; i >= 0; i--) {
//                // 带符号右移，可以识别到符号位
//                int bit = (num >>> i) & 1;
//                cur.next[bit] = cur.next[bit] == null ? new Node() : cur.next[bit];
//                cur = cur.next[bit];
//            }
//        }
//    }
}
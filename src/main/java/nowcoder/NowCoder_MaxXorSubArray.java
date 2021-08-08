/*题目描述
数组异或和的定义：把数组中所有的数异或起来得到的值。给定一个整型数组arr，其中可能有正、有负，有零，求其中子数组的最大异或和。
输入描述:
输出包含两行，第一行一个整数n ,代表数组arr长度，第二个n个整数，代表数组arr
输出描述:
输出一个整数，代表其中子数组的最大异或和。
示例1
输入
复制
4
3 -28 -29 2
输出
复制
7
说明
{-28，-29}这个子数组的异或和为7，是所有子数组中最大的
备注:
时间复杂度O(nlog2n)，额外空间复杂度O(nlog2n)。*/
package nowcoder;

import java.util.Scanner;

// https://www.nowcoder.com/questionTerminal/43f62c52fbac47feaeabe40ac1ab9091
public class NowCoder_MaxXorSubArray {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(maxEor(arr, n));
        in.close();

       
    }

    // // 暴力解，利用前缀异或和数组
    public static int maxXor(int[] arr, int n) {
        if (arr == null || n == 0) {
            return 0;
        }
        int[] eor = new int[n];
        // 生成前缀异或和数组
        eor[0] = arr[0];
        for (int i = 1; i < n; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = eor[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, eor[i]);
            for (int j = i; j < n; j++) {
                max = Math.max(max, eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }
    
    public static int maxEor(int[] arr, int n) {
        if (arr == null || n == 0) {
            return 0;
        }
        int[] eor = new int[n];
        eor[0] = arr[0];
        for (int i = 1; i < n; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Math.max(0, eor[0]);
        Trie trie = new Trie();
        trie.add(eor[0]);
        for (int i = 0; i < n; i++) {
            max = Math.max(max, trie.maxEor(eor[i]));
            trie.add(eor[i]);
        }
        return max;
    }

    public static class Node {
        Node[] next = new Node[2];
    }

    public static class Trie {
        public Node head = new Node();

        // 符号位, 保持一致
        // 除符号位，从高到底依次期待和自己相反的那个数
        // 给我一个num，我可以拿到这个num对应最大的异或和返回
        public int maxEor(int num) {
            Node cur = head;
            int res = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                int except = (i == 31) ? bit : (bit ^ 1);
                except = cur.next[except] != null ? except : (except ^ 1);
                res |= (bit ^ except) << i;
                cur = cur.next[except];
            }
            return res;
        }

        // 将一个数加入前缀树
        public void add(int num) {
            // 需要从高到低获取每一个数的状态位
            // int -> 32位
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                // 带符号右移，可以识别到符号位
                int bit = (num >>> i) & 1;
                cur.next[bit] = cur.next[bit] == null ? new Node() : cur.next[bit];
                cur = cur.next[bit];
            }
        }
    }
}
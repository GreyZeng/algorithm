package 数据结构.前缀树;

import java.util.Scanner;

// 数组中所有数都异或起来的结果，叫做异或和。给定一个数组arr，返回arr的最大子数组异或和
// 给定一个整型数组arr，其中可能有正、有负，有零，求其中子数组的最大异或和。
// 备注:
// 时间复杂度O(nlog2n)，额外空间复杂度O(nlog2n)。
// https://www.nowcoder.com/questionTerminal/43f62c52fbac47feaeabe40ac1ab9091
// 方法1：暴力解O(N^3)
// 方法2：O(N^2) 前缀异或和 辅助数组
// 方法3：前缀树
// [11,1,15,10,13,4]
// e[-1] = 0000
// e[0..0] = 11 = 1011
// e[0..1] = 11^1 = 1010
// e[0..2] = 0101
// e[0..3] = 1111
// e[0..4] = 0010
// e[0..5] = 0110
// 这些数构造成前缀树
// 最高位（符合位）期待一样，紧着高位要期待不一样的
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

  // 最优解，前缀树加速O(N)
  public static int maxEor(int[] arr, int n) {
    int[] eor = new int[arr.length];
    int max = 0;
    eor[0] = arr[0];
    for (int i = 1; i < n; i++) {
      eor[i] = eor[i - 1] ^ arr[i];
    }
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
}

package nowcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// https://www.nowcoder.com/practice/5ae5174f17674e458028ce12bc8bfe0b
// 笔记：https://www.cnblogs.com/greyzeng/p/16406847.html
// 描述
// 给出一棵二叉树的先序和中序数组，通过这两个数组直接生成正确的后序数组。
// 输入描述：
// 第一行一个整数 n，表示二叉树的大小。
//
// 第二行 n 个整数 a_i，表示二叉树的先序遍历数组。
//
// 第三行 n 个整数 b_i，表示二叉树的中序遍历数组。
// 输出描述：
// 输出一行 n 个整数表示二叉树的后序遍历数组。
// 比如给定:
// int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
// int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
// {4,5,2,6,7,3,1}
// 假设
// 先序遍历数组 {a, b, d, e, c, f}
// 中序遍历数组 {d, b, e, a, c, f}
// 后序遍历数组 {}
// a 一定是 后序数组的最后一个位置
// a在中序数组的位置是4， 这个位置把中序数组分成两部分，左边就是左树元素，右边就是右树元素
public class NowCoder_PreAndInArrayToPosArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] preOrder = new int[n];
        int[] inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            preOrder[i] = in.nextInt();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inOrder[i] = in.nextInt();
            map.put(inOrder[i], i);
        }
        int[] posOrder = new int[n];
        func(preOrder, 0, n - 1, inOrder, 0, n - 1, posOrder, 0, n - 1, map);
        for (int i = 0; i < n; i++) {
            System.out.print(posOrder[i] + " ");
        }
        in.close();
    }

    public static void func(int[] pre, int l1, int r1, int[] in, int l2, int r2, int[] pos, int l3,
                            int r3) {
        if (l1 > r1) {
            // 避免了无效情况
            return;
        }
        if (l1 == r1) {
            // 只有一个数的时候
            pos[l3] = pre[l1];
        } else {
            // 不止一个数的时候
            pos[r3] = pre[l1];
            // index表示某个头在中序数组中的位置
            int index;
            // 可以优化
            for (index = l2; index <= r2; index++) {
                if (in[index] == pre[l1]) {
                    break;
                }
            }
            int b = index - l2;
            func(pre, l1 + 1, l1 + b, in, l2, index - 1, pos, l3, l3 + b - 1);
            func(pre, l1 + b + 1, r1, in, index + 1, r2, pos, l3 + b, r3 - 1);
        }
    }

    public static void func(int[] pre, int l1, int r1, int[] in, int l2, int r2, int[] pos, int l3,
                            int r3, Map<Integer, Integer> map) {
        if (l1 > r1) {
            // 避免了无效情况
            return;
        }
        if (l1 == r1) {
            // 只有一个数的时候
            pos[l3] = pre[l1];
        } else {
            // 不止一个数的时候
            pos[r3] = pre[l1];
            // index表示某个头在中序数组中的位置
            int index = map.get(pre[l1]);
            int b = index - l2;
            func(pre, l1 + 1, l1 + b, in, l2, index - 1, pos, l3, l3 + b - 1, map);
            func(pre, l1 + b + 1, r1, in, index + 1, r2, pos, l3 + b, r3 - 1, map);
        }
    }

}

package 练习题.leetcode.hard;

import java.util.*;

// 你有k个非递减排列的整数列表。找到一个最小区间，使得k个列表中的每个列表至少有一个数包含在其中
// 我们定义如果b-a < d-c或者在b-a == d-c时a < c，则区间 [a,b] 比 [c,d] 小。
// Leetcode题目：https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
// 笔记：https://www.cnblogs.com/greyzeng/p/16463385.html
public class LeetCode_0632_SmallestRangeCoveringElementsFromKLists {
    public static class Node {
        public int value;// 值是多少
        public int position;// 在链表的哪个位置上
        public int bucket; // 在哪个链表上

        public Node(int v, int p, int b) {
            value = v;
            position = p;
            bucket = b;
        }
    }


    public static int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null) {
            return null;
        }
        if (nums.size() == 1) {
            if (nums.get(0).size() > 0) {
                return new int[]{nums.get(0).get(0), nums.get(0).get(0)};
            } else {
                return null;
            }
        }
        TreeSet<Node> set = new TreeSet<>(
                (o1, o2) -> o1.value != o2.value ? o1.value - o2.value : o1.bucket - o2.bucket);
        int i = 0;
        for (List<Integer> list : nums) {
            set.add(new Node(list.get(0), 0, i));
            i++;
        }
        Node min = set.pollFirst();
        Node max = set.last();
        int[] result = {min.value, max.value};
        while (min.position + 1 < nums.get(min.bucket).size()) {
            set.add(new Node(nums.get(min.bucket).get(min.position + 1), min.position + 1, min.bucket));
            min = set.pollFirst();
            max = set.last();
            result = minRange(result, new int[]{min.value, max.value});
        }
        return result;
    }

    public static int[] minRange(int[] a, int[] b) {
        if (a[1] - a[0] > b[1] - b[0]) {
            return b;
        } else if (a[1] - a[0] < b[1] - b[0]) {
            return a;
        }
        return a[0] > b[0] ? b : a;
    }

    // 类似问题：有三个有序数组，分别在三个数组中挑出3个数，x、y、z。返回 |x-y| + |y-z| + |z-x|最小是多少？
    public static int minRange1(int[][] m) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0; j < m[1].length; j++) {
                for (int k = 0; k < m[2].length; k++) {
                    min = Math.min(min, Math.abs(m[0][i] - m[1][j]) + Math.abs(m[1][j] - m[2][k])
                            + Math.abs(m[2][k] - m[0][i]));
                }
            }
        }
        return min;
    }

    public static int minRange2(int[][] matrix) {
        int N = matrix.length;
        TreeSet<Node> set = new TreeSet<>(
                (o1, o2) -> o1.value != o2.value ? o1.value - o2.value : o1.bucket - o2.bucket);
        for (int i = 0; i < N; i++) {
            set.add(new Node(matrix[i][0], 0, i));
        }
        int min = Integer.MAX_VALUE;
        while (set.size() == N) {
            min = Math.min(min, set.last().value - set.first().value);
            Node cur = set.pollFirst();
            if (cur.position < matrix[cur.bucket].length - 1) {
                set.add(new Node(matrix[cur.bucket][cur.position + 1], cur.position + 1, cur.bucket));
            }
        }
        return min << 1;
    }

    public static int[][] generateRandomMatrix(int n, int v) {
        int[][] m = new int[3][];
        int s = 0;
        for (int i = 0; i < 3; i++) {
            s = (int) (Math.random() * n) + 1;
            m[i] = new int[s];
            for (int j = 0; j < s; j++) {
                m[i][j] = (int) (Math.random() * v);
            }
            Arrays.sort(m[i]);
        }
        return m;
    }

    public static void main(String[] args) {
        int n = 20;
        int v = 200;
        int t = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < t; i++) {
            int[][] m = generateRandomMatrix(n, v);
            int ans1 = minRange1(m);
            int ans2 = minRange2(m);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

}

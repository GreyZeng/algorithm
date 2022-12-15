/*You are given an integer array nums and you have to return a new counts array. 

The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 

Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 

Constraints:

0 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4*/
package 练习题.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
// https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
// 方法1：小和问题 改归并
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
// TODO
// 方法2： 利用只支持单点增加 + 范围查询的动态开点线段树（累加和），也可以用index tree来解
// 方法3：有序表
public class LeetCode_0315_CountOfSmallerNumbersAfterSelf {
    public static class Node {
        public int value;
        public int index;

        public Node(int index, int value) {
            this.value = value;
            this.index = index;
        }
    }

    // 思路转换为：一个数的右边有多少个数比它小！
    // 改归并排序（从大到小）
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result.add(0);
            nodes[i] = new Node(i, nums[i]);
        }
        process(nodes, 0, nums.length - 1, result);
        return result;
    }

    private static void process(Node[] nodes, int l, int r, List<Integer> result) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        process(nodes, l, m, result);
        process(nodes, m + 1, r, result);
        merge(nodes, l, m, r, result);
    }

    private static void merge(Node[] nodes, int l, int m, int r, List<Integer> result) {
        Node[] help = new Node[r - l + 1];
        int s1 = l;
        int s2 = m + 1;
        int index = 0;
        while (s1 <= m && s2 <= r) {
            if (nodes[s1].value > nodes[s2].value) {
                result.set(nodes[s1].index, result.get(nodes[s1].index) + r - s2 + 1);
                help[index++] = nodes[s1++];
            } else if (nodes[s1].value < nodes[s2].value) {
                help[index++] = nodes[s2++];
            } else {
                help[index++] = nodes[s2++];
            }
        }
        while (s1 <= m) {
            help[index++] = nodes[s1++];
        }
        while (s2 <= r) {
            help[index++] = nodes[s2++];
        }
        for (int i = 0; i < help.length; i++) {
            nodes[l + i] = help[i];
        }
    }

    public static class Segment {
        public int sum;
        public Segment left;
        public Segment right;
    }

    public static class DynamicSegmentTree {
        public Segment root;
        public int size;

        public DynamicSegmentTree(int max) {
            root = new Segment();
            size = max;
        }

        public void add(int i, int v) {
            add(root, 1, size, i, v);
        }

        private void add(Segment c, int l, int r, int i, int v) {
            if (l == r) {
                c.sum += v;
            } else {
                int mid = (l + r) / 2;
                if (i <= mid) {
                    if (c.left == null) {
                        c.left = new Segment();
                    }
                    add(c.left, l, mid, i, v);
                } else {
                    if (c.right == null) {
                        c.right = new Segment();
                    }
                    add(c.right, mid + 1, r, i, v);
                }
                c.sum = (c.left != null ? c.left.sum : 0) + (c.right != null ? c.right.sum : 0);
            }
        }

        public int query(int s, int e) {
            return query(root, 1, size, s, e);
        }

        private int query(Segment c, int l, int r, int s, int e) {
            if (c == null) {
                return 0;
            }
            if (s <= l && r <= e) {
                return c.sum;
            }
            int mid = (l + r) / 2;
            if (e <= mid) {
                return query(c.left, l, mid, s, e);
            } else if (s > mid) {
                return query(c.right, mid + 1, r, s, e);
            } else {
                return query(c.left, l, mid, s, e) + query(c.right, mid + 1, r, s, e);
            }
        }

    }

    public static List<Integer> countSmaller2(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            ans.add(0);
        }
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] { nums[i], i };
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        DynamicSegmentTree dst = new DynamicSegmentTree(n);
        for (int[] num : arr) {
            ans.set(num[1], dst.query(num[1] + 1, n));
            dst.add(num[1] + 1, 1);
        }
        return ans;
    }
}

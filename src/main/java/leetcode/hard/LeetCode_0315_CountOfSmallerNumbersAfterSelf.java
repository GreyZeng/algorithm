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
package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 方法1：小和问题 改归并
// 方法2：TODO  利用只支持单点增加 + 范围查询的动态开点线段树（累加和）
public class LeetCode_0315_CountOfSmallerNumbersAfterSelf {
    public static class Key {
        public int value;
        public int index;

        public Key(int index, int value) {
            this.value = value;
            this.index = index;
        }
    }

    // 思路转换为：一个数的右边有多少个数比它小！
    // 改归并排序（从大到小）
    public static List<Integer> countSmaller(int[] nums) {
        int size = nums.length;
        List<Integer> result = new ArrayList<>(size);
        Key[] nodes = new Key[size];
        for (int i = 0; i < size; i++) {
            result.add(0);
            nodes[i] = new Key(i, nums[i]);
        }
        process(nodes, 0, size - 1, result);
        return result;
    }

    private static void process(Key[] nodes, int L, int R, List<Integer> result) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(nodes, L, mid, result);
        process(nodes, mid + 1, R, result);
        merge(nodes, L, mid, R, result);
    }

    private static void merge(Key[] nodes, int l, int mid, int r, List<Integer> result) {
        Key[] help = new Key[r - l + 1];
        int s1 = l;
        int s2 = mid + 1;
        int index = 0;
        while (s1 <= mid && s2 <= r) {
            if (nodes[s1].value < nodes[s2].value) {
                help[index++] = nodes[s2++];
            } else if (nodes[s1].value > nodes[s2].value) {
                result.set(nodes[s1].index, result.get(nodes[s1].index) + 1 + r - s2);
                help[index++] = nodes[s1++];
            } else {
                help[index++] = nodes[s2++];
            }
        }
        while (s1 <= mid) {
            help[index++] = nodes[s1++];
        }
        while (s2 <= r) {
            help[index++] = nodes[s2++];
        }
        index = 0;
        for (Key v : help) {
            nodes[l + (index++)] = v;
        }
    }

    public static class Node {
        public int sum;
        public Node left;
        public Node right;
    }

    public static class DynamicSegmentTree {
        public Node root;
        public int size;

        public DynamicSegmentTree(int max) {
            root = new Node();
            size = max;
        }

        public void add(int i, int v) {
            add(root, 1, size, i, v);
        }

        private void add(Node c, int l, int r, int i, int v) {
            if (l == r) {
                c.sum += v;
            } else {
                int mid = (l + r) / 2;
                if (i <= mid) {
                    if (c.left == null) {
                        c.left = new Node();
                    }
                    add(c.left, l, mid, i, v);
                } else {
                    if (c.right == null) {
                        c.right = new Node();
                    }
                    add(c.right, mid + 1, r, i, v);
                }
                c.sum = (c.left != null ? c.left.sum : 0) + (c.right != null ? c.right.sum : 0);
            }
        }

        public int query(int s, int e) {
            return query(root, 1, size, s, e);
        }

        private int query(Node c, int l, int r, int s, int e) {
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
            arr[i] = new int[]{nums[i], i};
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

package git.snippet.segmenttree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 笔记：https://blog.csdn.net/hotonyhui/article/details/128474428
// 利用只支持单点增加 + 范围查询的动态开点线段树（累加和），解决leetcode 315
public class LeetCode_0315_CountOfSmallerNumbersAfterSelf {

    public static List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int n = nums.length;
        List<Integer> ans = new ArrayList<>(n);
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
}

package git.snippet.mergesort;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Given an integer array nums, return an integer array counts
// where counts[i] is the number of smaller elements to the right of nums[i].
//
// Constraints:
//
// 0 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
// https://leetcode.com/problems/count-of-smaller-numbers-after-self/
// 方法1：小和问题 改归并
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
// TODO
// 方法2： 利用只支持单点增加 + 范围查询的动态开点线段树（累加和），也可以用index tree来解
// 方法3：有序表
public class LeetCode_0315_CountOfSmallerNumbersAfterSelf {
  public class Node {
    public int value;
    public int index;

    public Node(int index, int value) {
      this.value = value;
      this.index = index;
    }
  }

  // 思路转换为：一个数的右边有多少个数比它小！
  // 改归并排序（从大到小）
  public List<Integer> countSmaller(int[] nums) {
    List<Integer> ans = new LinkedList<>();
    int[] result = new int[nums.length];
    Node[] nodes = new Node[nums.length];
    for (int i = 0; i < nums.length; i++) {
      nodes[i] = new Node(i, nums[i]);
    }
    count(nodes, 0, nums.length - 1, result);
    for (int n : result) {
      ans.add(n);
    }
    return ans;
  }

  public void count(Node[] nums, int l, int r, int[] result) {
    if (l != r) {
      int m = ((r - l) >> 1) + l;
      count(nums, l, m, result);
      count(nums, m + 1, r, result);
      merge(nums, l, m, r, result);
    }
  }

  // 54 21 20 19 18 17
  public void merge(Node[] nums, int l, int m, int r, int[] result) {
    Node[] help = new Node[r - l + 1];
    int i = 0;
    int ls = l;
    int rs = m + 1;
    while (ls <= m && rs <= r) {
      if (nums[ls].value > nums[rs].value) {
        result[nums[ls].index] = r - rs + 1 + result[nums[ls].index];
        help[i++] = nums[ls++];
      } else {
        help[i++] = nums[rs++];
      }
    }
    while (ls <= m) {
      help[i++] = nums[ls++];
    }
    while (rs <= r) {
      help[i++] = nums[rs++];
    }
    for (i = 0; i < help.length; i++) {
      nums[l + i] = help[i];
    }
  }

  public class Segment {
    public int sum;
    public Segment left;
    public Segment right;
  }

  public class DynamicSegmentTree {
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

  public List<Integer> countSmaller2(int[] nums) {
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
      arr[i] = new int[] {nums[i], i};
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

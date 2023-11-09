package git.snippet.mergesort;

import java.util.HashSet;

// https://leetcode.com/problems/count-of-range-sum/
// 方法1：归并排序
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
// 思路
// 1. 前缀和加速求区间和
// 2. 必须以i结尾的达标子数组有多少个
// 方法2：改写有序表
// 思路：
// 子数组必须以i位置结尾，有多少是落在[a,b], 就可以通过前缀和
// 0 - 0
// 0 - 1
// ...
// 0 - i-1
// 有多少个累加和落在 [sum - a, sum -b] 上
// 前缀和加入到有序表
// 有序表提供add(num) [可以加入重复数字] 和 search(L,R)【L...R中有多少个，其实只需要提供<num的数有多少个这个接口加工而来】 两个接口即可
// 左滑不处理，右滑累加，每个数据项里面包含节点个数
public class LeetCode_0327_CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] preSum = new long[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        return count(preSum, 0, preSum.length - 1, lower, upper);
    }

    public int count(long[] preSum, int l, int r, int lower, int upper) {
        if (l == r) {
            if (preSum[l] <= upper && preSum[l] >= lower) {
                return 1;
            }
            return 0;
        }
        int m = l + ((r - l) >> 1);
        int left = count(preSum, l, m, lower, upper);
        int right = count(preSum, m + 1, r, lower, upper);
        int merge = merge(preSum, l, m, r, lower, upper);
        return left + right + merge;
    }

    private int merge(long[] preSum, int l, int m, int r, int lower, int upper) {
        int ans = 0;
        // 单调性->滑动窗口
        int windowL = l;
        int windowR = l;
        // [windowL, windowR)
        for (int i = m + 1; i <= r; i++) {
            long min = preSum[i] - upper;
            long max = preSum[i] - lower;
            while (windowR <= m && preSum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= m && preSum[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }

        // mergeSort经典代码
        long[] helper = new long[r - l + 1];
        int ls = l;
        int rs = m + 1;
        int index = 0;
        while (ls <= m && rs <= r) {
            if (preSum[ls] > preSum[rs]) {
                helper[index++] = preSum[rs++];
            } else {
                helper[index++] = preSum[ls++];
            }
        }
        while (ls <= m) {
            helper[index++] = preSum[ls++];
        }
        while (rs <= r) {
            helper[index++] = preSum[rs++];
        }
        int k = 0;
        for (long num : helper) {
            preSum[l + (k++)] = num;
        }
        return ans;
    }

    // ways2 通过改有序表的方式实现。
    public int countRangeSum2(int[] nums, int lower, int upper) {
        // 黑盒，加入数字（前缀和），不去重，可以接受重复数字
        // < num , 有几个数？
        SizeBalancedTreeSet treeSet = new SizeBalancedTreeSet();
        long sum = 0;
        int ans = 0;
        treeSet.add(0); // 一个数都没有的时候，就已经有一个前缀和累加和为0，
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // [sum - upper, sum - lower]
            // [10, 20] ?
            // < 10 ? < 21 ?
            long a = treeSet.lessKeySize(sum - lower + 1);
            long b = treeSet.lessKeySize(sum - upper);
            ans += a - b;
            treeSet.add(sum);
        }
        return ans;
    }

    public class SBTNode {
        public long key;
        public SBTNode l;
        public SBTNode r;
        public long size; // 不同key的size
        public long all; // 总的size

        public SBTNode(long k) {
            key = k;
            size = 1;
            all = 1;
        }
    }

    public class SizeBalancedTreeSet {
        private SBTNode root;
        private HashSet<Long> set = new HashSet<>();

        private SBTNode rightRotate(SBTNode cur) {
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            // all modify
            leftNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return leftNode;
        }

        private SBTNode leftRotate(SBTNode cur) {
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            // all modify
            rightNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return rightNode;
        }

        private SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            long leftSize = cur.l != null ? cur.l.size : 0;
            long leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            long leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            long rightSize = cur.r != null ? cur.r.size : 0;
            long rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            long rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode add(SBTNode cur, long key, boolean contains) {
            if (cur == null) {
                return new SBTNode(key);
            } else {
                cur.all++;
                if (key == cur.key) {
                    return cur;
                } else { // 还在左滑或者右滑
                    if (!contains) {
                        cur.size++;
                    }
                    if (key < cur.key) {
                        cur.l = add(cur.l, key, contains);
                    } else {
                        cur.r = add(cur.r, key, contains);
                    }
                    return maintain(cur);
                }
            }
        }

        public void add(long sum) {
            boolean contains = set.contains(sum);
            root = add(root, sum, contains);
            set.add(sum);
        }

        public long lessKeySize(long key) {
            SBTNode cur = root;
            long ans = 0;
            while (cur != null) {
                if (key == cur.key) {
                    return ans + (cur.l != null ? cur.l.all : 0);
                } else if (key < cur.key) {
                    cur = cur.l;
                } else {
                    ans += cur.all - (cur.r != null ? cur.r.all : 0);
                    cur = cur.r;
                }
            }
            return ans;
        }
    }
}

package git.snippet.mergesort;

import java.util.ArrayList;
import java.util.List;


/**
 * 测试链接
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * 方法1：小和问题 改归并
 * 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
 * TODO 方法2： 利用只支持单点增加 + 范围查询的动态开点线段树（累加和），也可以用index tree来解
 *
 * @see git.snippet.segmenttree.LeetCode_0315_CountOfSmallerNumbersAfterSelf
 * 方法3：有序表
 */

public class LeetCode_0315_CountOfSmallerNumbersAfterSelf {
    // 思路转换为：一个数的右边有多少个数比它小！
    // 改归并排序（从大到小）
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>(nums.length);
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(i, nums[i]);
            // 必须初始化
            ans.add(0);
        }
        count(nodes, 0, nums.length - 1, ans);
        return ans;
    }

    public void count(Node[] nums, int l, int r, List<Integer> result) {
        if (l >= r) {
            return;
        }
        int m = ((r - l) >> 1) + l;
        count(nums, l, m, result);
        count(nums, m + 1, r, result);
        merge(nums, l, m, r, result);
    }

    // 54 21 20 19 18 17
    public void merge(Node[] nums, int l, int m, int r, List<Integer> result) {
        Node[] help = new Node[r - l + 1];
        int i = 0;
        int ls = l;
        int rs = m + 1;
        while (ls <= m && rs <= r) {
            if (nums[ls].value > nums[rs].value) {
                result.set(nums[ls].index, r - rs + 1 + result.get(nums[ls].index));
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


    public class Node {
        public int value;
        public int index;

        public Node(int index, int value) {
            this.value = value;
            this.index = index;
        }
    }

}

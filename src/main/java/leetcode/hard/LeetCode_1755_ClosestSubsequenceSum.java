package leetcode.hard;


import java.util.TreeSet;

//最接近sum的子序列累加和问题
//https://leetcode.com/problems/closest-subsequence-sum/
//tips: 查看数据量，由于数组长度不大，可以用分治的方法，左边满足条件的数量，右边满足条件的数量，左右结合的数量
public class LeetCode_1755_ClosestSubsequenceSum {
    public static int minAbsDifference(int[] nums, int goal) {
        TreeSet<Integer> left = new TreeSet<>();
        TreeSet<Integer> right = new TreeSet<>();
        process(nums, 0, nums.length >> 1, 0, left);
        process(nums, nums.length >> 1, nums.length, 0, right);
        int ans = Math.abs(goal);
        for (int e : right) {
            Integer a = left.floor(goal - e);
            if (a != null) {
                ans = Math.min(ans, Math.abs(a + e - goal));
            }
            a = left.ceiling(goal - e);
            if (a != null) {
                ans = Math.min(ans, Math.abs(a + e - goal));
            }
        }
        return ans;
    }

    // 枚举所有子序列的累加和
    public static void process(int[] nums, int s, int e, int sum, TreeSet<Integer> set) {
        if (s == e) {
            set.add(sum);
        } else {
            process(nums, s + 1, e, sum + nums[s], set);
            process(nums, s + 1, e, sum, set);
        }
    }

}

package 练习题.leetcode.hard;

// 给定一个数组arr，返回如果排序之后（注意是如果排序），相邻两数的最大差值。要求时间复杂度O(N)，不能使用非基于比较的排序
// 求最大值和最小值，然后把数分为N+1份，每个桶只装可以进这个桶的最小值和最大值
// 遍历时，把每个数分到每个桶里面，其中肯定有个桶是空的
// 桶之间的数取最大差值即可
// 设置空桶的目的是杀死桶内数据的差值
// https://leetcode-cn.com/problems/maximum-gap/
public class LeetCode_0164_MaximumGap {

    // O(N)解法
    public static int maximumGap(int[] nums) {
        if (null == nums || nums.length < 1) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        // 最大值和最小值一样，说明只有一种数，最大间距就是0
        if (max == min) {
            return 0;
        }
        int len = nums.length;
        // 标记桶是否为空
        boolean[] flag = new boolean[len + 1];
        // i号桶的最大值
        int[] maxArr = new int[len + 1];
        // i号桶的最小值
        int[] minArr = new int[len + 1];
        for (int num : nums) {
            int index = getIndex(num, len, min, max);
            maxArr[index] = flag[index] ? Math.max(maxArr[index], num) : num;
            minArr[index] = flag[index] ? Math.min(minArr[index], num) : num;
            flag[index] = true;
        }
        int ans = 0;
        int last = maxArr[0];
        for (int i = 1; i <= len; i++) {
            if (flag[i]) {
                ans = Math.max(ans, minArr[i] - last);
                last = maxArr[i];
            }
        }
        return ans;
    }

    // 决定num这个数应该进几号桶
    public static int getIndex(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}

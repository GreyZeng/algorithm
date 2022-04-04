/*Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

        Return 0 if the array contains less than 2 elements.

        Example 1:

        Input: [3,6,9,1]
        Output: 3
        Explanation: The sorted form of the array is [1,3,6,9], either
        (3,6) or (6,9) has the maximum difference 3.
        Example 2:

        Input: [10]
        Output: 0
        Explanation: The array contains less than 2 elements, therefore return 0.
        Note:

        You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
        Try to solve it in linear time/space.*/
package leetcode.hard;

//求最大值和最小值，然后把数分为N+1份，每个桶只装可以进这个桶的最小值和最大值
//        遍历时，把每个数分到每个桶里面，其中肯定有个桶是空的
//        桶之间的数取最大差值即可
//        设置空桶的目的是杀死桶内数据的差值
// https://leetcode-cn.com/problems/maximum-gap/
public class LeetCode_0164_MaximumGap {
    public static void main(String[] args) {
        int[] num = {3, 6, 9, 1};
        System.out.println(maximumGap(num));
        int[] num2 = {10};
        System.out.println(maximumGap(num2));
    }

    public static int maximumGap(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        if (max == min) {
            return 0;
        }
        int len = nums.length;
        boolean[] flag = new boolean[len + 1];
        int[] maxArr = new int[len + 1];
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

    public static int getIndex(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}

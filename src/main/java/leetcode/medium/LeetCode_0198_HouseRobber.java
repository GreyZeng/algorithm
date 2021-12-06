package leetcode.medium;

//You are a professional robber planning to rob houses along a street. 
// Each house has a certain amount of money stashed, 
// the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//		Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
//
//
//		Example 1:
//
//		Input: nums = [1,2,3,1]
//		Output: 4
//		Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//		Total amount you can rob = 1 + 3 = 4.
//		Example 2:
//
//		Input: nums = [2,7,9,3,1]
//		Output: 12
//		Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//		Total amount you can rob = 2 + 9 + 1 = 12.
//如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//
//		给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//
//		来源：力扣（LeetCode）
//		链接：https://leetcode-cn.com/problems/house-robber
//		著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0198_HouseRobber {
    public static int rob(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int l = nums.length;
        if (l == 0) {
            return 0;
        }
        if (l == 1) {
            return nums[0];
        }
        if (l == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int preMax = nums[0];
        int max = Math.max(nums[0], nums[1]);
        for (int i = 2; i < l; i++) {
            int cur = Math.max(preMax + nums[i], max);
            preMax = max;
            max = cur;
        }
        return max;
    }

}

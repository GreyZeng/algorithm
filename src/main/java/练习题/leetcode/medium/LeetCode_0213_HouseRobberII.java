package 练习题.leetcode.medium;

//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
//
//		给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
//
//
//
//		示例1：
//
//		输入：nums = [2,3,2]
//		输出：3
//		解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//		示例 2：
//
//		输入：nums = [1,2,3,1]
//		输出：4
//		解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//		    偷窃到的最高金额 = 1 + 3 = 4 。
//		示例 3：
//
//		输入：nums = [1,2,3]
//		输出：3
//
//
//		提示：
//
//		1 <= nums.length <= 100
//		0 <= nums[i] <= 1000
//
//		来源：力扣（LeetCode）
//		链接：https://leetcode.cn/problems/house-robber-ii
//		著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 笔记：https://www.cnblogs.com/greyzeng/p/16494637.html
public class LeetCode_0213_HouseRobberII {

    public static int rob(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }
        if (arr.length == 3) {
            return Math.max(Math.max(arr[0], arr[1]), arr[2]);
        }
        final int n = arr.length;
        // 以下情况是考虑最后一个位置
        int prePre = arr[1];
        int pre = Math.max(arr[1], arr[2]);
        int max = pre;
        for (int i = 3; i < n; i++) {
            int cur = Math.max(pre, prePre + arr[i]);
            prePre = pre;
            pre = cur;
            max = Math.max(cur, max);
        }
        // 以下情况是不考虑最后一个位置
        prePre = arr[0];
        pre = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n - 1; i++) {
            int cur = Math.max(pre, prePre + arr[i]);
            prePre = pre;
            pre = cur;
            max = Math.max(cur, max);
        }
        return max;
    }
}

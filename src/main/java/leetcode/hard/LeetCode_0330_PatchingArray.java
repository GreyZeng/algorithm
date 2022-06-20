package leetcode.hard;

import java.util.Arrays;

//给定一个已排序的正整数数组 nums，和一个正整数n 。从[1, n]区间内选取任意个数字补充到nums中，使得[1, n]区间内的任何数字都可以用nums中某几个数字的和来表示。
//        请返回 满足上述要求的最少需要补充的数字个数。
//        示例1:
//        输入: nums = [1,3], n = 6
//        输出: 1
//        解释:
//        根据 nums里现有的组合[1], [3], [1,3]，可以得出1, 3, 4。
//        现在如果我们将2添加到nums 中，组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
//        其和可以表示数字1, 2, 3, 4, 5, 6，能够覆盖[1, 6]区间里所有的数。
//        所以我们最少需要添加一个数字。
//        示例 2:
//        输入: nums = [1,5,10], n = 20
//        输出: 2
//        解释: 我们需要添加[2,4]。
//        示例3:
//        输入: nums = [1,2,2], n = 5
//        输出: 0
//        提示：
//        1 <= nums.length <= 1000
//        1 <= nums[i] <= 10^4
//        nums按 升序排列
//        1 <= n <= 2^31- 1
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode.cn/problems/patching-array
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//
public class LeetCode_0330_PatchingArray {
    // 时间复杂度：O(m+logn)
    // 其中 m 是数组 nums 的长度，n是给定的正整数。
    public int minPatches(int[] arr, int n) {
        int patches = 0;
        long range = 1;
        int i = 0;
        while (range <= n) {
            if (i < arr.length && arr[i] <= range) {
                range += arr[i];
                i++;
            } else {
                range <<= 1;
                patches++;
            }
        }
        return patches;
    }
}

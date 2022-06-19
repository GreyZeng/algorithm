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
//        1 <= nums[i] <= 104
//        nums按 升序排列
//        1 <= n <= 231- 1
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode.cn/problems/patching-array
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// TODO
public class LeetCode_0330_PatchingArray {
    // arr请保证有序，且正数  1~aim
    public static int minPatches(int[] arr, int aim) {
        int patches = 0; // 缺多少个数字
        long range = 0; // 已经完成了1 ~ range的目标
        Arrays.sort(arr);
        for (int i = 0; i != arr.length; i++) {
            // arr[i]
            // 要求：1 ~ arr[i]-1 范围被搞定！
            while (arr[i] - 1 > range) { // arr[i] 1 ~ arr[i]-1
                range += range + 1; // range + 1 是缺的数字
                patches++;
                if (range >= aim) {
                    return patches;
                }
            }
            // 要求被满足了！
            range += arr[i];
            if (range >= aim) {
                return patches;
            }
        }
        while (aim >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }

    // 嘚瑟
    public static int minPatches2(int[] arr, int K) {
        int patches = 0; // 缺多少个数字
        int range = 0; // 已经完成了1 ~ range的目标
        for (int i = 0; i != arr.length; i++) {
            // 1~range
            // 1 ~ arr[i]-1
            while (arr[i] > range + 1) { // arr[i] 1 ~ arr[i]-1

                if (range > Integer.MAX_VALUE - range - 1) {
                    return patches + 1;
                }

                range += range + 1; // range + 1 是缺的数字
                patches++;
                if (range >= K) {
                    return patches;
                }
            }
            if (range > Integer.MAX_VALUE - arr[i]) {
                return patches;
            }
            range += arr[i];
            if (range >= K) {
                return patches;
            }
        }
        while (K >= range + 1) {
            if (K == range && K == Integer.MAX_VALUE) {
                return patches;
            }
            if (range > Integer.MAX_VALUE - range - 1) {
                return patches + 1;
            }
            range += range + 1;
            patches++;
        }
        return patches;
    }
}

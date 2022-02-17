//给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
//
//        由于答案可能很大，因此 返回答案模 10^9 + 7 。
//
//
//
//        示例 1：
//
//        输入：arr = [3,1,2,4]
//        输出：17
//        解释：
//        子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
//        最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
//
//        示例 2：
//
//        输入：arr = [11,81,94,43,3]
//        输出：444
//
//
//
//        提示：
//
//        1 <= arr.length <= 3 * 104
//        1 <= arr[i] <= 3 * 104
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/sum-of-subarray-minimums
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package leetcode.medium;

import java.util.Stack;

// 单调栈
public class LeetCode_0907_SumOfSubarrayMinimums {
    static int MOD = (int) 1e9 + 7;

    // arr[i]左右两边离i最近的比arr[i]小的位置是m，n
    // 必须以arr[i]作为最小值的子数组有 (i - m) * (n - i)
    public static int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        long max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer popIndex = stack.pop();
                max += (long) arr[popIndex] * (popIndex - (stack.isEmpty() ? -1 : stack.peek())) * (i - popIndex);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer popIndex = stack.pop();
            max += (long) arr[popIndex] * (popIndex - (stack.isEmpty() ? -1 : stack.peek())) * (arr.length - popIndex);
        }
        return (int) (max % MOD);
    }
}

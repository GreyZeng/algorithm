package leetcode;

//Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
//        Each element in the array represents your maximum jump length at that position.
//
//        Your goal is to reach the last index in the minimum number of jumps.
//
//        Example:
//
//        Input: [2,3,1,1,4]
//        Output: 2
//        Explanation: The minimum number of jumps to reach the last index is 2.
//        Jump 1 step from index 0 to 1, then 3 steps to the last index.
//        Note:
//
//        You can assume that you can always reach the last index.
public class LeetCode_0045_JumpGameII {

    public static int jump(int[] arr) {
        if (null == arr) {
            return 0;
        }
        int N = arr.length;
        if (N == 0 || N == 1) {
            return 0;
        }

        int step = 0; // 当前最少步数跳到i
        int cur = 0; // 跳的步数不超过step，最右位置
        int next = arr[0]; // 跳的步数不超过step+1，最右位置
        for (int i = 0; i < N; i++) {
            // 当前步数不超过step，最右的位置不足到i
            if (cur < i) {
                // 更新step的步数
                step++;
                // cur到step+1能走到的最右位置
                cur = next;
            }
            // 更新next到下一步能走到的最右位置
            next = Math.max(next, arr[i] + i);
            if (next >= N - 1) {
                return step + 1;
            }
        }
        return step;
    }

}

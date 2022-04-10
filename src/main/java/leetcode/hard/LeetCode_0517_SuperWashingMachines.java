package leetcode.hard;

/**
 * @author Young
 * @version 1.0
 * @date 2021/1/22 21:18
 */
// https://leetcode-cn.com/problems/super-washing-machines/
public class LeetCode_0517_SuperWashingMachines {
    public static int packageMachine(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return 0;
        }
        int sum = 0;
        int size = arr.length;
        for (int item : arr) {
            sum += item;
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < size; i++) {
            int leftRest = leftSum - i * avg;
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}

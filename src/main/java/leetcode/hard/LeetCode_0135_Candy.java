package leetcode.hard;

// 分糖果问题
// https://leetcode-cn.com/problems/candy/
// 进阶问题：@see NowCoder_CandyII
// https://www.nowcoder.com/questionTerminal/de342201576e44548685b6c10734716e
// 环形分糖果 @see Code_0112_CircleCandy
public class LeetCode_0135_Candy {

    public static int candy(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = arr[i] > arr[i - 1] ? left[i - 1] + 1 : 1;
        }
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = arr[i] > arr[i + 1] ? right[i + 1] + 1 : 1;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }
}

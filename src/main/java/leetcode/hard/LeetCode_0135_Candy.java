package leetcode.hard;

// 分糖果问题
// https://leetcode-cn.com/problems/candy/
// 进阶问题：NowCoder_CandyII
// https://www.nowcoder.com/questionTerminal/de342201576e44548685b6c10734716e
// 环形分糖果 @see Code_0112_CircleCandy
public class LeetCode_0135_Candy {
    public static int candy(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        left[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            left[i] = arr[i] > arr[i - 1] ? left[i - 1] + 1 : 1;
        }
        right[arr.length - 1] = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            right[i] = arr[i] > arr[i + 1] ? right[i + 1] + 1 : 1;
        }
        for (int i = 0; i < arr.length; i++) {
            left[i] = Math.max(left[i], right[i]);
        }
        int sum = 0;
        for (int n : left) {
            sum += n;
        }
        return sum;
    }
}

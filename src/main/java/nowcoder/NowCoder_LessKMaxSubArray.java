//链接：https://www.nowcoder.com/questionTerminal/3473e545d6924077a4f7cbc850408ade
//        来源：牛客网
//
//        给定一个无序数组arr，其中元素可正、可负、可0。给定一个整数k，求arr所有的子数组中累加和小于或等于k的最长子数组长度
//        例如：arr = [3, -2, -4, 0, 6], k = -2. 相加和小于等于-2的最长子数组为{3, -2, -4, 0}，所以结果返回4
//        [要求]
//        时间复杂度为O(n)，空间复杂度为O(n)
//
//
//        输入描述:
//        第一行两个整数N, k。N表示数组长度，k的定义已在题目描述中给出
//        第二行N个整数表示数组内的数
//
//
//        输出描述:
//        输出一个整数表示答案
//        示例1
//        输入
//        5 -2
//        3 -2 -4 0 6
//        输出
//        4
//
//        备注:
//       1⩽N⩽10^5
//      −10^9⩽k⩽10^9
//      −100⩽arr[i]⩽100
package nowcoder;


import java.util.*;
// minSum  必须以i开头的子数组获得的最小累加和是多少
// minSumEnd 必须以i开头的子数组获得最小累加和的时候，扩到的右边界是多少。
public class NowCoder_LessKMaxSubArray {
    public static int maxSubArray(int[] nums, int k) {
        int n = nums.length;
        // i位置向右边最多能扩到多少累加和最小
        int[] minSumEnds = new int[n];
        // i位置向右边最多能扩到的最小累加和
        int[] minSumEndsValue = new int[n];
        initMinSumEnd(nums, minSumEnds, minSumEndsValue, n);
        int end = 0;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (end < n && sum + minSumEndsValue[end] <= k) {
                sum += minSumEndsValue[end];
                end = minSumEnds[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) {
                sum -= nums[i];
            } else {
                end = i + 1;
            }
        }

        return res;
    }

    private static void initMinSumEnd(int[] nums, int[] minSumEnds, int[] minSumEndsValue, int N) {
        minSumEnds[N - 1] = N - 1;
        minSumEndsValue[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            minSumEndsValue[i] = nums[i];
            minSumEnds[i] = i;
            if (minSumEndsValue[i + 1] <= 0) {
                minSumEndsValue[i] += minSumEndsValue[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(maxSubArray(nums, k));
        in.close();
    }
}

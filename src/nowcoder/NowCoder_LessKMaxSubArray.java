//链接：https://www.nowcoder.com/questionTerminal/3473e545d6924077a4f7cbc850408ade
//        来源：牛客网
//
//        给定一个无序数组arr，其中元素可正、可负、可0。给定一个整数k，求arr所有的子数组中累加和小于或等于k的最长子数组长度
//        例如：arr = [3, -2, -4, 0, 6], k = -2. 相加和小于等于-2的最长子数组为{3, -2, -4, 0}，所以结果返回4
//        [要求]
//        时间复杂度为O(n)O(n)，空间复杂度为O(n)O(n)
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

public class NowCoder_LessKMaxSubArray {
    public static int maxSubArray(int[] nums, int k) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        int N = nums.length;
        // i位置向右边最多能扩到多少累加和最小
        int[] minSumEnds = new int[N];
        // i位置向右边最多能扩到的最小累加和
        int[] minSumEndsValue = new int[N];
        initMinSumEnd(nums, minSumEnds, minSumEndsValue);
        // TODO

        return -1;
    }

    private static void initMinSumEnd(int[] nums, int[] minSumEnds, int[] minSumEndsValue) {
        // TODO
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int k = in.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(maxSubArray(nums, k));
        in.close();
    }
}

package leetcode.medium;
//给你一个长度为n的整数数组nums，其中n > 1，返回输出数组output，其中 output[i]等于nums中除nums[i]之外其余各元素的乘积。
//        示例:
//        输入: [1,2,3,4]
//        输出: [24,12,8,6]
//        说明: 请不要使用除法，且在O(n) 时间复杂度内完成此题。
//        进阶：
//        你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
//        Leetcode题目 : https://leetcode.com/problems/product-of-array-except-self/
public class LeetCode_0238_ProductOfArrayExceptSelf {

    // 后缀积数组维持右边的积，左边部分用变脸来维持前缀积
    public static int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] result = new int[N];
        result[0] = 1;
        result[1] = nums[0];
        for (int i = 2; i < N; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int t = 1;
        for (int i = N - 2; i >= 0; i--) {
            t = t * nums[i + 1];
            result[i] = result[i] * t;
        }
        return result;
    }

    // 扩展 : 如果仅仅是不能用除号，把结果直接填在nums里呢？
    // 解法：数一共几个0；每一个位得到结果就是，a / b，位运算替代 /
    
    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5};
        int[] result = productExceptSelf(nums);
        for (int i : result) {
            System.out.println(i + " ");
        }
    }

}

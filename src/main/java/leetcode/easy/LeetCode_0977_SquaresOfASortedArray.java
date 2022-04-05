package leetcode.easy;

//给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
//        示例 1：
//
//        输入：nums = [-4,-1,0,3,10]
//        输出：[0,1,9,16,100]
//        解释：平方后，数组变为 [16,1,0,9,100]
//        排序后，数组变为 [0,1,9,16,100]
//        示例 2：
//
//        输入：nums = [-7,-3,2,3,11]
//        输出：[4,9,9,49,121]
//        提示：
//        1 <= nums.length <= 10^4
//        -104 <= nums[i] <= 10^4
//        nums 已按 非递减顺序 排序
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0977_SquaresOfASortedArray {
    // 平方有多少，绝对值就有多少
    // 因为有0，所以可以从最大值开始填
    public static int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int index = nums.length;
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int left = nums[i] * nums[i];
            int right = nums[j] * nums[j];
            if (left > right) {
                ans[--index] = left;
                i++;
            } else {
                ans[--index] = right;
                j--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-4, -1, 0, 1, 10};
        int[] result = sortedSquares(arr);
        for (int n : result) {
            System.out.print(n + ", ");
        }
    }

}

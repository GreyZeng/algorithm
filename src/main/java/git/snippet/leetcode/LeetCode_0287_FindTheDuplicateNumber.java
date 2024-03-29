package git.snippet.leetcode;

// 给定一个包含n + 1 个整数的数组nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。
// 假设 nums 只有 一个重复的整数 ，返回这个重复的数 。
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
// 示例 1：
// 输入：nums = [1,3,4,2,2]
// 输出：2
// 示例 2：
// 输入：nums = [3,1,3,4,2]
// 输出：3
// 提示：
// 1 <= n <= 10^5
// nums.length == n + 1
// 1 <= nums[i] <= n
// nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
// 进阶：
// 如何证明 nums 中至少存在一个重复的数字?
// 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/find-the-duplicate-number
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0287_FindTheDuplicateNumber {
    // 单链表入环节点就是重复数字
    public static int findDuplicate(int[] arr) {
        int s = arr[0];
        int f = arr[arr[0]];
        // 快指针一次走两步
        // 慢指针一次走一步
        while (f != s) {
            s = arr[s];
            f = arr[arr[f]];
        }
        // 一定会相遇
        // 相遇后，快指针回到0位置，然后一次走一步
        // 慢指针也是一次走一步，一定会在入环节点相遇
        f = 0;
        while (s != f) {
            f = arr[f];
            s = arr[s];
        }
        return s;
    }
}

/*Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

        Notice that the solution set must not contain duplicate triplets.



        Example 1:

        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
        Example 2:

        Input: nums = []
        Output: []
        Example 3:

        Input: nums = [0]
        Output: []


        Constraints:

        0 <= nums.length <= 3000
        -105 <= nums[i] <= 105*/
package leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 复习二元组相加等于某个值的算法
// 1. 排序
// 2. L指针往右动 R往左动
// L + R
// < K L++
// > K R--
// == K 收集答案，L不等于左边的数 L++
// 以上方案比较适合顺序数组
// 如果是不顺序数组，hash表也可以

// 三元组
// 1. 排序
// 遍历每个元素，然后取出这个元素，然后剩下的数调用二元组的算法 算 其余元素相加 和这个元素之和为目标元素的答案
// 从右往左 ，可以优化效率     因为arraylist
public class LeetCode_0015_3Sum {

    public static List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    public static List<List<Integer>> threeSum(int[] nums, int target) {
        if (nums == null || nums.length <= 2) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int length = nums.length;
        return threeSum(nums, 0, length - 1, target);
    }

    // 调用这个方法，必须先保证start...end部分有序
    private static List<List<Integer>> threeSum(int[] nums, int start, int end, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int i = end;
        while (i >= start + 2) {
            int pre = nums[i];
            int gap = target - pre;
            // 找从i+1一直到最后twoSum == gap的值
            List<List<Integer>> lists = twoSum(nums, start + 2, i, gap);
            if (!lists.isEmpty()) {
                for (List<Integer> list : lists) {
                    list.add(pre);
                    ans.add(list);
                }
            }
            i--;
            /*while (i >= start && pre == nums[i]) {
                i--;
            }*/

        }
        return ans;
    }

    // 数组中，求两个元素之和是target的所有序列（需要去重）
    public static List<List<Integer>> twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return new ArrayList<>();
        }
        int length = nums.length;
        Arrays.sort(nums);
        return twoSum(nums, 0, length - 1, target);
    }

    // 如果要调用这个方法，必须先保证start...end部分有序
    private static List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        while (start < end) {
            int pre = nums[start];
            int pos = nums[end];
            if (pre + pos > target) {
                // end -- 到下一个不等于end位置上的值
                while (end > start && nums[end] == pos) {
                    end--;
                }
            } else if (pre + pos < target) {
                // start ++ 到下一个不等于start位置上的值
                while (end > start && nums[start] == pre) {
                    start++;
                }
            } else {
                // 收集答案
                List<Integer> item = new ArrayList<>();
                item.add(pre);
                item.add(pos);
                ans.add(item);
                // start ++ 到下一个不等于start位置上的值
                while (end > start && nums[start] == pre) {
                    start++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        // System.out.println(twoSum(nums,1,5, 0));
        System.out.println(threeSum(nums));
        // System.out.println(twoSum(nums, 3));
    }
}

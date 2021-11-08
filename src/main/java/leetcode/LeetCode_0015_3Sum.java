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
// 从右往左 ，可以优化效率
public class LeetCode_0015_3Sum {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = N - 1; i > 1; i--) { // 三元组最后一个数，是arr[i]   之前....二元组 + arr[i]
            if (i == N - 1 || nums[i] != nums[i + 1]) {
                List<List<Integer>> nexts = twoSum(nums, i - 1, -nums[i]);
                for (List<Integer> cur : nexts) {
                    cur.add(nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    // nums[0...end]这个范围上，有多少个不同二元组，相加==target，全返回
    // {-1,5}     K = 4
    // {1, 3}
    public static List<List<Integer>> twoSum(int[] nums, int end, int target) {
        int L = 0;
        int R = end;
        List<List<Integer>> ans = new ArrayList<>();
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else { // nums[L] + nums[R] == target
                if (L == 0 || nums[L - 1] != nums[L]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[L]);
                    cur.add(nums[R]);
                    ans.add(cur);
                }
                L++;
            }
        }
        return ans;
    }

    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = 1;
        int result = 0;
        while (left < nums.length && right < nums.length) {
            if (left == right || nums[right] - nums[left] < k) {
                right++;
            } else if (nums[right] - nums[left] > k) {
                left++;
            } else {
                left++;
                result++;
                while (left < nums.length && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
        }
        return result;
    }

//    public static List<List<Integer>> threeSum(int[] nums) {
//        return threeSum(nums, 0);
//    }
//
//    public static List<List<Integer>> threeSum(int[] nums, int target) {
//        if (nums == null || nums.length <= 2) {
//            return new ArrayList<>();
//        }
//        Arrays.sort(nums);
//        int length = nums.length;
//        return threeSum(nums, 0, length - 1, target);
//    }
//
//    // 调用这个方法，必须先保证start...end部分有序
//    private static List<List<Integer>> threeSum(int[] nums, int start, int end, int target) {
//        List<List<Integer>> res = new ArrayList<>();
//        for (int i = end; i >= start + 2; i--) {
//            if (i == end || nums[i] != nums[i + 1]) {
//                List<List<Integer>> rest = twoSum(nums, start, i - 1, target - nums[i]);
//                for (List<Integer> item : rest) {
//                    item.add(nums[i]);
//                    res.add(item);
//                }
//            }
//        }
//
//        return res;
//    }
//
//    // 数组中，求两个元素之和是target的所有序列（需要去重）
//    public static List<List<Integer>> twoSum(int[] nums, int target) {
//        if (nums == null || nums.length <= 1) {
//            return new ArrayList<>();
//        }
//        int length = nums.length;
//        Arrays.sort(nums);
//        return twoSum(nums, 0, length - 1, target);
//    }
//
//    // 如果要调用这个方法，必须先保证start...end部分有序
//    private static List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
//        List<List<Integer>> ans = new ArrayList<>();
//        while (start < end) {
//            int pre = nums[start];
//            int pos = nums[end];
//            if (pre + pos > target) {
//                // end -- 到下一个不等于end位置上的值
//                while (end > start && nums[end] == pos) {
//                    end--;
//                }
//            } else if (pre + pos < target) {
//                // start ++ 到下一个不等于start位置上的值
//                while (end > start && nums[start] == pre) {
//                    start++;
//                }
//            } else {
//                // 收集答案
//                List<Integer> item = new ArrayList<>();
//                item.add(pre);
//                item.add(pos);
//                ans.add(item);
//                // start ++ 到下一个不等于start位置上的值
//                while (end > start && nums[start] == pre) {
//                    start++;
//                }
//
//            }
//        }
//        return ans;
//    }
}

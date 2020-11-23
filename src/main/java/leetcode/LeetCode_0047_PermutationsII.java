//Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
//
//
//
//        Example 1:
//
//        Input: nums = [1,1,2]
//        Output:
//        [[1,1,2],
//        [1,2,1],
//        [2,1,1]]
//        Example 2:
//
//        Input: nums = [1,2,3]
//        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//        Constraints:
//
//        1 <= nums.length <= 8
//        -10 <= nums[i] <= 10
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_0047_PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        p(nums, 0, ans);
        return ans;
    }

    // 所有index以后的数字都可以来到index位置进行替换
    // 生成的组合放在path里面
    public static void p(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length - 1) {
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            ans.add(path);
        } else {
            boolean[] visit = new boolean[21];
            for (int i = index; i < nums.length; i++) {
                if (!visit[nums[i] + 10]) {
                    visit[nums[i] + 10] = true;
                    swap(nums, i, index);
                    p(nums, index + 1, ans);
                    swap(nums, index, i);
                }
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}

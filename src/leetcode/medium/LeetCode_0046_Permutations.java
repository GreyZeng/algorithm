package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

//Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
//
//
//
//		Example 1:
//
//		Input: nums = [1,2,3]
//		Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//		Example 2:
//
//		Input: nums = [0,1]
//		Output: [[0,1],[1,0]]
//		Example 3:
//
//		Input: nums = [1]
//		Output: [[1]]
//
//
//		Constraints:
//
//		1 <= nums.length <= 6
//		-10 <= nums[i] <= 10
//		All the integers of nums are unique
// https://leetcode-cn.com/problems/permutations/
// LintCode要求使用递归和非递归两种方式实现
public class LeetCode_0046_Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length < 1) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        process(0, nums, ans);
        return ans;
    }

    private static void process(int i, int[] nums, List<List<Integer>> ans) {
        if (i == nums.length - 1) {
            List<Integer> path = new ArrayList<>();
            for (int n : nums) {
                path.add(n);
            }
            ans.add(path);
        } else {
            for (int index = i; index < nums.length; index++) {
                swap(index, i, nums);
                process(i + 1, nums, ans);
                swap(index, i, nums);
            }
        }
    }

    private static void swap(int index, int i, int[] nums) {
        if (index == i) {
            return;
        }
        nums[i] = nums[i] ^ nums[index];
        nums[index] = nums[i] ^ nums[index];
        nums[i] = nums[i] ^ nums[index];
    }

    // 非递归方法
    public List<List<Integer>> permute2(int[] nums) {
        //先判断数组的特殊情况
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        if (nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }

        //插入第一个数
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        res.add(new ArrayList<>(list));

        //插入剩下的数
        for (int i = 1; i < nums.length; i++) {
            //获取当前res中list个数
            int num_res = res.size();
            //对res中每个list进行数字插入
            for (int j = 0; j < num_res; j++) {
                //获取每个list中当前数字个数，确定可插入方案数
                int num_list = res.get(0).size();
                for (int k = 0; k <= num_list; k++) {
                    //每次取第一个list进行插入，完成后删除掉第一个
                    ArrayList<Integer> temp = new ArrayList<>(res.get(0));
                    temp.add(k, nums[i]);
                    res.add(new ArrayList<>(temp));
                }
                res.remove(0);
            }
        }
        return res;
    }
}

package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Given a set of distinct integers, nums, return all possible subsets (the power set).
//
//		Note: The solution set must not contain duplicate subsets.
//
//		Example:
//
//		Input: nums = [1,2,3]
//		Output:
//		[
//		[3],
//		[1],
//		[2],
//		[1,2,3],
//		[1,3],
//		[2,3],
//		[1,2],
//		[]
//		]
public class LeetCode_0078_Subsets {

    // 记得清除现场
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> a = new ArrayList<>();
        LinkedList<Integer> p = new LinkedList<>();
        process(nums, 0, p, a);
        return a;
    }

    private static void process(int[] nums, int i, LinkedList<Integer> path, List<List<Integer>> ansList) {
        if (i == nums.length) {
            List<Integer> list = new ArrayList<>();
            list.addAll(path);
            ansList.add(list);
        } else {
            process(nums, i + 1, path, ansList);
            path.addLast(nums[i]);
            process(nums, i + 1, path, ansList);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }

}

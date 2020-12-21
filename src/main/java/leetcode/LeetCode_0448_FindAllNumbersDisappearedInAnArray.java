/*Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

		Find all the elements of [1, n] inclusive that do not appear in this array.

		Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

		Example:

		Input:
		[4,3,2,7,8,2,3,1]

		Output:
		[5,6]*/
package leetcode;


import java.util.ArrayList;
import java.util.List;

public class LeetCode_0448_FindAllNumbersDisappearedInAnArray {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                cycle(nums, i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                list.add(i + 1);
            }
        }
        return list;
    }

    private static void cycle(int[] nums, int i) {
        while (nums[i] != i + 1) {
            int t = nums[i];
            if (nums[t - 1] == t) {
                break;
            }
            int m = nums[t-1];
            nums[t - 1] = t;
            nums[i] = m;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> disappearedNumbers = findDisappearedNumbers(nums);
        System.out.println(disappearedNumbers);
    }


}

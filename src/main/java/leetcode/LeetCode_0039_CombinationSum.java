/*Given an array of distinct integers candidates and a target integer target,
return a list of all unique combinations of candidates where the chosen numbers sum to target.
You may return the combinations in any order.

        The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

        It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



        Example 1:

        Input: candidates = [2,3,6,7], target = 7
        Output: [[2,2,3],[7]]
        Explanation:
        2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
        7 is a candidate, and 7 = 7.
        These are the only two combinations.
        Example 2:

        Input: candidates = [2,3,5], target = 8
        Output: [[2,2,2,2],[2,3,3],[3,5]]
        Example 3:

        Input: candidates = [2], target = 1
        Output: []
        Example 4:

        Input: candidates = [1], target = 1
        Output: [[1]]
        Example 5:

        Input: candidates = [1], target = 2
        Output: [[1,1]]


        Constraints:

        1 <= candidates.length <= 30
        1 <= candidates[i] <= 200
        All elements of candidates are distinct.
        1 <= target <= 500*/
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_0039_CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int N = candidates.length;
        return p(candidates, N, 0, target);
    }

    // 从i号位置开始及其后面的所有，帮我搞定target
    private static List<List<Integer>> p(int[] candidates, int N, int i, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (i == N) {
            return ans;
        }
        if (target == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        for (int z = 0; z * candidates[i] <= target; z++) {
            if (z * candidates[i] == target) {
                List<Integer> t = new ArrayList<>();
                for (int j = 0; j < z; j++) {
                    t.add(candidates[i]);
                }
                ans.add(t);
                return ans;
            }
            for (List<Integer> a : p(candidates, N, i + 1, target - z * candidates[i])) {
                for (int j = 0; j < z; j++) {
                    a.add(candidates[i]);
                }
                ans.add(a);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] ca = new int[]{2, 3, 6, 7};
        int t = 7;
        System.out.println(combinationSum(ca, t));
    }

}

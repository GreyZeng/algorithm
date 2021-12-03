/*Given an array of integers nums and an integer k, 
return the total number of continuous subarrays whose sum equals to k.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 10^4
-1000 <= nums[i] <= 1000
-10^7 <= k <= 10^7*/
package leetcode;

import java.util.HashMap;

// arr[L...R] = sum -> arr[0..R] - arr[0...L-1] = sum
// 子数组一定要以i结尾
// Map前缀和（key: 前缀和， value: 前缀和出现的最小位置）
// 初始化 Map.put(0,-1)
// （-100，3，1，2，100）
public class LeetCode_0560_SubarraySumEqualsK {
    public static int subarraySum(int[] nums, int k) {
        // 初始化前缀和数组
        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);
        int all = 0;
        int ans = 0;
        int g;
        for (int num : nums) {
            all += num;
            g = all - k;
            if (dp.containsKey(g)) {
                ans += dp.get(g);
            }
            if (!dp.containsKey(all)) {
                dp.put(all, 1);
            } else {
                dp.put(all, dp.get(all) + 1);
            }
        }
        return ans;

    }
}

/*
 * You are given an array people where people[i] is the weight of the ith person, and an infinite
 * number of boats where each boat can carry a maximum weight of limit. Each boat carries at most
 * two people at the same time, provided the sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 *
 *
 *
 * Example 1:
 *
 * Input: people = [1,2], limit = 3 Output: 1 Explanation: 1 boat (1, 2) Example 2:
 *
 * Input: people = [3,2,2,1], limit = 3 Output: 3 Explanation: 3 boats (1, 2), (2) and (3) Example
 * 3:
 *
 * Input: people = [3,5,3,4], limit = 5 Output: 4 Explanation: 4 boats (3), (3), (4), (5)
 *
 *
 * Constraints:
 *
 * 1 <= people.length <= 5 * 10^4 1 <= people[i] <= limit <= 3 * 10^4
 */
package 练习题.leetcode.medium;

import java.util.Arrays;

// 给定一个正数数组arr，代表若干人的体重，再给定一个正数limit，表示所有船共同拥有的载重量，每艘船最多坐两人，且不能超过载重
// 想让所有的人同时过河，并且用最好的分配方法让船尽量少，返回最少的船数
// Leetcode链接 : https://leetcode.cn/problems/boats-to-save-people/
// tips：双指针
public class LeetCode_0881_BoatsToSavePeople {

    public static int numRescueBoats1(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        Arrays.sort(arr);
        if (arr[N - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return N;
        }
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0;
        while (L >= 0) {
            int solved = 0;
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = (N - all) - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }

    // 首尾双指针的解法
    public static int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        int l = 0;
        int r = people.length - 1;
        int sum = 0;
        while (l <= r) {
            sum = l == r ? people[l] : people[l] + people[r];
            if (sum > limit) {
                r--;
            } else {
                l++;
                r--;
            }
            ans++;
        }
        return ans;
    }
}

/*Given a non negative integer number num.
For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

        Example 1:

        Input: 2
        Output: [0,1,1]
        Example 2:

        Input: 5
        Output: [0,1,1,2,1,2]
        Follow up:

        It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
        Space complexity should be O(n).
        Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.*/
package 练习题.leetcode.easy;


public class LeetCode_0338_CountingBits {

    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i < num + 1; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }


    // TODO 待理解
    public int[] countBits1(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            ans[i] = count(i);
        }
        return ans;
    }

    public static int count(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }
}

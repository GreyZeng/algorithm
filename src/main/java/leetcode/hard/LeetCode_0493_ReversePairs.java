/*Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.*/
package leetcode.hard;

// 方法1： 归并排序
// TODO 方法2： 树状数组
// 小和问题是一个数右边有多少个数比它自己大
// 降序对问题就是求一个数右边有多少个数比它小，可以从右往左来算
public class LeetCode_0493_ReversePairs {

    public static int reversePairs(int[] A) {
        if (null == A || A.length < 2) {
            return 0;
        }
        int size = A.length;
        return process(A, 0, size - 1);
    }

    public static int process(int[] a, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(a, l, m) + process(a, m + 1, r) + merge(a, l, m, r);
    }

    public static int merge(int[] a, int l, int m, int r) {
        // 先执行统计
        int ans = 0;
        int s1 = l;
        int s2 = m + 1;
        while (s1 <= m && s2 <= r) {
            if ((long) a[s1] - (long) a[s2] > (long) a[s2]) {
                ans += (r - s2 + 1);
                s1++;
            } else {
                s2++;
            }
        }
        // 以下是经典mergesort排序
        int[] help = new int[r - l + 1];
        s1 = l;
        s2 = m + 1;
        int index = 0;

        while (s1 <= m && s2 <= r) {
            if (a[s1] < a[s2]) {
                help[index++] = a[s2++];
            } else if (a[s1] > a[s2]) {
                help[index++] = a[s1++];
            } else {
                help[index++] = a[s2++];
            }
        }
        while (s1 <= m) {
            help[index++] = a[s1++];
        }
        while (s2 <= r) {
            help[index++] = a[s2++];
        }
        index = 0;
        for (int n : help) {
            a[l + (index++)] = n;
        }
        return ans;
    }

}

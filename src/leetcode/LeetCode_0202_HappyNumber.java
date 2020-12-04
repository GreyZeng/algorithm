package leetcode;

import java.util.HashSet;

//Write an algorithm to determine if a number n is "happy".
//
//        A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
//
//        Return True if n is a happy number, and False if not.
//
//        Example:
//
//        Input: 19
//        Output: true
//        Explanation:
//        12 + 92 = 82
//        82 + 22 = 68
//        62 + 82 = 100
//        12 + 02 + 02 = 1
public class LeetCode_0202_HappyNumber {

    // 使用Hash表
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            int s = 0;
            while (n != 0) {
                int t = n % 10;
                s += (t * t);
                n = n / 10;
            }
            n = s;
            // 如果沿途算过的值在set中出现过，说明出现了循环引用，此时如果n!=1，则认为肯定不是happyNumber
            if (set.contains(n)) {
                break;
            }
            set.add(n);
        }
        return n == 1;
    }

    //公式解法 不具有普遍性
    public static boolean isHappy2(int n) {
        while (n != 1 && n != 4) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }
}

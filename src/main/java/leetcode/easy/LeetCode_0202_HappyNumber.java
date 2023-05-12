package leetcode.easy;

import java.util.HashSet;

// 编写一个算法来判断一个数 n 是不是快乐数。
// 「快乐数」定义为：
// 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
// 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
// 如果 可以变为 1，那么这个数就是快乐数。
// 如果 n 是快乐数就返回 true ；不是，则返回 false 。
// 示例 1：
// 输入：19
// 输出：true
// 解释：
// 1^2 + 9^2 = 82
// 8^2 + 2^2 = 68
// 6^2 + 8^2 = 100
// 1^2 + 0^2 + 0^2 = 1
// 示例 2：
// 输入：n = 2
// 输出：false
// 提示：
// 1 <= n <= 2^31 - 1
// Leetcode题目 : https://leetcode.com/problems/happy-number
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

    // 公式解法 不具有普遍性
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

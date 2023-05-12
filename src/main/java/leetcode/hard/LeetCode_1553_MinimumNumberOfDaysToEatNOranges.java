package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/
// n数据量比较大，用数组无法AC
// 显而易见的贪心：能一次吃一批就不一次吃一个
public class LeetCode_1553_MinimumNumberOfDaysToEatNOranges {

    static final Map<Integer, Integer> map = new HashMap<>();

    public static int minDays(int n) {
        if (n <= 1) {
            map.put(n, n);
            return n;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int ans = Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3));
        map.put(n, ans);
        return ans;
    }
}

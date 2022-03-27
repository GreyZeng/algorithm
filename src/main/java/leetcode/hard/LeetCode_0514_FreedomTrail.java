package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//tips:
//        f(i,j) -> i拨动到j怎么走最省
//        map记录哪些位置拥有某个字符
//        深度优先遍历
public class LeetCode_0514_FreedomTrail {
    // 暴力解法,LeetCode超时
    public static int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] str1 = ring.toCharArray();
        int n = str1.length;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(str1[i])) {
                map.put(str1[i], new ArrayList<>());
            }
            map.get(str1[i]).add(i);
        }
        return f(key.toCharArray(), map, 0, 0, n);
    }

    // preIndex 目前被对齐的位置是什么位置
    // index需要搞定index...
    // resize 电话盘上一共有多少个位置
    public static int f(char[] key, Map<Character, List<Integer>> map, int preIndex, int index, int resize) {
        if (index == key.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int c : map.get(key[index])) {
            int step = dial(preIndex, c, resize) + 1 + f(key, map, c, index + 1, resize);
            ans = Math.min(ans, step);
        }
        return ans;
    }

    // 从i1到i2的最小距离
    public static int dial(int from, int to, int size) {
        return Math.min(Math.abs(from - to), Math.min(from, to) + size - Math.max(from, to));
    }

    // 使用记忆化搜索，可以通过LeetCode
    public static int findRotateSteps2(String r, String k) {
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] ring = r.toCharArray();
        int n = ring.length;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        int[][] dp = new int[n][k.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(k.toCharArray(), map, 0, 0, n, dp);
    }

    // preIndex 目前被对齐的位置是什么位置
    // index需要搞定index...
    // resize 电话盘上一共有多少个位置
    public static int f2(char[] key, Map<Character, List<Integer>> map, int preIndex, int index, int resize, int[][] dp) {
        if (dp[preIndex][index] != -1) {
            return dp[preIndex][index];
        }
        if (index == key.length) {
            dp[preIndex][index] = 0;
            return dp[preIndex][index];
        }
        int ans = Integer.MAX_VALUE;
        for (int c : map.get(key[index])) {
            int step = dial(preIndex, c, resize) + 1 + f2(key, map, c, index + 1, resize, dp);
            ans = Math.min(ans, step);
        }
        dp[preIndex][index] = ans;
        return ans;
    }

}

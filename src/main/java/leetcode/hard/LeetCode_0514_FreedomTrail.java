package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//tips:
//        f(i,j) -> i拨动到j怎么走最省
//        map记录哪些位置拥有某个字符
//        深度优先遍历
// https://leetcode-cn.com/problems/freedom-trail/
public class LeetCode_0514_FreedomTrail {
    // 暴力解法,LeetCode超时
    public static int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> dict = dict(ring.toCharArray());
        return process(key.toCharArray(), 0, 0, ring.length(), dict);
    }

    // 当前位置是curPos
    // 需要搞定的位置是index...
    private static int process(char[] keys, int curPos, int index, int size, Map<Character, List<Integer>> dict) {
        if (index == keys.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        List<Integer> positions = dict.get(keys[index]);
        for (int toPos : positions) {
            ans = Math.min(ans, dail(curPos, toPos, size) + 1 + process(keys, toPos, index + 1, size, dict));
        }
        return ans;
    }

    private static Map<Character, List<Integer>> dict(char[] ring) {
        Map<Character, List<Integer>> dict = new HashMap<>();
        for (int i = 0; i < ring.length; i++) {
            if (!dict.containsKey(ring[i])) {
                dict.put(ring[i], new ArrayList<>());
            }
            dict.get(ring[i]).add(i);
        }
        return dict;
    }

    // from 到 to的最佳路径，需要考虑顺时针和逆时针
    private static int dail(int from, int to, int size) {
        int case1 = Math.abs(from - to);
        int case2 = Math.min(from, to) + size - Math.max(from, to);
        return Math.min(case1, case2);
    }

    // 记忆化搜索
    public static int findRotateSteps2(String ring, String key) {
        Map<Character, List<Integer>> dict = dict(ring.toCharArray());
        int[][] dp = new int[ring.length()][key.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length - 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        return process(key.toCharArray(), 0, 0, ring.length(), dict, dp);
    }

    // 当前位置是curPos
    // 需要搞定的位置是index...
    private static int process(char[] keys, int curPos, int index, int size, Map<Character, List<Integer>> dict, int[][] dp) {
        if (dp[curPos][index] != Integer.MAX_VALUE) {
            return dp[curPos][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == keys.length) {
            ans = 0;
            dp[curPos][index] = ans;
            return ans;
        }
        List<Integer> positions = dict.get(keys[index]);
        for (int toPos : positions) {
            ans = Math.min(ans, dail(curPos, toPos, size) + 1 + process(keys, toPos, index + 1, size, dict, dp));
        }
        dp[curPos][index] = ans;
        return ans;
    }
}

package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门
// 给定一个字符串ring，表示刻在外环上的编码；给定另一个字符串key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数
// 最初，ring的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使key的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完key中的所有字符
// 旋转ring拼出 key 字符key[i]的阶段中：
// 您可以将ring顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串ring的一个字符与 12:00 方向对齐，并且这个字符必须等于字符key[i] 。
// 如果字符key[i]已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作1 步。按完之后，您可以开始拼写key的下一个字符（下一阶段）, 直至完成所有拼写。
// Leetcode题目：https://leetcode.com/problems/freedom-trail/
// tips:
// f(i,j) -> i拨动到j怎么走最省
// map记录哪些位置拥有某个字符
// 深度优先遍历
// https://leetcode-cn.com/problems/freedom-trail/
public class LeetCode_0514_FreedomTrail {
    // 暴力解法,LeetCode超时
    public static int findRotateSteps1(String ring, String key) {
        Map<Character, List<Integer>> dict = dict(ring.toCharArray());
        return process(key.toCharArray(), 0, 0, ring.length(), dict);
    }

    // 当前位置是curPos
    // 需要搞定的位置是index...
    private static int process(
            char[] keys, int curPos, int index, int size, Map<Character, List<Integer>> dict) {
        if (index == keys.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        List<Integer> positions = dict.get(keys[index]);
        for (int toPos : positions) {
            ans =
                    Math.min(
                            ans, dail(curPos, toPos, size) + 1 + process(keys, toPos, index + 1, size, dict));
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
    private static int process(
            char[] keys,
            int curPos,
            int index,
            int size,
            Map<Character, List<Integer>> dict,
            int[][] dp) {
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
            ans =
                    Math.min(
                            ans, dail(curPos, toPos, size) + 1 + process(keys, toPos, index + 1, size, dict, dp));
        }
        dp[curPos][index] = ans;
        return ans;
    }
}

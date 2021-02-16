/*In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.

        Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.

        Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.

        At the stage of rotating the ring to spell the key character key[i]:

        You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
        If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.
Note:

Length of both ring and key will be in range 1 to 100.
There are only lowercase letters in both strings and might be some duplcate characters in both strings.
It's guaranteed that string key could always be spelled by rotating the string ring.
        */
package leetcode;

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

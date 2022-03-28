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
	// TODO
	// 记忆化搜索
	public static int findRotateSteps2(String ring, String key) {
		Map<Character, List<Integer>> dict = dict(ring.toCharArray());
		int size = ring.length();
		char[] keys = key.toCharArray();
		int m = 0;
		int n = 0;
		int[][] dp = new int[size][n];
		
//		
//		if (index == keys.length) {
//			return 0;
//		}
//		int ans = Integer.MAX_VALUE;
//		List<Integer> positions = dict.get(keys[index]);
//		for (int pos : positions) {
//			ans = Math.min(ans, dail(cur, pos, size) + 1 + process(keys, pos, index + 1, size, dict));
//		}
 
		
		return dp[0][0];
	}

//
//    // 使用记忆化搜索，可以通过LeetCode
//    public static int findRotateSteps2(String r, String k) {
//        Map<Character, List<Integer>> map = new HashMap<>();
//        char[] ring = r.toCharArray();
//        int n = ring.length;
//        for (int i = 0; i < n; i++) {
//            if (!map.containsKey(ring[i])) {
//                map.put(ring[i], new ArrayList<>());
//            }
//            map.get(ring[i]).add(i);
//        }
//        int[][] dp = new int[n][k.length() + 1];
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                dp[i][j] = -1;
//            }
//        }
//        return f2(k.toCharArray(), map, 0, 0, n, dp);
//    }
//
//    // preIndex 目前被对齐的位置是什么位置
//    // index需要搞定index...
//    // resize 电话盘上一共有多少个位置
//    public static int f2(char[] key, Map<Character, List<Integer>> map, int preIndex, int index, int resize, int[][] dp) {
//        if (dp[preIndex][index] != -1) {
//            return dp[preIndex][index];
//        }
//        if (index == key.length) {
//            dp[preIndex][index] = 0;
//            return dp[preIndex][index];
//        }
//        int ans = Integer.MAX_VALUE;
//        for (int c : map.get(key[index])) {
//            int step = dial(preIndex, c, resize) + 1 + f2(key, map, c, index + 1, resize, dp);
//            ans = Math.min(ans, step);
//        }
//        dp[preIndex][index] = ans;
//        return ans;
//    }

}

package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

// 我们给出了 N 种不同类型的贴纸。每个贴纸上都有一个小写的英文单词。
// 你希望从自己的贴纸集合中裁剪单个字母并重新排列它们，从而拼写出给定的目标字符串 target。
// 如果你愿意的话，你可以不止一次地使用每一张贴纸，而且每一张贴纸的数量都是无限的。
// 拼出目标target 所需的最小贴纸数量是多少？如果任务不可能，则返回 -1。
// 示例 1：
// 输入：
// ["with", "example", "science"], "thehat"
// 输出：
// 3
// 解释：
// 我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
// 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
// 此外，这是形成目标字符串所需的最小贴纸数量。
// 示例 2：
// 输入：
// ["notice", "possible"], "basicbasic"
// 输出：
// -1
// 解释：
// 我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。
// 提示：
// stickers 长度范围是[1, 50]。
// stickers 由小写英文单词组成（不带撇号）。
// target 的长度在[1, 15]范围内，由小写字母组成。
// 在所有的测试案例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选取的，目标是两个随机单词的串联。
// 时间限制可能比平时更具挑战性。预计 50 个贴纸的测试案例平均可在35ms内解决。
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/stickers-to-spell-word
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0691_StickersToSpellWord {
    public static int minStickers(String[] stickers, String target) {
        if (stickers == null || stickers.length < 1 || target.length() < 1) {
            return 0;
        }
        int p = process(stickers, target);
        return p == Integer.MAX_VALUE ? -1 : p;
    }

    public static int process(String[] stickers, String target) {
        if (target.length() == 0) {
            // 目标是空串，只需要0张贴纸
            return 0;
        }
        int ways = Integer.MAX_VALUE;
        for (String s : stickers) {
            String rest = minus(s, target);
            if (target.length() != rest.length()) {
                // 有效
                ways = Math.min(process(stickers, rest), ways);
            }
        }
        return ways == Integer.MAX_VALUE ? Integer.MAX_VALUE : ways + 1;
    }

    private static String minus(String first, String target) {
        char[] s2 = target.toCharArray();
        char[] s1 = first.toCharArray();
        StringBuilder sb = new StringBuilder();
        int[] dict = new int[26];
        for (char c : s2) {
            dict[c - 'a']++;
        }
        for (char c : s1) {
            dict[c - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            int times = dict[i];
            for (int m = 0; m < times; m++) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.toString();
    }

    // 优化1: 二维数组可以代替词频数组，也可以自然排序
    // 优化2：只选择含有第一个字符的贴纸去尝试
    // 也会超时
    public int minStickers2(String[] stickers, String target) {
        if (target == null || target.length() < 1) {
            return 0;
        }
        int res = p2(build2D(stickers), target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int p2(int[][] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        char[] t = target.toCharArray();
        int ways = Integer.MAX_VALUE;
        // 每一张贴纸作为第一张贴纸，搞定后续的方法数
        for (int[] first : stickers) {
            // 搞定第一个字符的的贴纸才考虑后续过程
            if (first[t[0] - 'a'] > 0) {
                String rest = minus(first, t);
                // rest长度==target长度，说明没有搞定任何情况
                if (rest.length() != target.length()) {
                    ways = Math.min(p2(stickers, rest), ways);
                }
            }
        }
        return ways == Integer.MAX_VALUE ? Integer.MAX_VALUE : ways + 1;
    }

    // 增加缓存
    // 可以AC
    public int minStickers3(String[] stickers, String target) {
        if (target == null || target.length() < 1) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("", 0);
        int res = p3(build2D(stickers), target, map);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int p3(int[][] stickers, String target, Map<String, Integer> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }
        if (target.length() == 0) {
            map.put(target, 0);
            return 0;
        }
        char[] t = target.toCharArray();
        int ways = Integer.MAX_VALUE;
        // 每一张贴纸作为第一张贴纸，搞定后续的方法数
        for (int[] first : stickers) {
            // 搞定第一个字符的的贴纸才考虑后续过程
            if (first[t[0] - 'a'] > 0) {
                String rest = minus(first, t);
                // rest长度==target长度，说明没有搞定任何情况
                if (rest.length() != target.length()) {
                    ways = Math.min(p3(stickers, rest, map), ways);
                }
            }
        }
        ways = ways == Integer.MAX_VALUE ? Integer.MAX_VALUE : ways + 1;
        map.put(target, ways);
        return ways;
    }

    // 将字符串数组转换成二维数组
    public int[][] build2D(String[] stickers) {
        int n = stickers.length;
        int[][] s = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] line = stickers[i].toCharArray();
            for (char c : line) {
                s[i][c - 'a']++;
            }
        }
        return s;
    }

    private String minus(int[] first, char[] t) {
        int[] count = new int[first.length];
        System.arraycopy(first, 0, count, 0, first.length);
        StringBuilder sb = new StringBuilder();
        for (char c : t) {
            if (count[c - 'a'] > 0) {
                count[c - 'a']--;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String target = "thehat";
        String[] stickers = {"with", "example", "science"};
        int rest = new LeetCode_0691_StickersToSpellWord().minStickers2(stickers, target);
        System.out.println(rest);
    }

}

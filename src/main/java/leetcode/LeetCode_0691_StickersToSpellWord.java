package leetcode;

// 我们给出了 N 种不同类型的贴纸。每个贴纸上都有一个小写的英文单词。

// 你希望从自己的贴纸集合中裁剪单个字母并重新排列它们，从而拼写出给定的目标字符串 target。

// 如果你愿意的话，你可以不止一次地使用每一张贴纸，而且每一张贴纸的数量都是无限的。

// 拼出目标 target 所需的最小贴纸数量是多少？如果任务不可能，则返回 -1。

//  

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
//  

// 提示：

// stickers 长度范围是 [1, 50]。
// stickers 由小写英文单词组成（不带撇号）。
// target 的长度在 [1, 15] 范围内，由小写字母组成。
// 在所有的测试案例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选取的，目标是两个随机单词的串联。
// 时间限制可能比平时更具挑战性。预计 50 个贴纸的测试案例平均可在35ms内解决。

// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/stickers-to-spell-word
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0691_StickersToSpellWord {
    // 暴力解，枚举每一张贴纸作为第一张贴纸的情况
    // 暴力方法，会超时
    public int minStickers(String[] stickers, String target) {
        if (target == null || target.length() < 1) {
            return 0;
        }
        int res = p(stickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    public int p(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int ways = Integer.MAX_VALUE;
        // 每一张贴纸作为第一张贴纸，搞定后续的方法数
        for (String first : stickers) {
            String rest = minus(first, target);
            // rest长度==target长度，说明没有搞定任何情况
            if (rest.length() != target.length()) {
                ways = Math.min(p(stickers, rest), ways);
            }
        }
        return ways == Integer.MAX_VALUE ? Integer.MAX_VALUE : ways + 1;
    }

    private String minus(String first, String target) {
        char[] s1 = first.toCharArray();
        char[] s2 = target.toCharArray();
        StringBuilder sb = new StringBuilder();
        int[] dict = new int[26];
        for (char s : s2) {
            dict[s - 'a']++;
        }
        for (char s : s1) {
            dict[s - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            int times = dict[i];
            for (int k = 0; k < times; k++) {
                sb.append((char)(i + 'a'));
            }
        }
        return sb.toString();
    }
    // 优化1: 二维数组可以代替词频数组
    // 优化2：只选择含有第一个字符的贴纸去尝试

    
}

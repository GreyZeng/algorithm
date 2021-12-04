package leetcode.medium;

//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
//
//        'A' -> 1
//        'B' -> 2
//        ...
//        'Z' -> 26
//        要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
//
//        "AAJF" ，将消息分组为 (1 1 10 6)
//        "KJF" ，将消息分组为 (11 10 6)
//        注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
//
//        给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
//
//        题目数据保证答案肯定是一个 32 位 的整数。
//
//         
//
//        示例 1：
//
//        输入：s = "12"
//        输出：2
//        解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
//        示例 2：
//
//        输入：s = "226"
//        输出：3
//        解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
//        示例 3：
//
//        输入：s = "0"
//        输出：0
//        解释：没有字符映射到以 0 开头的数字。
//        含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//        由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
//        示例 4：
//
//        输入：s = "06"
//        输出：0
//        解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/decode-ways
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0091_DecodeWays {
    public static int numDecodings2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        // i位置往后所有解码情况数
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (str[i] == '0') {
                // i位置居然让我独立面对0，则为错误决策
                dp[i] = 0;
            } else {
                // 只使用i位置的值来解码
                dp[i] = dp[i + 1];
                if (i + 1 < n && str[i + 1] <= '6' && str[i] == '2') {
                    dp[i] = dp[i + 1] + dp[i + 2];
                }
                if (i + 1 < n && str[i] == '1') {
                    dp[i] = dp[i + 1] + dp[i + 2];
                }
            }
        }
        return dp[0];

    }

    // 超时
    public static int numDecodings(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        return p(str, 0);
    }

    // 0 ~ i-1位置搞定了，接下来搞定i位置以及后续位置
    public static int p(char[] str, int i) {
        // i到终止位置，找到一种方法
        if (i == str.length) {
            return 1;
        }
        // i位置独立面对0字符，说明之前的决策有问题。直接返回0种
        if (str[i] == '0') {
            return 0;
        }
        // i独立转换
        int p = p(str, i + 1);
        if (i + 1 < str.length && str[i + 1] <= '6' && str[i] == '2') {
            return p + p(str, i + 2);
        }
        if (i + 1 < str.length && str[i] == '1') {
            return p + p(str, i + 2);
        }
        return p;
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("1"));
        System.out.println(numDecodings("26"));
        System.out.println(numDecodings("2611055971756562"));
    }

}

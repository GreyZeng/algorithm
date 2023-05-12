package leetcode.hard;

// TODO
// 一条包含字母A-Z 的消息通过以下的方式进行了编码：
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// 除了上述的条件以外，现在加密字符串可以包含字符 '*'了，字符'*'可以被当做1到9当中的任意一个数字。
// 给定一条包含数字和字符'*'的加密信息，请确定解码方法的总数。
// 同时，由于结果值可能会相当的大，所以你应当对109+ 7取模。（翻译者标注：此处取模主要是为了防止溢出）
// 示例 1 :
// 输入: "*"
// 输出: 9
// 解释: 加密的信息可以被解密为: "A", "B", "C", "D", "E", "F", "G", "H", "I".
// 示例 2 :
// 输入: "1*"
// 输出: 9 + 9 = 18（翻译者标注：这里1*可以分解为1,* 或者当做1*来处理，所以结果是9+9=18）
// Leetcode题目 : https://leetcode.com/problems/decode-ways-ii/
public class LeetCode_0639_DecodeWaysII {

    public static int numDecodings0(String str) {
        return f(str.toCharArray(), 0);
    }

    public static int f(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        // str[index]有字符且不是'0'
        if (str[i] != '*') {
            // str[index] = 1~9
            // i -> 单转
            int p1 = f(str, i + 1);
            if (i + 1 == str.length) {
                return p1;
            }
            if (str[i + 1] != '*') {
                int num = (str[i] - '0') * 10 + str[i + 1] - '0';
                int p2 = 0;
                if (num < 27) {
                    p2 = f(str, i + 2);
                }
                return p1 + p2;
            } else { // str[i+1] == '*'
                // i i+1 -> 一起转 1* 2* 3* 9*
                int p2 = 0;
                if (str[i] < '3') {
                    p2 = f(str, i + 2) * (str[i] == '1' ? 9 : 6);
                }
                return p1 + p2;
            }
        } else { // str[i] == '*' 1~9
            // i 单转 9种
            int p1 = 9 * f(str, i + 1);
            if (i + 1 == str.length) {
                return p1;
            }
            if (str[i + 1] != '*') {
                // * 0 10 20
                // * 1 11 21
                // * 2 12 22
                // * 3 13 23
                // * 6 16 26
                // * 7 17
                // * 8 18
                // * 9 19
                int p2 = (str[i + 1] < '7' ? 2 : 1) * f(str, i + 2);
                return p1 + p2;
            } else { // str[i+1] == *
                // **
                // 11~19 9
                // 21 ~26 6
                // 15
                int p2 = 15 * f(str, i + 2);
                return p1 + p2;
            }
        }
    }

    public static long mod = 1000000007;

    public static int numDecodings1(String str) {
        long[] dp = new long[str.length()];
        return (int) ways1(str.toCharArray(), 0, dp);
    }

    public static long ways1(char[] s, int i, long[] dp) {
        if (i == s.length) {
            return 1;
        }
        if (s[i] == '0') {
            return 0;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        long ans = ways1(s, i + 1, dp) * (s[i] == '*' ? 9 : 1);
        if (s[i] == '1' || s[i] == '2' || s[i] == '*') {
            if (i + 1 < s.length) {
                if (s[i + 1] == '*') {
                    ans += ways1(s, i + 2, dp) * (s[i] == '*' ? 15 : (s[i] == '1' ? 9 : 6));
                } else {
                    if (s[i] == '*') {
                        ans += ways1(s, i + 2, dp) * (s[i + 1] < '7' ? 2 : 1);
                    } else {
                        ans += ((s[i] - '0') * 10 + s[i + 1] - '0') < 27 ? ways1(s, i + 2, dp) : 0;
                    }
                }
            }
        }
        ans %= mod;
        dp[i] = ans;
        return ans;
    }

    public static int numDecodings2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long[] dp = new long[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                dp[i] = dp[i + 1] * (s[i] == '*' ? 9 : 1);
                if (s[i] == '1' || s[i] == '2' || s[i] == '*') {
                    if (i + 1 < n) {
                        if (s[i + 1] == '*') {
                            dp[i] += dp[i + 2] * (s[i] == '*' ? 15 : (s[i] == '1' ? 9 : 6));
                        } else {
                            if (s[i] == '*') {
                                dp[i] += dp[i + 2] * (s[i + 1] < '7' ? 2 : 1);
                            } else {
                                dp[i] += ((s[i] - '0') * 10 + s[i + 1] - '0') < 27 ? dp[i + 2] : 0;
                            }
                        }
                    }
                }
                dp[i] %= mod;
            }
        }
        return (int) dp[0];
    }

    public static int numDecodings3(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long a = 1;
        long b = 1;
        long c = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                c = b * (s[i] == '*' ? 9 : 1);
                if (s[i] == '1' || s[i] == '2' || s[i] == '*') {
                    if (i + 1 < n) {
                        if (s[i + 1] == '*') {
                            c += a * (s[i] == '*' ? 15 : (s[i] == '1' ? 9 : 6));
                        } else {
                            if (s[i] == '*') {
                                c += a * (s[i + 1] < '7' ? 2 : 1);
                            } else {
                                c += a * (((s[i] - '0') * 10 + s[i + 1] - '0') < 27 ? 1 : 0);
                            }
                        }
                    }
                }
            }
            c %= mod;
            a = b;
            b = c;
            c = 0;
        }
        return (int) b;
    }

}

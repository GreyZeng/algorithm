package leetcode;


public class LeetCode_0076_MinimumWindowSubstring {

    // 欠账表 + all
    // 滑动窗口
    // 滑动窗口
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int[] order = new int[256]; // 初始化欠帐表
        for (char c : target) {
            order[c]++;
        }
        int all = target.length;
        int win = -1;
        int L = 0;
        int R = 0;
        int fL = -1;
        int fR = -1;
        while (R != str.length) {
            order[str[R]]--;
            if (order[str[R]] >= 0) {
                // 有效还款
                all--;
            }
            if (all == 0) {
                while (order[str[L]] < 0) {
                    order[str[L++]]++;
                }
                if (win == -1 || win > R - L + 1) {
                    win = R - L + 1;
                    fL = L;
                    fR = R;
                }
                order[str[L++]]++;
                all++;
            }
            R++;
        }

        if (win == -1) {
            return "";
        }
        return s.substring(fL, fR + 1);
    }

    public static void main(String[] args) {
        // System.out.println("abc".substring(0, 2));
        String S = "bdab";
        String T = "ab";
        System.out.println(minWindow(S, T));
    }

}

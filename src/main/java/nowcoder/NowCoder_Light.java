package nowcoder;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。 ‘X’表示墙，不能放灯，也不需要点亮 ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * <p>
 * 暴力方法 可以放灯的点，有放灯不放灯两种情况，在这两种情况下，摘出照亮所有点的情况， 然后再在这些情况中选出灯最少的方案
 */

// https://www.nowcoder.com/questionTerminal/45d20d0e59d94e7d8879f19a5755c177
// 贪心解法
public class NowCoder_Light {
    public static int minLight1(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        return p(str.toCharArray(), 0, new HashSet<>());
    }

    // i及其往后最少的放灯数
    // i之前的放灯情况存在set里面
    public static int p(char[] str, int i, Set<Integer> set) {
        if (i == str.length) {
            for (int s = 0; s < str.length; s++) {
                if (str[s] == '.' && (!set.contains(s - 1) && !set.contains(s) && !set.contains(s + 1))) {
                    return Integer.MAX_VALUE;
                }
            }
            return set.size();
        }
        int no = p(str, i + 1, set);
        if (str[i] == '.') {
            set.add(i);
            int yes = p(str, i + 1, set);
            set.remove(i);
            return Math.min(yes, no);
        }
        return no;
    }

    // 贪心解法
    // i位置有点，且i+1位置也是点，那么i位置一定不需要放灯，等到i+1号位置来放灯
    public static int minLight2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int ans = 0;
        int i = 0;
        while (i < str.length) {
            if (str[i] == 'X') {
                i++;
            } else {
                // 无论如何都要
                ans++;
                if (i + 1 < str.length) {
                    if (str[i + 1] == '.') {
                        i += 3;
                    } else {
                        // str[i+1] == 'X'
                        i += 2;
                    }
                } else {
                    // i+1==str.length
                    i++;
                }

            }
        }
        return ans;
    }


    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");

    }
}

package leetcode;

public class LeetCode_0008_StringToInteger {
    // trim前后空格
    // remove 开头部分的0，开头有+、-的时候，略过开头的+，-在remove掉0， 标志位置m
    // 从右往左，最左的不为数字字符的位置，标志为n
    // substring一下(m,n) 然后带上符号
    // 再判空一下
    // 单个+， 单个-， 没有+，-又不是单个数字字符，则检测不通过
    // 经过以上处理，则只有这些情况： +...  -... num
    // 然后再逐个遍历每个位置，不为数字直接不通过
    // 经过以上情况，则处理成了日常书写的数字格式
    // 一律转换成负数来处理 cur = '0' - str[i]
    // 边界：如果给的字符串是负数的最小值形式去掉负号，则转换成整数的最大值
    public static int myAtoi(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim())) {
            return 0;
        }
        String trim = str.trim();
        char[] chars = trim.toCharArray();
        boolean tagged = chars[0] == '+' || chars[0] == '-';
        int m = 0;
        for (int i = 0; i < chars.length; i++) {
            if (tagged && i == 0) {
                m++;
            } else if (chars[i] == '0') {
                m++;
            } else if (chars[i] != '0') {
                break;
            }
        }

        int n = chars.length;
        for (int i = chars.length - 1; i >= ((chars[0] == '+' || chars[0] == '-') ? 1 : 0); i--) {
            if (chars[i] > '9' || chars[i] < '0') {
                n = i;
            }
        }

        String substring = trim.substring(m, n);
        if ("".equals(substring)) {
            return 0;
        }
        if (tagged) {
            substring = chars[0] + substring;
        }
        if (tagged && substring.length() == 1) {
            return 0;
        }
        char[] chars1 = substring.toCharArray();
        for (int i = tagged ? 1 : 0; i < chars1.length; i++) {
            if (chars1[i] > '9' || chars1[i] < '0') {
                return 0;
            }
        }
        int x = Integer.MIN_VALUE / 10;
        int y = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        for (int i = tagged ? 1 : 0; i < chars1.length; i++) {
            cur = ('0' - chars1[i]);// 每个位置用负数表示
            if (res < x || (res == x && cur < y)) {
                if (tagged && chars1[0] == '-') {
                    return Integer.MIN_VALUE;
                }
                return Integer.MAX_VALUE;
            }
            res = res * 10 + cur;
        }
        if (!(tagged && chars1[0] == '-')) {
            if (res == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
        }
        if (tagged && chars1[0] == '-') {
            return res;
        }
        return -res;


    }


}

package leetcode.hard;

import java.util.LinkedList;

//1 <= s.length <= 3 * 10^5
//s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
//s 表示一个有效的表达式
//'+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
//'-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
//输入中不存在两个连续的操作符
//每个数字和运行的计算将适合于一个有符号的 32位 整数
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/basic-calculator
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0224_BasicCalculator {
    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] f(char[] str, int i) {
        LinkedList<String> queue = new LinkedList<>();
        int curNum = 0;
        int[] bra;
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
            } else if (str[i] >= '0' && str[i] <= '9') {
                curNum = curNum * 10 + (str[i++] - '0');
            } else if (str[i] != '(') {
                // 遇到的是运算符号
                addNum(queue, curNum);
                queue.addLast(String.valueOf(str[i++]));
                curNum = 0;
            } else { // 遇到左括号了
                bra = f(str, i + 1);
                curNum = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(queue, curNum);
        return new int[]{getNum(queue), i};
    }

    public static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            int cur;
            String top = queue.pollLast();
            // 先结算
            if (top.equals("+") || top.equals("-")) {
                queue.addLast(top);
            } else {
                // 到这里了，就只可能是*和/号
                cur = Integer.parseInt(queue.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        queue.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> queue) {
        int res = 0;
        boolean add = true;
        String cur;
        int num;
        while (!queue.isEmpty()) {
            cur = queue.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.parseInt(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }
}

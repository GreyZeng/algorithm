package leetcode;

import java.util.LinkedList;

// tip 递归过程
// 压缩串问题
public class LeetCode_0772_BasicCalculatorIII {
    public static int calculate(String s) {
        return f(s.toCharArray(), 0)[0];
    }

    private static int[] f(char[] str, int i) {
        LinkedList<String> queue = new LinkedList<>();
        int c = 0;
        int[] b;
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
            } else if (str[i] >= '0' && str[i] <= '9') {
                c = c * 10 + str[i++] - '0';
            } else if (str[i] == '(') {
                b = f(str, i + 1);
                c = b[0];
                i = b[1] + 1;
            } else {
                // str[i]是运算符
                addNum(queue, c);
                queue.addLast(String.valueOf(str[i++]));
                c = 0;
            }
        }
        addNum(queue, c);

        return new int[]{getNum(queue), i};
    }

    private static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    private static void addNum(LinkedList<String> queue, int c) {
        if (!queue.isEmpty()) {
            int t = 0;
            String top = queue.pollLast();
            if ("*".equals(top)) {
                t = Integer.valueOf(queue.pollLast());
                c = c * t;
            } else if ("/".equals(top)) {
                t = Integer.valueOf(queue.pollLast());
                c = t / c;
            } else {
                queue.addLast(top);
            }
        }
        queue.addLast(String.valueOf(c));
    }
}

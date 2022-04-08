package leetcode.hard;

import java.util.Stack;

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
    public static int calculate(String s) {
        return process(s.toCharArray(), 0)[0];
    }

    // i...算出的结果，遇到右括号或者结尾才结算
    // 返回值 int[] 表示两位
    // 第0位，计算的结果
    // 第1位，计算到了什么位置
    private static int[] process(char[] str, int i) {
        Stack<String> stack = new Stack<>();
        int result = 0;
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
            } else if (str[i] >= '0' && str[i] <= '9') {
                result = result * 10 + (str[i++] - '0');
            } else if (str[i] == '(') {
                int[] b = process(str, i + 1);
                i = b[1] + 1;
                result = b[0];
            } else {
                // i是运算符，本题中，运算符只有+,-
                // 先把result放入stack里面，与stack现有的内容结算出一个结果
                cal(stack, result);
                // i位置的操作符入栈
                stack.push(String.valueOf(str[i++]));
                result = 0;
            }
        }
        cal(stack, result);
        return new int[]{cal(stack), i};
    }

    // stack里面就是单纯的不带括号的表达式了
    private static int cal(Stack<String> stack) {
        int result = 0;
        boolean isAdd = true;
        while (!stack.isEmpty()) {
            String c = stack.pop();
            if ("+".equals(c)) {
                isAdd = true;
            } else if ("-".equals(c)) {
                isAdd = false;
            } else {
                // c是数字
                int num = Integer.parseInt(c);
                result += isAdd ? num : (-num);
            }
        }
        return result;
    }

    private static void cal(Stack<String> stack, int result) {
        if (!stack.isEmpty()) {
            String c = stack.pop();
            if ("+".equals(c)) {
                result += Integer.parseInt(stack.pop());
            } else if ("-".equals(c)) {
                result = Integer.parseInt(stack.pop()) - result;
            } else {
                stack.push(c);
            }
        }
        stack.push(String.valueOf(result));
    }

}

package 练习题.leetcode.hard;

import java.util.LinkedList;

// 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号。返回公式的计算结果
// 难点在于括号可能嵌套很多层，str="48*((70-65)-43)+8*1"，返回-1816。str="3+1*4"，返回7。str="3+(1*4)"，返回7。
// 1，可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
// 2，如果是负数，就需要用括号括起来，比如"4*(-3)"但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"
// 都是合法的
// 3，不用考虑计算过程中会发生溢出的情况。
// https://leetcode.cn/problems/basic-calculator-iii/
// tip 递归过程
// 压缩串问题
// 1 <= s <= 104
// s 由整数、'+'、'-'、'*'、'/'、'(' 和 ')' 组成
// s 是一个 有效的 表达式
public class LeetCode_0772_BasicCalculatorIII {
  public static void main(String[] args) {
    String st = "(2+6*3+5-(3*14/7+2)*5)+3";
    System.out.println(calculate(st));
  }

  public static int calculate(String s) {
    return process(s.toCharArray(), 0)[0];
  }

  public static int[] process(char[] str, int i) {
    LinkedList<String> queue = new LinkedList<>();
    int num = 0;
    int[] b;
    while (i < str.length && str[i] != ')') {
      if (str[i] == ' ') {
        i++;
      } else if (str[i] >= '0' && str[i] <= '9') {
        num = num * 10 + (str[i++] - '0');
      } else if (str[i] == '(') {
        b = process(str, i + 1);
        i = b[1] + 1;
        num = b[0];
      } else {
        addNum(queue, num);
        queue.addLast(String.valueOf(str[i++]));
        num = 0;
        // str[i]是操作符
      }
    }
    addNum(queue, num);
    return new int[] {getNum(queue), i};
  }

  private static void addNum(LinkedList<String> queue, int num) {
    if (!queue.isEmpty()) {
      String s = queue.pollLast();
      if ("+".equals(s) || "-".equals(s)) {
        queue.addLast(s);
      } else {
        int v = Integer.parseInt(queue.pollLast());
        num = "*".equals(s) ? (v * num) : (v / num);
      }
    }
    queue.addLast(String.valueOf(num));
  }

  public static int getNum(LinkedList<String> queue) {
    boolean isAdd = true;
    int num = 0;
    while (!queue.isEmpty()) {
      String s = queue.pollFirst();
      if ("+".equals(s)) {
        isAdd = true;
      } else if ("-".equals(s)) {
        isAdd = false;
      } else {
        int v = Integer.parseInt(s);
        num += isAdd ? v : (-v);
      }
    }
    return num;
  }
}

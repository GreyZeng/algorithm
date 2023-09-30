package leetcode;

import java.util.LinkedList;

// The expression string contains only non-negative integers, +, -, *, / operators and empty spaces
// .
// The integer division should truncate toward zero.
// https://leetcode-cn.com/problems/basic-calculator-ii
public class LeetCode_0227_BasicCalculatorII {

  public static int calculate(String str) {
    return process(str.toCharArray(), 0)[0];
  }

  public static int[] process(char[] str, int i) {
    int[] b;
    int num = 0;
    // 必须用双端队列！！！！
    LinkedList<String> queue = new LinkedList<>();
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
        // str[i] 是+-*/
        addNum(queue, num);
        queue.addLast(String.valueOf(str[i++]));
        num = 0;
      }
    }
    addNum(queue, num);
    return new int[] {getNum(queue), i};
  }

  public static void addNum(LinkedList<String> queue, int num) {
    if (!queue.isEmpty()) {
      int cur = 0;
      String op = queue.pollLast();
      if ("+".equals(op) || "-".equals(op)) {
        queue.addLast(op);
      } else {
        cur = Integer.parseInt(queue.pollLast());
        num = "*".equals(op) ? (cur * num) : (cur / num);
      }
    }
    queue.addLast(String.valueOf(num));
  }

  public static int getNum(LinkedList<String> queue) {
    boolean isAdd = true;
    int result = 0;
    while (!queue.isEmpty()) {
      String op = queue.pollFirst();
      if ("+".equals(op)) {
        isAdd = true;
      } else if ("-".equals(op)) {
        isAdd = false;
      } else {
        int value = Integer.parseInt(op);
        result += isAdd ? value : (-value);
      }
    }
    return result;
  }
}

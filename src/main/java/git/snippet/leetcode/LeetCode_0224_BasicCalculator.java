package git.snippet.leetcode;

import java.util.Stack;

// 1 <= s.length <= 3 * 10^5
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
// s 表示一个有效的表达式
// '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
// '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
// 输入中不存在两个连续的操作符
// 每个数字和运行的计算将适合于一个有符号的 32位 整数
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/basic-calculator
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// tips:
// int[] f(i)
// 返回两个值：
// 从i往后获取到的合适的位置（终止位置或者右括号）
// 计算的结果
// 补充：如果整个字符串不包括括号，可以直接用栈来计算
// 双端队列
// LeetCode_0224_BasicCalculator.java
// LeetCode_0227_BasicCalculatorII.java
// LeetCode_0772_BasicCalculatorIII.java
// 都可以用同一套模板
// 类似的：
// 化学符号还原成元素种类
// 压缩串还原成原始串，比如：3{aab}k ==>aabaabaabk
public class LeetCode_0224_BasicCalculator {
  public static void main(String[] args) {
    String str = "1-1+1";
    int calculate = calculate(str);
    System.out.println(calculate);
  }

  public static int calculate(String str) {
    return process(str.toCharArray(), 0)[0];
  }

  public static int[] process(char[] str, int i) {
    Stack<String> stack = new Stack<>();
    int[] b;
    int num = 0;
    while (i < str.length && str[i] != ')') {
      if (str[i] == ' ') {
        i++;
      } else if (str[i] >= '0' && str[i] <= '9') {
        num = num * 10 + (str[i++] - '0');
      } else if (str[i] == '+' || str[i] == '-') {
        addNum(stack, num);
        stack.push(String.valueOf(str[i++]));
        num = 0;
      } else if (str[i] == '(') {
        b = process(str, i + 1);
        i = b[1] + 1;
        num = b[0];
      } else {
        // 根据题目意思，永远不会走到这个分支
      }
    }
    addNum(stack, num);
    return new int[] {getNum(stack), i};
  }

  public static int getNum(Stack<String> stack) {
    int result = 0;
    boolean isAdd = true;
    while (!stack.isEmpty()) {
      String pop = stack.pop();
      if ("+".equals(pop)) {
        isAdd = true;
      } else if ("-".equals(pop)) {
        isAdd = false;
      } else {
        int v = Integer.parseInt(pop);
        result += isAdd ? v : (-v);
      }
    }
    return result;
  }

  public static void addNum(Stack<String> stack, int num) {
    if (!stack.isEmpty()) {
      String op = stack.pop();
      if ("+".equals(op)) {
        num += Integer.valueOf(stack.pop());
      } else if ("-".equals(op)) {
        num = Integer.valueOf(stack.pop()) - num;
      }
    }
    stack.push(String.valueOf(num));
  }
}

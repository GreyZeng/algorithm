package leetcode.cn;

//给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
//
// 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
//
//        示例 1:
//
//        输入: s = "1^0|0|1", result = 0
//
//        输出: 2
//        解释:两种可能的括号方法是
//        1^(0|(0|1))
//        1^((0|0)|1)
//        示例 2:
//
//        输入: s = "0&0&0&1^1|0", result = 1
//
//        输出: 10
//        提示：
//
//        运算符的数量不超过 19 个
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/boolean-evaluation-lcci
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// https://leetcode-cn.com/problems/boolean-evaluation-lcci/
public class LeetCodeCN_0814_BooleanEvaluationLCCI {
    public int countEval(String s, int result) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return f(s.toCharArray(), result, 0, s.length() - 1);
    }

    // L...R范围内，得到期待值的方法数
    public static int f(char[] str, int result, int L, int R) {
        // TODO
        return -1;
    }
}

package 练习题.leetcode.cn;

// 剑指 Offer 58 - II. 左旋转字符串
// 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
//
//
//
// 示例 1：
//
// 输入: s = "abcdefg", k = 2
// 输出:"cdefgab"
// 示例 2：
//
// 输入: s = "lrloseumgh", k = 6
// 输出:"umghlrlose"
//
//
// 限制：
//
// 1 <= k < s.length <= 10000
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCodeCN_0058_LCOF {
  public String reverseLeftWords(String s, int n) {
    char[] str = s.toCharArray();
    rotate(str, 0, n - 1, s.length() - 1);
    return String.valueOf(str);
  }

  public void rotate(char[] arr, int L, int M, int R) {
    reverse(arr, L, M);
    reverse(arr, M + 1, R);
    reverse(arr, L, R);
  }

  public void reverse(char[] str, int l, int r) {
    while (l < r) {
      swap(str, l++, r--);
    }
  }

  public void swap(char[] str, int l, int r) {
    char tmp = str[l];
    str[l] = str[r];
    str[r] = tmp;
  }
}

// We are given two strings, A and B.
//
// A shift on A consists of taking string A and moving the leftmost character to the rightmost
// position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True
// if and only if A can become B after some number of shifts on A.
//
// Example 1:
// Input: A = 'abcde', B = 'cdeab'
// Output: true
//
// Example 2:
// Input: A = 'abcde', B = 'abced'
// Output: false
// Note:
//
// A and B will have length at most 100.
package 练习题.leetcode.easy;

// 使用kmp算法解：https://www.cnblogs.com/greyzeng/p/15317466.html
public class LeetCode_0796_RotateString {
  public static boolean rotateString(String A, String B) {
    if (A.length() != B.length()) {
      return false;
    }
    char[] a = A.toCharArray();
    char[] aa = new char[a.length << 1];
    for (int i = 0; i < aa.length; i++) {
      aa[i] = a[i % a.length];
    }
    char[] b = B.toCharArray();

    // b是否为aa的子串
    return indexOf(aa, b) != -1;
  }

  public static int indexOf(char[] s, char[] m) {
    if (s == null || m == null || s.length < m.length) {
      return -1;
    }
    if (m.length == 0) {
      return 0;
    }
    int[] next = getNext(m);
    int x = 0;
    int y = 0;
    while (x < s.length && y < m.length) {
      if (s[x] == m[y]) {
        x++;
        y++;
      } else if (y != 0) {
        y = next[y];
      } else {
        x++;
      }
    }
    return y == m.length ? x - y : -1;
  }

  public static int[] getNext(char[] m) {
    if (m.length == 1) {
      return new int[] {-1};
    }
    int[] next = new int[m.length];
    next[0] = -1;
    next[1] = 0;
    int i = 2;
    int cn = 0;
    while (i < next.length) {
      if (m[i - 1] == m[cn]) {
        next[i++] = ++cn;
      } else if (cn > 0) {
        cn = next[cn];
      } else {
        next[i++] = 0;
      }
    }
    return next;
  }
}

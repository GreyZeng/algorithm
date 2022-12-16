package 练习题.leetcode.easy;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/21
 * @since
 */
public class LeetCode_0231_PowerOfTwo {
  // 借鉴 Netty 代码
  public boolean isPowerOfTwo(int n) {
    return n > 0 && (n & -n) == n;
  }
}

package lintcode;

// 名人的定义：所有人都认识这个人，这个人不认识除自己以外的所有人
// leetcode加锁：https://leetcode.cn/problems/find-the-celebrity/
// lintcode : https://www.lintcode.com/problem/find-the-celebrity/description
public class LintCode_0645_FindTheCelebrity {
  // 它会告诉你A是否知道B
  public boolean knows(int a, int b) {
    return true;
  }

  public int findCelebrity(int n) {
    if (n <= 0) {
      return -1;
    }
    if (n == 1) {
      return 0;
    }
    // 默认第0号是候选人
    int cand = 0;
    for (int i = 1; i < n; i++) {
      if (knows(cand, i)) {
        cand = i;
      }
    }
    // cand是有可能的候选
    // 接下来验证所有人是否都认识cand
    for (int i = 0; i < n; i++) {
      if (i != cand && !knows(i, cand)) {
        return -1;
      }
    }
    // 接下来验证cand是否都不认识所有人
    for (int i = 0; i < n; i++) {
      if (i != cand && knows(cand, i)) {
        return -1;
      }
    }
    return cand;
  }
}

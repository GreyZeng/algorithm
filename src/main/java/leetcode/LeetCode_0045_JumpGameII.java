package leetcode;

// 给出一组非负整数arr，你从第0个数向最后一个数，
// 每个数的值表示你从这个位置可以向右跳跃的最大长度
// 计算如何以最少的跳跃次数跳到最后一个数。
// step: 目前跳了几步 ,初始值为0
// curR: 在step步内，如果不额外增加步数，右边界到哪里，初始值为0
// next: 如果再跳一步，能跳到哪里，初始值为-1
// 从左往右的尝试模型
// https://leetcode-cn.com/problems/jump-game-ii/
public class LeetCode_0045_JumpGameII {
  // 最优解
  // O(N)，时间复杂度
  // O(1)，空间复杂度
  public int jump(int[] arr) {
    int maxPos = 0;
    int step = 0;
    int endIndex = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      maxPos = Math.max(arr[i] + i, maxPos);
      if (endIndex == i) {
        endIndex = maxPos;
        step++;
      }
    }
    return step;
  }

  // 从左往右的尝试模型
  // O(N^2)时间复杂度
  public static int jump2(int[] arr) {
    if (arr.length == 0) {
      return 0;
    }
    if (arr.length == 1) {
      return 0;
    }
    if (arr.length == 2) {
      return arr[0] == 0 ? Integer.MAX_VALUE : 1;
    }
    // dp[i]表示：从i开始，跳到最后，最少步数是什么。
    int[] dp = new int[arr.length];
    // dp[arr.length-1] = 0; // 最后一个位置，跳0步就可以，数组不赋值默认就是0
    // 倒数第二个位置，如果arr[arr.length-2]=0，则永远无法跳到最后一个位置，如果arr[arr.length-2]>=1，则只需要一步就可以跳到最后
    dp[arr.length - 2] = arr[arr.length - 2] >= 1 ? 1 : Integer.MAX_VALUE;
    for (int i = arr.length - 3; i >= 0; i--) {
      int steps = arr[i];
      dp[i] = (steps == 0 || dp[i + 1] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (dp[i + 1] + 1);
      // 以下的枚举行为，如何优化？？？？
      for (int step = Math.min(steps, arr.length - i - 1); step > 0; step--) {
        dp[i] =
            Math.min(
                (dp[i + step] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (dp[i + step] + 1),
                dp[i]);
      }
    }
    return dp[0];
  }
}

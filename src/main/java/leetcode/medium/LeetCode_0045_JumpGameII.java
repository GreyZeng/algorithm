package leetcode.medium;


// 给出一组正整数arr，你从第0个数向最后一个数，
// 每个数的值表示你从这个位置可以向右跳跃的最大长度
// 计算如何以最少的跳跃次数跳到最后一个数。
// step: 目前跳了几步 ,初始值为0
// curR: 在step步内，如果不额外增加步数，右边界到哪里，初始值为0
// next: 如果再跳一步，能跳到哪里，初始值为-1
// 从左往右的尝试模型
// https://leetcode-cn.com/problems/jump-game-ii/
public class LeetCode_0045_JumpGameII {

    public static int jump(int[] arr) {
        if (null == arr) {
            return 0;
        }
        int N = arr.length;
        if (N == 0 || N == 1) {
            return 0;
        }
        int step = 0; // 当前最少步数跳到i
        int cur = 0; // 跳的步数不超过step，最右位置
        int next = arr[0]; // 跳的步数不超过step+1，最右位置
        for (int i = 0; i < N; i++) {
            // 当前步数不超过step，最右的位置不足到i
            if (cur < i) {
                // 更新step的步数
                step++;
                // cur到step+1能走到的最右位置
                cur = next;
            }
            // 更新next到下一步能走到的最右位置
            next = Math.max(next, arr[i] + i);
            if (next >= N - 1) {
                return step + 1;
            }
        }
        return step;
    }
}

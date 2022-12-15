
package 练习题.leetcode.medium;


import java.util.ArrayDeque;
import java.util.Deque;

//请根据每日 气温 列表 temperatures，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用0 来代替。
//        示例 1:
//        输入: temperatures = [73,74,75,71,69,72,76,73]
//        输出:[1,1,4,2,1,1,0,0]
//        示例 2:
//        输入: temperatures = [30,40,50,60]
//        输出:[1,1,1,0]
//        示例 3:
//        输入: temperatures = [30,60,90]
//        输出: [1,1,0]
//        提示：
//        1 <=temperatures.length <= 10^5
//        30 <=temperatures[i]<= 100
//        Leetcode题目 : https://leetcode.cn/problems/daily-temperatures/
// tips: 单调栈
// 笔记：https://www.cnblogs.com/greyzeng/p/16326526.html
public class LeetCode_0739_DailyTemperatures {
    // 一个数右边离它最近的比它大的数是什么
    public static int[] dailyTemperatures(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        int n = arr.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int popIndex = stack.pop();
                ans[popIndex] = i - popIndex;
            }
            stack.push(i);
        }
        // 不需要弹，因为本身初始化就是0，而且栈中的元素本身就没有右边离它最近的比它大的数
        return ans;
    }

}

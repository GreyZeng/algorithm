// 描述

// 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。

// 由于答案可能很大，因此模 10^9 + 7以后返回答案。

//     1≤A≤300001 \leq A \leq 300001≤A≤30000
//     1≤A[i]≤300001 \leq A[i] \leq 300001≤A[i]≤30000

// 样例

// 样例 1:

// 输入：[3,1,2,4]
// 输出：17
// 解释：
// 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
// 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。

// 样例 2:

// 输入：[95,58,46,67,100]
// 输出：859
// 解释：
// 子数组为 [95], [58], [46], [67], [100], [98,58], [58,46], [46,67], [67,100], [95,58,67],[46,67,100],[95,58,46,67],[58,46,67,100],[95,58,46,67,100]。 
// 最小值为 95, 58, 46, 67, 100, 58, 46, 46, 67, 46, 46, 46, 46, 46, 46，和为 859。
package lintcode;

import java.util.Stack;

public class LintCode_1734_SumOfSubarrayMinimums {
    private static int MOD = 1000_000_007;

    public int sumSubarrayMins(int[] arr) { 
        // 子数组必定以某个值为最小值的时候，子数组有多少个 ，假设 n...m 范围内 必须包括最小值i位置数的子数组有：（i - n)*(m - i) 个，记住结论。。。。
        // 如何获取最小值必须是某个数的子数组区间呢？可以用单调栈，单调栈可以把当前数i左右两边离自己最近的两个比自己小的数抓取到
        // 因为求子数组的最小累加和，所以相等的情况和大于的情况一样处理
 
        int n = arr.length;
        long min = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int index = stack.pop();
                int m = stack.isEmpty() ? -1 : stack.peek();
                min += (index - m ) * (i - index) *arr[index];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
           int index =  stack.pop();
           int m = stack.isEmpty() ? -1 : stack.peek();
            min += (index -  m) * (n  - index) *arr[index];
        }
        return (int) (min%MOD);
    }

 
  
}

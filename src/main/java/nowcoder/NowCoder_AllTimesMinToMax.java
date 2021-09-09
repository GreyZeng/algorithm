//链接：https://www.nowcoder.com/questionTerminal/e6e57ef2771541dfa2f1720e50bebc9a
//来源：牛客网
//
//给定一个数组序列, 需要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：
//
//区间中的最小数 * 区间所有数的和最后程序输出经过计算后的最大值即可，不需要输出具体的区间。如给定序列  [6 2 1]则根据上述公式, 可得到所有可以选定各个区间的计算值:
//
// 
//
//[6] = 6 * 6 = 36;
//
//[2] = 2 * 2 = 4;
//
//[1] = 1 * 1 = 1;
//
//[6,2] = 2 * 8 = 16;
//
//[2,1] = 1 * 3 = 3;
//
//[6, 2, 1] = 1 * 9 = 9;
//
// 
//
//从上述计算可见选定区间 [6] ，计算值为 36， 则程序输出为 36。
//
//区间内的所有数字都在[0, 100]的范围内;
//
//输入描述:
//
//第一行输入数组序列长度n，第二行输入数组序列。
//对于 50%的数据,  1 <= n <= 10000;
//对于 100%的数据, 1 <= n <= 500000;
//
//
//
//输出描述:
//
//输出数组经过计算后的最大值。
//
//示例1
//输入
//
//3
//6 2 1
//
//输出
//
//36
package nowcoder;

import java.util.Scanner;
import java.util.Stack;

public class NowCoder_AllTimesMinToMax {
    // 前缀和数组 preSum
    // i == 0 ：sum[i..j] = preSum[j]
    // i > 0 情况下：
    // sum[i...j] = preSum[j] - preSum[i-1]
    // 遍历arr数组，必须以i作为最小值的情况下，收集所有答案，结果必在其中
    // i 找左右两边离它最近的比它小的元素位置（单调栈）
    public static int max(int[] arr) {
        int[] preSum = preSum(arr);
        int n = arr.length;
        int max = arr[0] * arr[0];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int index = stack.pop();
                // i为index右边离index最近的且比index小的数的位置
                // m为index左边离index最近的且比index小的数的位置
                //System.out.println(i + "为" + arr[index] + "右边离index最近的且比index小的数的位置");
                //System.out.println(m + "为" + arr[index] + "右边离index最近的且比index小的数的位置");
                max = Math.max(max, arr[index] * (preSum[i - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            max = Math.max(max, arr[pop] * (preSum[n - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])));
        }
        return max;
    }

    public static int[] preSum(int[] arr) {
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = arr[i] + preSum[i - 1];
        }
        return preSum;
    }

    public static void main(String[] args) {
//        int[] n = {81, 87, 47, 59, 81, 18, 25, 40, 56, 0};
//        System.out.println(max(n));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(max(arr));
        in.close();
    }
}

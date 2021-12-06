//链接：https://www.nowcoder.com/questionTerminal/2a2c00e7a88a498693568cef63a4b7bb
//        来源：牛客网
//
//        给定一个可能含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置。返回所有位置相应的信息。
//
//        输入描述:
//        第一行输入一个数字 n，表示数组 arr 的长度。
//        以下一行输入 n 个数字，表示数组的值
//
//
//        输出描述:
//        输出n行，每行两个数字 L 和 R，如果不存在，则值为 -1，下标从 0 开始。
//        示例1
//        输入
//        7
//        3 4 1 5 6 2 7
//        输出
//        -1 2
//        0 2
//        -1 -1
//        2 5
//        3 5
//        2 -1
//        5 -1
//
//        备注:
// 1<=n<=1000000
//-1000000 <=arr[i] <=1000000
package nowcoder;

import java.util.*;

/**
 * 单调栈
 * 左边右边离它最近比它小的数 O（N）
 * 数组中有重复 Stack<List<Integer>>
 * 数组中无重复 Stack<Integer>
 * 栈底到栈顶从小到大
 * 弹出的时候，假设弹出的值是A，那么让它弹出的值就是它右边离它最近的最小值
 * 原先A压的是谁，那么谁就是A左边离它最近的最小值
 */
public class NowCoder_MonoStackII {
    // arr[i] 中有重复值
    // int[i][0] -- > 左边离i位置最近的比i小的数的位置
    // int[i][1] -- > 右边离i位置最近的比i小的数的位置
    public static int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int n = arr.length;
        int[][] result = new int[n][2];
        if (arr.length == 1) {
            // 只有一个元素
            result[0][0] = -1;
            result[0][1] = -1;
            return result;
        }
        // 栈顶到栈底从大到小
        Deque<List<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                // 栈空，直接进入
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            } else if (arr[stack.peek().get(0)] < arr[i]) {
                // 走到这个分支，说明栈不为空
                // 如果栈顶元素对应的arr值比当前值arr[i]小
                // arr[i]进栈（另外启一个List)
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            } else if (arr[stack.peek().get(0)] == arr[i]) {
                // 处理重复元素的逻辑
                stack.peek().add(i);
            } else {
                if (arr[stack.peek().get(0)] > arr[i]) {
                    // 如果栈顶元素对应的arr值比当前值arr[i]大
                    // 则走弹出元素的逻辑：
                    // 弹出位置（一个List）中的所有元素右边离它最近的比它小的数就是i
                    // List下面压着的List中最右边的元素就是左边离它最近的比它小的数。
                    List<Integer> pop = stack.pop();
                    for (int index : pop) {
                        result[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                        result[index][1] = i;
                    }
                    // i--以后循环会跑i++，其实就是下轮循环再次判断arr[i]位置的值和栈顶元素的关系
                    i--;
                }
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            for (Integer num : pop) {
                result[num][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                result[num][1] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }
        int[][] r = getNearLess(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(r[i][0]).append(" ").append(r[i][1]).append("\n");
        }
        System.out.println(sb);
        in.close();
        // int[] arr = { 3, 4, 1, 5, 6, 2, 7 };
        // int[][] result = getNearLess(arr);
        // for (int i = 0; i < result.length; i++) {
        //     System.out.println(result[i][0] + " == " + result[i][1]);
        // }
    }

}


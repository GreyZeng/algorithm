//链接：https://www.nowcoder.com/questionTerminal/e3d18ffab9c543da8704ede8da578b55
//        来源：牛客网
//
//        给定一个不含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置。返回所有位置相应的信息。
//
//
//        输入描述:
//        第一行输入一个数字 n，表示数组 arr 的长度。
//
//        以下一行输出 n个数字，表示数组的值。
//
//
//        输出描述:
//        输出n行，每行两个数字 L 和 R，如果不存在，则值为-1，下标从0开始。
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
//

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
public class NowCoder_MonoStack {
    // 无重复值
    public static int[][] getNearLess(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int N = arr.length;
        int[][] r = new int[N][2];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int v = stack.pop();
                r[v][0] = stack.isEmpty() ? -1 : stack.peek();
                r[v][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int v = stack.pop();
            r[v][0] = stack.isEmpty() ? -1 : stack.peek();
            r[v][1] = -1;
        }
        return r;
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
    }

}

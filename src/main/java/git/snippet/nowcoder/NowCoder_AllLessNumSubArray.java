// 链接：https://www.nowcoder.com/questionTerminal/5fe02eb175974e18b9a546812a17428e
// 来源：牛客网
//
// 给定数组 arr 和整数 num，共返回有多少个子数组满足如下情况：
// max(arr[i...j]) - min(arr[i...j]) <= num
// max(arr[i...j])表示子数组arr[i...j]中的最大值，min[arr[i...j])表示子数组arr[i...j]中的最小值。
package git.snippet.nowcoder;

import java.util.LinkedList;
import java.util.Scanner;

// 笔记：https://www.cnblogs.com/greyzeng/p/16966417.html
// arr[L..R]达标，则arr中内部的任何一个子数组都达标
// arr[L..R]不达标，则arr扩充后肯定也不达标
// L...R 范围如果达标，其子数组个数为：R - L
public class NowCoder_AllLessNumSubArray {
    // 必须以l位置作为左边界的情况下，有多少达标的数组
    public static int getNum(int[] arr, int num) {
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();
        int ans = 0;
        int l = 0;
        int r = 0;
        while (l < arr.length) {
            while (r < arr.length) {
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[r]) {
                    qMax.pollLast();
                }
                qMax.addLast(r);
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[r]) {
                    qMin.pollLast();
                }
                qMin.addLast(r);
                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
                    break;
                }
                r++;
            }
            // r是以l作为左边界，第一个不满足条件的位置
            ans += (r - l);
            // 弹出过期位置
            if (!qMax.isEmpty() && qMax.peekFirst() == l) {
                qMax.pollFirst();
            }
            // 弹出过期位置
            if (!qMin.isEmpty() && qMin.peekFirst() == l) {
                qMin.pollFirst();
            }
            l++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(getNum(arr, m));
        in.close();
    }
}

//链接：https://www.nowcoder.com/questionTerminal/5fe02eb175974e18b9a546812a17428e
//        来源：牛客网
//
//        给定数组 arr 和整数 num，共返回有多少个子数组满足如下情况：
//        max(arr[i...j]) - min(arr[i...j]) <= num
//        max(arr[i...j])表示子数组arr[i...j]中的最大值，min[arr[i...j])表示子数组arr[i...j]中的最小值。
//
//
//        输入描述:
//
//        第一行输入两个数 n 和 num，其中 n 表示数组 arr 的长度
//        第二行输入n个整数XiX_iXi​，表示数组arr中的每个元素
//
//
//
//        输出描述:
//
//        输出给定数组中满足条件的子数组个数
//
//        示例1
//        输入
//
//        5 2
//        1 2 3 4 5
//
//        输出
//
//        12
//
//
//        备注:
//
//        1≤n≤1000000
//        −1000000≤arri≤1000000
//        0≤num≤20000000
package snippet;

import java.util.LinkedList;

// arr[L..R]达标，则arr中内部的任何一个子数组都达标
// arr[L..R]不达标，则arr扩充后肯定也不达标
// L...R 范围如果达标，其子数组个数为：
public class Code_0087_AllLessNumSubArray {
    public static int getNum(int[] arr, int num) {
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();
        int l = 0;
        int r = 0;
        int count = 0;
        int len = arr.length;
        while (l < len) {
            while (r < len) {
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
            // l是满足条件的位置，r是不满足条件的第一个位置
            // 必须以l开头的满足条件的子数组个数为： r - 1 - (l - 1) = r - l
            count += (r - l);
            if (qMax.peekFirst() == l) {
                qMax.pollFirst();
            }
            if (qMin.peekFirst() == l) {
                qMin.pollFirst();
            }
            l++;
        }
        return count;
    }

    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }


    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = getNum(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }


}

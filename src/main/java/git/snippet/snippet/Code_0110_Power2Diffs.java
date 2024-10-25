package git.snippet.snippet;

import java.util.Arrays;
import java.util.HashSet;

/*
 * 给定一个有序数组arr，其中值可能为正、负、0。 返回arr中每个数都平方之后不同的结果有多少种？
 *
 * 给定一个数组arr，先递减然后递增，返回arr中有多少个绝对值不同的数字？
 *
 */
// 类似@LeetCode_0977_SquaresOfASortedArray
public class Code_0110_Power2Diffs {

    // 时间复杂度O(N)，额外空间复杂度O(N)
    public static int diff1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int cur : arr) {
            set.add(cur * cur);
        }
        return set.size();
    }

    // 时间复杂度O(N)，额外空间复杂度O(1)
    public static int diff2(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        int count = 0;
        while (i <= j) {
            count++;
            int v1 = Math.abs(arr[i]);
            int v2 = Math.abs(arr[j]);
            if (v1 < v2) {
                // j一直左移
                while (j >= i && v2 == Math.abs(arr[j])) {
                    j--;
                }
            } else if (v1 > v2) {
                while (j >= i && v1 == Math.abs(arr[i])) {
                    i++;
                }
            } else {
                // i和j同时移动
                while (j >= i && v1 == Math.abs(arr[i])) {
                    i++;
                }
                while (j >= i && v2 == Math.abs(arr[j])) {
                    j--;
                }
            }
        }
        return count;
    }

    // for test
    public static int[] randomSortedArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        Arrays.sort(ans);
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int cur : arr) {
            System.out.print(cur + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 100;
        int value = 500;
        int testTimes = 200000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomSortedArray(len, value);
            int ans1 = diff1(arr);
            int ans2 = diff2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}

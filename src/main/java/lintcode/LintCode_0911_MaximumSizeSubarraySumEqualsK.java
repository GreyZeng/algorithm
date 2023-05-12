package lintcode;

import java.util.HashMap;
import java.util.Map;

// https://www.lintcode.com/problem/911/
// 笔记：https://www.cnblogs.com/greyzeng/p/16701589.html
public class LintCode_0911_MaximumSizeSubarraySumEqualsK {
    public static int maxSubArrayLen(int[] arr, int k) {
        if (arr == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // 期待map里面有sum - k的记录
            if (map.containsKey(sum - k)) {
                ans = Math.max(ans, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }

    // for test
    public static int right(int[] arr, int k) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, k)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generateRandomArray(int size, int value) {
        int[] ans = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;

        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int ans1 = maxSubArrayLen(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }

}

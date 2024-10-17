package git.snippet.snippet;

import java.util.HashMap;
import java.util.Map;

// arr是货币数组，其中的值都是正数。再给定一个正数aim。每个值都认为是一张货币，认为值相同的货币没有任何不同，返回组成aim的方法数
// 例如：arr = {1,2,1,1,2,1,2}，aim = 4 方法：1+1+1+1、1+1+2、2+2 一共就3种方法，所以返回3
public class Code_0052_CoinsWaySameValueSamePaper {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        // 统计每一张纸币出现的张数
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int key : arr) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        int size = map.size();
        int[] coins = new int[size];
        int[] zhangs = new int[coins.length];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[--size] = entry.getKey();
            zhangs[size] = entry.getValue();
        }
        return process(0, coins, zhangs, aim);
    }

    // i号硬币及其往后所有硬币自由组合，组成aim的方法数
    public static int process(int i, int[] coins, int[] zhangs, int rest) {
        if (rest == 0) {
            return 1;
        }
        // aim不为0
        if (i == coins.length) {
            return 0;
        }
        // aim不为0，且i没到头
        // 不选i号
        int ways = 0;
        for (int index = 0; index <= zhangs[i] && coins[i] * index <= rest; index++) {
            // 必须选0张i位置的
            ways += process(i + 1, coins, zhangs, rest - coins[i] * index);
        }
        return ways;
    }

    public static int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        // 统计每一张纸币出现的张数
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int key : arr) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        int size = map.size();
        int[] coins = new int[size];
        int[] zhangs = new int[coins.length];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[--size] = entry.getKey();
            zhangs[size] = entry.getValue();
        }
        int[][] dp = new int[coins.length + 1][aim + 1];

        // 第一列
        for (int i = 0; i < coins.length + 1; i++) {
            dp[i][0] = 1;
        }
        // 最后一行
        // for (int i = 1; i < aim + 1; i++) {
        // dp[coins.length][i] = 0;
        // }
        // 不选i号
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = aim; j >= 0; j--) {
                int ways = 0;
                // 枚举行为
                for (int index = 0; index <= zhangs[i] && coins[i] * index <= j; index++) {
                    // 必须选0张i位置的
                    ways += dp[i + 1][j - coins[i] * index];
                }
                dp[i][j] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int coinsWay3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        // 统计每一张纸币出现的张数
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int key : arr) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        int size = map.size();
        int[] coins = new int[size];
        int[] zhangs = new int[coins.length];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[--size] = entry.getKey();
            zhangs[size] = entry.getValue();
        }
        int[][] dp = new int[coins.length + 1][aim + 1];

        // 第一列
        for (int i = 0; i < coins.length + 1; i++) {
            dp[i][0] = 1;
        }
        // 最后一行
        // for (int i = 1; i < aim + 1; i++) {
        // dp[coins.length][i] = 0;
        // }
        // 不选i号
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j] + (j >= coins[i] ? dp[i][j - coins[i]] : 0);
                dp[i][j] -=
                        (j - coins[i] * (zhangs[i] + 1) >= 0 ? dp[i + 1][j - coins[i] * (zhangs[i] + 1)] : 0);
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = coinsWay2(arr, aim);
            int ans3 = coinsWay3(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

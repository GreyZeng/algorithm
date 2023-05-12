package snippet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

// arr是货币数组，其中的值都是正数。再给定一个正数aim。每个值都认为是一张货币，返回组成aim的最少货币数
// 注意：
// 因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
// 张数不压缩的话，是O(N*aim)复杂度
// O(M*aim) M面值类型
// 动态规划+滑动窗口
public class Code_0053_MinCoinsOnePaper {
    public static int minCoins(int[] arr, int aim) {
        return p(arr, 0, aim);
    }

    // 从i位置一直到最后，组成rest的最少货币数
    public static int p(int[] arr, int i, int rest) {
        if (rest == 0) {
            return 0;
        }
        // 没有货币了，rest又不为0
        if (i == arr.length) {
            return Integer.MAX_VALUE;
        }
        // 有货币，rest也不为0
        // 不选i位置
        int p1 = p(arr, i + 1, rest);
        // 选i位置，有条件
        if (rest - arr[i] >= 0) {
            int p2 = p(arr, i + 1, rest - arr[i]);
            if (p2 != Integer.MAX_VALUE) {
                p2++;
            }
            return Math.min(p1, p2);
        }
        return p1;
    }

    // dp1时间复杂度为：O(arr长度 * aim)
    public static int dp1(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int j = 1; j < aim + 1; j++) {
            dp[arr.length][j] = Integer.MAX_VALUE;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    int p2 = dp[i + 1][j - arr[i]];
                    if (p2 != Integer.MAX_VALUE) {
                        p2++;
                    }
                    dp[i][j] = Math.min(dp[i][j], p2);
                }
            }
        }
        return dp[0][aim];
    }

    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    // dp2时间复杂度为：O(arr长度) + O(货币种数 * aim * 每种货币的平均张数)
    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        // 去重
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int[] zhangs = new int[map.size()];
        int[] coins = new int[map.size()];
        int index = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            zhangs[index] = entry.getValue();
            coins[index] = entry.getKey();
            index++;
        }
        int[][] dp = new int[coins.length + 1][aim + 1];
        for (int i = 1; i < aim + 1; i++) {
            dp[coins.length][i] = Integer.MAX_VALUE;
        }
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                for (int zhang = 1; zhang <= zhangs[i] && zhang * coins[i] <= j; zhang++) {
                    if (dp[i + 1][j - zhang * coins[i]] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], zhang + dp[i + 1][j - zhang * coins[i]]);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    // dp3时间复杂度为：O(arr长度) + O(货币种数 * aim)
    // 优化需要用到窗口内最小值的更新结构
    public static int dp3(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        // 得到info时间复杂度O(arr长度)
        Info info = getInfo(arr);
        int[] c = info.coins;
        int[] z = info.zhangs;
        int N = c.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 虽然是嵌套了很多循环，但是时间复杂度为O(货币种数 * aim)
        // 因为用了窗口内最小值的更新结构
        for (int i = N - 1; i >= 0; i--) {
            for (int mod = 0; mod < Math.min(aim + 1, c[i]); mod++) {
                // 当前面值 X
                // mod mod + x mod + 2*x mod + 3 * x
                LinkedList<Integer> w = new LinkedList<>();
                w.add(mod);
                dp[i][mod] = dp[i + 1][mod];
                for (int r = mod + c[i]; r <= aim; r += c[i]) {
                    while (!w.isEmpty() && (dp[i + 1][w.peekLast()] == Integer.MAX_VALUE
                            || dp[i + 1][w.peekLast()] + compensate(w.peekLast(), r, c[i]) >= dp[i + 1][r])) {
                        w.pollLast();
                    }
                    w.addLast(r);
                    int overdue = r - c[i] * (z[i] + 1);
                    if (w.peekFirst() == overdue) {
                        w.pollFirst();
                    }
                    dp[i][r] = dp[i + 1][w.peekFirst()] + compensate(w.peekFirst(), r, c[i]);
                }
            }
        }
        return dp[0][aim];
    }

    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }

    // 为了测试
    public static int[] randomArray(int N, int maxValue) {
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
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            int ans4 = dp3(arr, aim);
            if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("==========");

        int aim = 0;
        int[] arr = null;
        long start;
        long end;
        int ans2;
        int ans3;

        System.out.println("性能测试开始");
        maxLen = 30000;
        maxValue = 20;
        aim = 60000;
        arr = randomArray(maxLen, maxValue);

        start = System.currentTimeMillis();
        ans2 = dp2(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp2答案 : " + ans2 + ", dp2运行时间 : " + (end - start) + " ms");

        start = System.currentTimeMillis();
        ans3 = dp3(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp3答案 : " + ans3 + ", dp3运行时间 : " + (end - start) + " ms");
        System.out.println("性能测试结束");

        System.out.println("===========");

        System.out.println("货币大量重复出现情况下，");
        System.out.println("大数据量测试dp3开始");
        maxLen = 20000000;
        aim = 10000;
        maxValue = 10000;
        arr = randomArray(maxLen, maxValue);
        start = System.currentTimeMillis();
        ans3 = dp3(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp3运行时间 : " + (end - start) + " ms");
        System.out.println("大数据量测试dp3结束");

        System.out.println("===========");
        System.out.println("货币大量重复出现情况下，");
        System.out.println("大数据量测试dp2开始");
        maxLen = 20000000;
        aim = 10000;
        maxValue = 10000;
        arr = randomArray(maxLen, maxValue);
        start = System.currentTimeMillis();
        ans3 = dp2(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp2运行时间 : " + (end - start) + " ms");
        System.out.println("大数据量测试dp2结束");

        System.out.println("===========");
        System.out.println("当货币很少出现重复，dp2比dp3有常数时间优势");
        System.out.println("当货币大量出现重复，dp3时间复杂度明显优于dp2");
        System.out.println("dp3的优化用到了窗口内最小值的更新结构");
    }

}

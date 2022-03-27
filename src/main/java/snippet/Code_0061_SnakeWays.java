package snippet;

import java.util.Map;
import java.util.TreeMap;

// 牛客测评链接
// https://www.nowcoder.com/questionTerminal/d94bb2fa461d42bcb4c0f2b94f5d4281
public class Code_0061_SnakeWays {
    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int w = in.nextInt();
//        int[] v = new int[n];
//        long sum = 0;
//        for (int i = 0; i < n; i++) {
//            v[i] = in.nextInt();
//            sum += v[i];
//        }
//        if (sum <= w) {
//            System.out.println((long) Math.pow(2, n));
//        } else {
//            System.out.println(ways0(v, w));
//        }
//
//        in.close();
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            long ans1 = ways0(arr, aim);
            long ans2 = ways1(arr, aim);
            long ans3 = ways3(arr, aim);
            long ans4 = ways2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
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
        System.out.println("测试结束");
    }

    // 分治方式
    public static long ways0(int[] arr, int bag) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] <= bag ? 2 : 1;
        }
        int mid = (arr.length - 1) >> 1;
        TreeMap<Long, Long> lmap = new TreeMap<>();
        long ways = process0(arr, 0, 0, mid, bag, lmap);
        TreeMap<Long, Long> rmap = new TreeMap<>();
        ways += process0(arr, mid + 1, 0, arr.length - 1, bag, rmap);
        TreeMap<Long, Long> rpre = new TreeMap<>();
        long pre = 0;
        for (Map.Entry<Long, Long> entry : rmap.entrySet()) {
            pre += entry.getValue();
            rpre.put(entry.getKey(), pre);
        }
        for (Map.Entry<Long, Long> entry : lmap.entrySet()) {
            long lweight = entry.getKey();
            long lways = entry.getValue();
            Long floor = rpre.floorKey(bag - lweight);
            if (floor != null) {
                long rways = rpre.get(floor);
                ways += lways * rways;
            }
        }
        return ways + 1;
    }


    public static long process0(int[] arr, int index, long w, int end, int bag, TreeMap<Long, Long> map) {
        if (w > bag) {
            return 0;
        }
        if (index > end) {
            if (w != 0) {
                if (!map.containsKey(w)) {
                    map.put(w, 1L);
                } else {
                    map.put(w, map.get(w) + 1);
                }
                return 1;
            } else {
                return 0;
            }
        } else {
            long ways = process0(arr, index + 1, w, end, bag, map);
            ways += process0(arr, index + 1, w + arr[index], end, bag, map);
            return ways;
        }
    }

    public static long ways1(int[] arr, int w) {
        return process(arr, 0, w);
    }


    public static long process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 1;
        }
        long next1 = process(arr, index + 1, rest);
        if (rest - arr[index] >= 0) {
            return next1 + process(arr, index + 1, rest - arr[index]);
        }
        return next1;
    }

    public static long ways2(int[] arr, int w) {
        int N = arr.length;
        long[][] dp = new long[N + 1][w + 1];
        for (int j = 0; j <= w; j++) {
            dp[N][j] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i + 1][j] + ((j - arr[i] >= 0) ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][w];
    }

    public static long ways3(int[] arr, int w) {
        int N = arr.length;
        long[][] dp = new long[N][w + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= w) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= w; j++) {
                dp[i][j] = dp[i - 1][j] + ((j - arr[i]) >= 0 ? dp[i - 1][j - arr[i]] : 0);
            }
        }
        long ans = 0;
        for (int j = 0; j <= w; j++) {
            ans += dp[N - 1][j];
        }
        return ans;
    }
}

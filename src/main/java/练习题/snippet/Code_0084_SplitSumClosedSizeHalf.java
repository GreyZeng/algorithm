package 练习题.snippet;

//给定一个正数数组arr，请把arr中所有的数分成两个集合 如果arr长度为偶数，两个集合包含数的个数要一样多 如果arr长度为奇数，
//       两个集合包含数的个数必须只差一个 请尽量让两个集合的累加和接近 返回： 最接近的情况下，较小集合的累加和
//
//       tips: 偶数个的时候，比如8，考虑4个的情况 奇数个的时候，比如7，要考虑4个和3个的情况
public class Code_0084_SplitSumClosedSizeHalf {

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            int[] newArr = new int[arr.length + 1];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            return process(newArr, 0, newArr.length / 2, sum / 2);
        }
    }

    // 一定要选picks个
    public static int process(int[] arr, int i, int picks, int rest) {
        if (i == arr.length) {
            return picks == 0 ? 0 : -1;
        }
        // 不选当前这个
        int p1 = process(arr, i + 1, picks, rest);
        // 选当前这个
        int p2 = -1;
        int next = -1;
        if (rest >= arr[i]) {
            next = process(arr, i + 1, picks - 1, rest - arr[i]);
        }
        if (next != -1) {
            p2 = next + arr[i];
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int[][][] dp = new int[arr.length + 1][arr.length / 2 + 1 + 1][sum / 2 + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    if (i == arr.length && j != 0) {
                        dp[i][j][k] = -1;
                    }
                }
            }
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 1; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    int p1 = dp[i + 1][j][k];
                    // 选当前这个
                    int p2 = -1;
                    int next = -1;
                    if (k >= arr[i]) {
                        next = dp[i + 1][j - 1][k - arr[i]];
                    }
                    if (next != -1) {
                        p2 = next + arr[i];
                    }
                    dp[i][j][k] = Math.max(p1, p2);
                }
            }
        }


        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][sum / 2];
        } else {
            return Math.max(dp[0][arr.length / 2][sum / 2], dp[0][(arr.length / 2) + 1][sum / 2]);
        }
    }


    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

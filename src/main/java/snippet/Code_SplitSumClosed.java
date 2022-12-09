package snippet;

// 笔记：https://www.cnblogs.com/greyzeng/p/16970094.html
// 给定一个正数数组arr， 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
// 返回： 最接近的情况下，较小集合的累加和
public class Code_SplitSumClosed {
    public static int splitSumClosed(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int aim = sum / 2;
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return 0;
        }
        int p1 = process(arr, i + 1, rest);
        if (rest - arr[i] >= 0) {
            p1 = Math.max(process(arr, i + 1, rest - arr[i]) + arr[i], p1);
        }
        return p1;
    }

    public static int splitSumClosed2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int aim = sum / 2;
        int[][] dp = new int[arr.length + 1][aim + 1];
        // last row == 0
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                int p1 = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    p1 = Math.max(dp[i + 1][j - arr[i]] + arr[i], p1);
                }
                dp[i][j] = p1;
            }
        }
        return dp[0][aim];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = splitSumClosed(arr);
            int ans2 = splitSumClosed2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

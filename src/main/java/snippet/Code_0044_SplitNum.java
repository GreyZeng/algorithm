/*给定一个正数1，裂开的方法有一种，(1) 给定一个正数2，裂开的方法有两种，(1和1)、(2) 给定一个正数3，裂开的方法有三种，(1、1、1)、(1、2)、(3) 给定一个正数4，
		裂开的方法有五种，(1、1、1、1)、(1、1、2)、(1、3)、(2、2)、 (4)
		给定一个正数n，求裂开的方法数。 动态规划优化状态依赖的技巧
		f(int pre, int rest)

		可以优化枚举行为（前面有：分硬币方法，股票问题3）*/
package snippet;

public class Code_0044_SplitNum {
    public static int splitNum(int n) {
        return process(1, n);
    }

    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }


    public static int split2(int n) {
        // 行pre
        // 列rest
        // dp[0][...] 弃而不用
        int[][] dp = new int[n + 1][n + 1];
        // dp[...][0] = 0 第0列为1
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            // 对角线都是1(因为对角线表示pre的值和rest相等，由于需要递增，所以只有一种方式）
            // dp[i][i] = 1;
        }
        // 左下半区全为0
        for (int pre = n; pre >= 1; pre--) {
            for (int rest = pre; rest <= n; rest++) {
                for (int i = pre; i <= rest; i++) {
                    dp[pre][rest] += dp[i][rest - i];
                }
            }
        }
        return dp[1][n];
    }


    public static int split3(int n) {
        // 行pre
        // 列rest
        // dp[0][...] 弃而不用
        int[][] dp = new int[n + 1][n + 1];
        // dp[...][0] = 0 第0列为1
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            // 对角线都是1(因为对角线表示pre的值和rest相等，由于需要递增，所以只有一种方式）
            dp[i][i] = 1;
        }
        // 左下半区全为0
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
//                dp[pre][rest] += dp[pre][rest - pre];
//                dp[pre][rest] += dp[pre + 1][rest - pre - 1];
//                dp[pre][rest] += dp[pre + 2][rest - pre - 2];
//                dp[pre][rest] += dp[pre + 3][rest - pre - 3];
//                dp[pre][rest] += dp[pre + 4][rest - pre - 4];
//                dp[pre][rest] += dp[pre + 5][rest - pre - 5];


//                dp[pre + 1][rest] += dp[pre + 1][rest - pre - 1];
//                dp[pre + 1][rest] += dp[pre + 2][rest - pre - 2];
//                dp[pre + 1][rest] += dp[pre + 3][rest - pre - 3];
//                dp[pre + 1][rest] += dp[pre + 4][rest - pre - 4];
//                dp[pre + 1][rest] += dp[pre + 5][rest - pre - 5];
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int value = 100;
        System.out.println("begin");
        for (int i = 1; i < value; i++) {
            int ans1 = splitNum(i);
            int ans2 = split2(i);
            int ans3 = split3(i);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!!!");
                break;
            }
        }
        System.out.println("end");
    }


}

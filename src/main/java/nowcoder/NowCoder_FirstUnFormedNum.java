package nowcoder;

// 最小不可组成和
// https://www.nowcoder.com/questionTerminal/296c2c18037843a7b719cf4c9c0144e4
public class NowCoder_FirstUnFormedNum {
    public static int getFirstUnFormedNum(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max += arr[i];
        }
        int n = arr.length;
        boolean[][] dp = new boolean[n][max + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= max; j++) {
                dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
            }
        }
        for (int i = min; i <= max; i++) {
            if (!dp[n - 1][i]) {
                return i;
            }
        }
        return max + 1;
    }
}

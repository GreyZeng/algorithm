import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        boolean[] b = new boolean[3];
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
        System.out.println(b);
    }

    public static int minInsertions(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        // dp[i][j] 表示，[i...j] 最少添加多少字符可以让[i...j]成为回文串
        int[][] dp = new int[str.length][str.length];
        // 对角线都是0，只需要填对角线右上半区
        for (int i = str.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < str.length; j++) {
                System.out.println("i == " + i + " j == " + j);
            }
        }
        return dp[0][str.length - 1];
    }
}

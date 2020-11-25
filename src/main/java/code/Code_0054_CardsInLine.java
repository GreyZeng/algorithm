package code;

public class Code_0054_CardsInLine {
    public static int win2(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];

        // 先填对角线
        for (int i = 0; i < n; i++) {
            f[i][i] = arr[i];
        }
        for (int col = 1; col < n; col++) {
            int r = 0;
            int c = col;
            while (r < n && c < n) {
                s[r][c] = Math.min(f[r + 1][c], f[r][c - 1]);
                f[r][c] = Math.max(s[r + 1][c] + arr[r], s[r][c - 1] + arr[c]);
                r++;
                c++;
            }
        }
        return Math.max(f[0][n - 1], s[0][n - 1]);
    }

    public static int win1(int[] arr) {
        if (null == arr || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
    }

    public static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }

    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 5, 19, 29, 80, 4};
        // A 4 9
        // B 7 5
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}

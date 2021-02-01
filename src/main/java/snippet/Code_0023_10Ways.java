package snippet;

//假设给你N个0，和N个1，你必须用全部数字拼序列
// 返回有多少个序列满足：任何前缀串，1的数量都不少于0的数量
// 卡特兰数
public class Code_0023_10Ways {
	public static int ways(int N) {
		return C(2 * N , N) - C(2 * N, N-1);
	}
	public static int C(int n, int k) {
        long res = 1L;
        long res2 = 1L;
        int x = n;
        int y = n - k;
        long gcd;
        while (x >= k + 1 && y >= 1) {
            res *= x--;
            res2 *= y--;
            gcd = gcd(res, res2);
            res /= gcd;
            res2 /= gcd;
        }
        int f = (int) (res / res2);
        while (x >= k + 1) {
            f *= x--;
        }
        while (y >= 1) {
            f /= y--;
        }
        return f;
    }

    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}

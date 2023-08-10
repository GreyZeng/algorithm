package bit;

// 给定一个参数N，返回1!+2!+3!+4!+…+N!的结果
public class Code_SumOfFactorial {

    public static void main(String[] args) {
        int N = 11;
        System.out.println(f1(N));
        System.out.println(f2(N));
    }

    public static long f1(int n) {
        long result = 0L;
        for (int i = 1; i <= n; i++) {
            result += factorial(i);
        }
        return result;
    }

    // 得到n!
    public static long factorial(int n) {
        long f = 1L;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    public static long f2(int N) {
        long ans = 0;
        long cur = 1;
        for (int i = 1; i <= N; i++) {
            cur = cur * i;
            ans += cur;
        }
        return ans;
    }
}

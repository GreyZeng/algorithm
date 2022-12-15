package 算法.位运算;

import java.util.Scanner;

// 给定一个参数N
// 求它的阶乘N!
// https://www.nowcoder.com/questionTerminal/4b8f5815c7cd40ab851c24fc5de70fa4
public class NowCoder_Factorial {

    public static long factorial(int N) {
        long ans = 1;
        for (int i = 1; i <= N; i++) {
            ans *= i;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.println(factorial(in.nextInt()));
        }
        in.close();
    }
}

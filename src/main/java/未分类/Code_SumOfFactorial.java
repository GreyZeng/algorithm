package 未分类;

// 给定一个参数N，返回1!+2!+3!+4!+…+N!的结果
public class Code_SumOfFactorial {
    public long f1(int num) {
        long result = 0;
        for (int i = 1; i <= num; i++) {
            result += factorial(i);
        }
        return result;
    }

    public long factorial(int n) {
        long result = 1L;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public long f2(int num) {
        long result = 0;
        long pre = 1;
        for (int i = 1; i <= num; i++) {
            pre = pre * i;
            result += pre;
        }
        return result;
    }
}

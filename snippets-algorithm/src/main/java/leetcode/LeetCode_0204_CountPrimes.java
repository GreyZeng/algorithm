package leetcode;

//Count the number of prime numbers less than a non-negative number, n.
//
//
//
//        Example 1:
//
//        Input: n = 10
//        Output: 4
//        Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
//        Example 2:
//
//        Input: n = 0
//        Output: 0
//        Example 3:
//
//        Input: n = 1
//        Output: 0
//
//
//        Constraints:
//
//        0 <= n <= 5 * 10^6
public class LeetCode_0204_CountPrimes {
    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] f = new boolean[n];
        int count = n >> 1; // 0~n范围内的偶数直接排除
        for (int i = 3; i * i < n; i += 2) {
            // 判断奇数是不是
            if (!f[i]) {
                // 排除掉奇数中有因数的值
                for (int j = i * i; j < n; j += (2 * i)) {
                    if (!f[j]) {
                        count--;
                        f[j] = true;
                    }
                }
            }
        }
        return count;
    }


    //  这个不是最优解
    public static int countPrimes2(int n) {
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                res++;
            }
        }
        return res;
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        countPrimes(10);
    }

}

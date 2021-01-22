package snippet;

//给定一个正整数M，请构造出一个长度为M的数组arr，要求
//        对任意的i、j、k三个位置，如果i<j<k，都有arr[i] + arr[k] != 2*arr[j]
//        返回构造出的arr
//        tips:
//        长度为1，满足
//        假设 a + b != 2*c
//        -> 2 * a - 1 + 2 * b - 1 != 2 * c - 1
//        2 * a + 2 * b != 2 * c
//
//        [a,b,c]
//        ->[2 * a - 1, 2 * b - 1, 2 * c - 1, 2 * a, 2 * b, 2 * c]
//
//        [1,5,3]
//        ->[1,9,5,2,10,6]
//
//        长度为M，只需要一个(M + 1)/2长度的种子
//
//        复杂度估计
//        T(N) = T(N/2) + O(N)
//
//        空间复杂度可以做到O(1)
public class Code_0010_RandomArray {
    public static int[] randomArray(int M) {
        if (M == 1) {
            return new int[]{1};
        }
        int half = (M + 1) / 2;
        int[] base = randomArray(half);
        int[] result = new int[M];
        int index = 0;
        for (; index < half; index++) {
            result[index] = base[index] * 2 - 1;
        }
        for (int i = 0; index < M; i++, index++) {
            result[index] = base[i] * 2;
        }
        return result;
    }
    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {


                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = randomArray(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");

        System.out.println(isValid(randomArray(1042)));
        System.out.println(isValid(randomArray(2981)));


    }

}

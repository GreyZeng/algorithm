package nowcoder;

import java.util.Scanner;

// 完美洗牌问题
// 笔记：https://www.cnblogs.com/greyzeng/p/16410631.html
// https://www.nowcoder.com/practice/90e03089da164172bf193786d242184b
// 给定一个长度为偶数的数组arr，长度记为2*N。
// 前N个为左部分，后N个为右部分。 arr就可以表示为{L1,L2,..,Ln,R1,R2,..,Rn}， 
// 请将数组调整成{R1,L1,R2,L2,..,Rn,Ln}的样子。
//一个结论：当arr长度S = 3^k - 1 (偶数)的时候，环的出发点1,3,9...3^(k-1)
// 前提：
// 1) 数组从1开始计算
// 2) 数组长度偶数
// @see LeetCode_0324_WiggleSortII
// @see LeetCode_1470_ShuffleTheArray
public class NowCoder_Shuffle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        shuffle(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        in.close();
    }
    public static void shuffle(int[] arr) {
        if (arr == null || arr.length == 0 || (arr.length & 1) != 0) {
            return;
        }
        shuffle(arr, 0, arr.length - 1);
    }

    public static void swap(int[] nums, int L, int R) {
        if (nums == null || nums.length <= 1 || R == L) {
            return;
        }
        nums[L] = nums[L] ^ nums[R];
        nums[R] = nums[L] ^ nums[R];
        nums[L] = nums[L] ^ nums[R];
    }

    public static void shuffle(int[] arr, int L, int R) {
        while (R - L + 1 > 0) {
            int len = R - L + 1;
            int base = 3;
            int k = 1;
            while (base <= (len + 1) / 3) {
                base *= 3;
                k++;
            }
            int half = (base - 1) / 2;
            int mid = (L + R) / 2;
            rotate(arr, L + half, mid, mid + half);
            toNext(arr, L, base - 1, k);
            L = L + base - 1;
        }
    }

    // i位置下一个位置应该去哪里
    // i 从1开始，而不是从0开始!!!
    private static int findNextIndex(int i, int N) {
        // return (2 * i) % (N + 1);
        if (i <= N / 2) {
            return 2 * i;
        }
        return (i - N / 2) * 2 - 1;
    }

    private static void toNext(int[] arr, int start, int len, int k) {
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int pre = arr[start + trigger - 1];
            int next = findNextIndex(trigger, len);
            while (next != trigger) {
                int t = arr[next + start - 1];
                arr[next + start - 1] = pre;
                pre = t;
                next = findNextIndex(next, len);
            }
            arr[next + start - 1] = pre;
        }
    }

    // @see LeetCodeCN_0058_LCOF
    // L..M部分和M+1..R部分互换
    public static void rotate(int[] arr, int L, int M, int R) {
        reverse(arr, L, M);
        reverse(arr, M + 1, R);
        reverse(arr, L, R);
    }

    // L..R做逆序调整
    public static void reverse(int[] arr, int L, int R) {
        while (L < R) {
            swap(arr, L++, R--);
        }
    }

}

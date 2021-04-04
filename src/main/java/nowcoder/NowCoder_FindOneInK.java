/*题目描述
        给定一个整型数组arr和一个大于1的整数k。已知arr中只有1个数出现了一次，其他的数出现k次，请返回出现了1次的数。
        输入描述:
        输入包含两行，第一行包含两个整数n和k，1<=n<=10^5，1 < k <= 100，n代表数组arr的长度，第二行n个整数，代表数组arr，数组arr中每个数都是32位整数。
        输出描述:
        输出一个整数，代表唯一出现1次的数。
        示例1
        输入
        7 3
        5 4 1 1 5 1 5
        输出
        4
        备注:
        时间复杂度O(n),额外空间复杂度O(1)。*/
package nowcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// https://www.nowcoder.com/practice/26e46f1f5e0d48c4b9ba13fe3e8d0ec6
public class NowCoder_FindOneInK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(oneInk2(arr, n, k));
        /*int recover = recover(toK(7, 3), 3, 32);
        System.out.println(recover);*/
        in.close();
    }

    // 用hash表
    public static int oneInK(int[] arr, int n, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                res = entry.getKey();
                break;
            }
        }
        return res;
    }


    // 转成K进制，k进制的异或和
    public static int oneInk2(int[] arr, int n, int k) {
        int volume = 32;
        int[] helper = new int[volume];
        for (int i = 0; i < n; i++) {
            eor(toK(arr[i], k), helper, k, volume);
        }
        return recover(helper, k, volume);
    }

    private static int recover(int[] helper, int k, int volume) {
        int v = 0;
        for (int i = volume - 1; i >= 0; i--) {
            v = v * k + helper[i];
        }
        return v;
    }

    private static void eor(int[] kth, int[] helper, int k, int volume) {
        for (int i = 0; i < volume; i++) {
            helper[i] = (kth[i] + helper[i]) % k;
        }
    }

    // 十进制转K进制
    public static int[] toK(int v, int k) {
        int[] kth = new int[32];
        int i = 0;
        while (v != 0) {
            kth[i++] = v % k;
            v = v / k;
        }
        return kth;
    }
}

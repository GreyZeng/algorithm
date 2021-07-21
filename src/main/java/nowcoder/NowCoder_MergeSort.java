package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

// 链接：https://www.nowcoder.com/questionTerminal/23ed40416d9c4c72816edb12daa3bdff

// 来源：牛客网


// 请编程实现一个整型数组的归并排序。本题会人工判断,请严格按照题目描述完成

// 输入描述:
// 一个无序的整型数组,输入格式见输入样例

// 输出描述:
// 一个有序的整型数组,输出格式见输出样例
// 示例1
// 输入
// [3, 1, 4, 5, 17, 2, 12]
// 输出
// [1, 2, 3, 4, 5, 12, 17]
public class NowCoder_MergeSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int[] num = toArray(line);
        mergeSort(num);
        System.out.println(Arrays.toString(num));
        in.close();
    }

    public static void printArr(int[] num) {
        for (int n : num) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    private static void mergeSort(int[] num) {
        process(num, 0, num.length - 1);
    }

    private static void process(int[] num, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = (R + L) / 2;
        process(num, L, mid);
        process(num, mid + 1, R);
        merge(num, L, mid, R);
    }

    // L...mid有序
    // mid+1 ... r有序
    private static void merge(int[] num, int l, int mid, int r) {
        int[] result = new int[r - l + 1];
        int i = l;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= r) {
            if (num[i] < num[j]) {
                result[index++] = num[i++];
            } else {
                result[index++] = num[j++];
            }
        }
        while (i <= mid) {
            result[index++] = num[i++];
        }
        while (j <= r) {
            result[index++] = num[j++];
        }
        index = 0;
        for (i = l; i <= r; i++) {
            num[i] = result[index++];
        }
    }

    public static int[] toArray(String line) {
        String s = line.substring(1, line.length() - 1);
        String[] data = s.split(",");
        int[] arr = new int[data.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(data[i].trim());
        }
        return arr;
    }
}

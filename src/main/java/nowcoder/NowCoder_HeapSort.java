package nowcoder;

// 链接：https://www.nowcoder.com/questionTerminal/ed8308a5f0f744fc936bdba78c15f810
// 来源：牛客网

// 给你一个n代表有n个数字，然后你需要使用堆排序将这些数字从小到大排好。

// 输入描述:
// 第一行输入一个n，代表有n个数字
// 第二行输入n个数


// 输出描述:
// 输出排序好后的n个数
// 示例1
// 输入
// 4
// 4 3 2 1
// 输出
// 1 2 3 4 
import java.util.Scanner;

// 1. 先让整个数组都变成大根堆结构，建立堆的过程: 
//    a. 从上到下的方法，时间复杂度为O(N*logN) 
//    b. 从下到上的方法，时间复杂度为O(N)
// 2. 把堆的最大值和堆末尾的值交换， 然后减少堆的大小之后，再去调整堆， 一直周而复始，时间复杂度为O(N*logN) 
// 3. 把堆的大小减小成0之后，排序完成
public class NowCoder_HeapSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        sort(arr);
        for(int num : arr) {
            System.out.print(num + " ");
        }
        in.close();
    }

    public static void sort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        int N = arr.length;
        for (int i = N - 1; i >= 0; i--) {
            heapify(arr, i, N);
        }
        while (N > 0) {
            heapify(arr, 0, N);
            swap(arr, --N, 0);
        }
    }


    public static void heapify(int[] arr, int i, int n) {
        int leftChild = 2 * i + 1;
        while (leftChild < n) {
            int largest = leftChild + 1 < n ? Math.max(arr[leftChild], arr[leftChild + 1]) : arr[leftChild];
            if (largest <= arr[i]) {
                break;
            }
            largest = largest == arr[leftChild] ? leftChild : leftChild + 1;
            swap(arr, largest, i);
            i = largest;
            leftChild = 2 * i + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    
}







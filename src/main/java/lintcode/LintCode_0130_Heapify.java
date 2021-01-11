//130. 堆化
//给出一个整数数组，堆化操作就是把它变成一个最小堆数组。
//
//对于堆数组A，A[0]是堆的根，并对于每个A[i]，A [i * 2 + 1]是A[i]的左儿子并且A[i * 2 + 2]是A[i]的右儿子。
//
//样例
//样例 1
//
//输入 : [3,2,1,4,5]
//输出 : [1,2,3,4,5]
//解释 : 返回任何一个合法的堆数组
//挑战
//O(n)的时间复杂度完成堆化
//
//说明
//什么是堆？ 什么是堆化？ 如果有很多种堆化的结果？
//
//堆是一种数据结构，它通常有三种方法：push， pop 和 top。其中，“push”添加新的元素进入堆，“pop”删除堆中最小/最大元素，“top”返回堆中最小/最大元素。
//把一个无序整数数组变成一个堆数组。如果是最小堆，每个元素A[i]，我们将得到A[i * 2 + 1] >= A[i]和A[i * 2 + 2] >= A[i]
//返回其中任何一个。
package lintcode;

public class LintCode_0130_Heapify {
    public static void heapify(int[] A) {
        if (null == A || A.length <= 1) {
            return;
        }
        int N = A.length;
        for (int i = N - 1; i >= 0; i--) {
            heapify(A, i, N);
        }
    }

    public static void heapify(int[] arr, int index, int N) {
        int leftChild = index * 2 + 1;
        while (leftChild < N) {
            int smallest = leftChild + 1 < N ? Math.min(arr[leftChild], arr[leftChild + 1]) : arr[leftChild];
            if (smallest >= arr[index]) {
                break;
            }
            smallest = smallest == arr[leftChild] ? leftChild : leftChild + 1;
            swap(arr, smallest, index);
            index = smallest;
            leftChild = index * 2 + 1;
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}

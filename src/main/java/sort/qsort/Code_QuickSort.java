package sort.qsort;

import java.util.Arrays;
import java.util.Stack;

/**
 * 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html 快速排序
 * <p>
 * partition过程 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
 * <p>
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 * <p>
 * 快速排序3.0(随机快排+荷兰国旗技巧优化) 在arr[L..R]范围上，进行快速排序的过程： 0）在这个范围上，随机选一个数记为num， 1）用num对该范围做partition，<
 * num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b] 2）对arr[L..a-1]进行快速排序(递归)
 * 3）对arr[b+1..R]进行快速排序(递归) 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
 * 1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差 2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件
 * 3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N 4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
 * <p>
 * 时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。
 */
public class Code_QuickSort {

    // 测评：https://www.lintcode.com/problem/464
    // 递归方法
    public void sortIntegers2(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] arr, int s, int e) {
        if (s < e) {
            int p = s + (int) (Math.random() * (e - s));
            swap(arr, p, e);
            //e  is privot position
            int[] range = sortColors(arr, s, e);
            quickSort(arr, s, range[0] - 1);
            quickSort(arr, range[1] + 1, e);
        }
    }

    // 快速排序非递归版本
    // 测评：https://www.lintcode.com/problem/464
    // 测评时候需要把sortIntegers21改成sortIntegers2
    public void sortIntegers21(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        Stack<Op> stack = new Stack<>();
        int L = 0;
        int R = arr.length - 1;
        int pivot = (int) (Math.random() * (R - L + 1));
        swap(arr, pivot, R);
        int[] range = sortColors(arr, L, R);
        stack.push(new Op(0, range[0] - 1));
        stack.push(new Op(range[1] + 1, R));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l)), op.r);
                range = sortColors(arr, op.l, op.r);
                stack.push(new Op(op.l, range[0] - 1));
                stack.push(new Op(range[1] + 1, op.r));
            }
        }
    }

    public class Op {

        public int l;
        public int r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    public int[] sortColors(int[] arr, int l, int r) {
        int s = l - 1;
        int e = r + 1;
        int p = arr[r];
        int i = l;
        while (i < e) {
            if (arr[i] > p) {
                swap(arr, i, --e);
            } else if (arr[i] < p) {
                swap(arr, i++, ++s);
            } else {
                i++;
            }
        }
        return new int[]{s + 1, e - 1};
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test start");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr2);
            new Code_QuickSort().sortIntegers2(arr1);
            new Code_QuickSort().sortIntegers21(arr2);
            Arrays.sort(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3) || !isEqual(arr2, arr3)) {
                System.out.println("oops！");
                printArray(arr1);
                printArray(arr2);
                printArray(arr3);
                break;
            }
        }
        System.out.println("test end");
    }
}
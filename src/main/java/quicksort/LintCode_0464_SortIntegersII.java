package quicksort;

import java.util.Stack;

// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html 快速排序
// 测评：https://www.lintcode.com/problem/464
// partition过程
// 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
// 要求额外空间复杂度O(1)，时间复杂度O(N)
// 快速排序3.0(随机快排+荷兰国旗技巧优化)
// 在arr[L..R]范围上，进行快速排序的过程：
// 0）在这个范围上，随机选一个数记为num，
// 1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
// 2）对arr[L..a-1]进行快速排序(递归)
// 3）对arr[b+1..R]进行快速排序(递归) 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
// 1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差
// 2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件
// 3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
// 4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
// 时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。
public class LintCode_0464_SortIntegersII {


    // 递归方法
    public void sortIntegers2(int[] a) {
        if (null == a || a.length <= 1) {
            return;
        }
        quickSort(a, 0, a.length - 1);
    }

    public void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int p = l + (int) (Math.random() * (r - l));
            swap(arr, p, r);
            int[] range = sortColors(arr, l, r);
            quickSort(arr, l, range[0] - 1);
            quickSort(arr, range[1] + 1, r);
        }
    }

    public int[] sortColors(int[] arr, int l, int r) {
        int i = l;
        int p = arr[r];
        int s = l - 1;
        int e = r + 1;
        while (i < e) {
            if (arr[i] < p) {
                swap(arr, i++, ++s);
            } else if (arr[i] > p) {
                swap(arr, i, --e);
            } else {
                i++;
            }
        }

        return new int[]{s + 1, e - 1};
    }

    public static void swap(int[] arr, int l, int r) {
        if (l != r) {
            arr[l] = arr[l] ^ arr[r];
            arr[r] = arr[l] ^ arr[r];
            arr[l] = arr[l] ^ arr[r];
        }
    }

    // 快速排序非递归版本
    public class Op {
        public int l;
        public int r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public void sortIntegers21(int[] a) {
        if (null == a || a.length <= 1) {
            return;
        }
        int l = 0;
        int r = a.length - 1;
        int p = l + (int) (Math.random() * (r - l + 1));
        swap(a, p, r);
        int[] range = sortColors(a, l, r);
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(l, range[0] - 1));
        stack.push(new Op(range[1] + 1, r));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                swap(a, op.r, op.l + (int) (Math.random() * (op.r - op.l)));
                range = sortColors(a, op.l, op.r);
                stack.push(new Op(op.l, range[0] - 1));
                stack.push(new Op(range[1] + 1, op.r));
            }
        }
    }

    // merge sort
    public void sortIntegers23(int[] a) {
        if (null == a || a.length <= 1) {
            return;
        }
        mergeSort(a, 0, a.length - 1);
    }

    public void mergeSort(int[] arr, int l, int r) {
        if (l != r) {
            int m = l + ((r - l) >> 1);
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int ls = l;
        int rs = m + 1;
        while (ls <= m && rs <= r) {
            if (arr[ls] <= arr[rs]) {
                help[i++] = arr[ls++];
            } else {
                help[i++] = arr[rs++];
            }
        }

        while (ls <= m) {
            help[i++] = arr[ls++];
        }
        while (rs <= r) {
            help[i++] = arr[rs++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    // heap sort
    public void sortIntegers24(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        // smallest to the arr.length - 1;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    public void heapify(int[] arr, int i, int size) {
        int leftChildIndex = 2 * i + 1;
        while (leftChildIndex < size) {
            int largest = leftChildIndex + 1 < size && arr[leftChildIndex + 1] >= arr[leftChildIndex] ? leftChildIndex + 1 : leftChildIndex;
            largest = arr[i] > arr[largest] ? i : largest;
            if (largest == i) {
                break;
            }
            swap(arr, largest, i);
            i = largest;
            leftChildIndex = 2 * i + 1;
        }
    }
}
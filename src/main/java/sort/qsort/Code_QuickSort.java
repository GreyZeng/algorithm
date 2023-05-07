package sort.qsort;

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
 * 3）对arr[b+1..R]进行快速排序(递归) 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成 1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差
 * 2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件 3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
 * 4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
 * <p>
 * 时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。
 */
public class Code_QuickSort {

    // 测评：https://www.lintcode.com/problem/464
    // 递归方法
    public void sortIntegers2(int[] a) {
        if (null == a || a.length < 2) {
            return;
        }
        quickSort(a, 0, a.length - 1);
    }

    public void quickSort(int[] arr, int s, int e) {
        if (s < e) {
            // Math.random() -> [0,1) 
            // Math.random() * (e - s) -> [0, e - s)
            // (int)(Math.random()*(e-s)) -> [0, e - s - 1]
            // s + (int) (Math.random() * (e - s)) -> [s, e - 1]
            swap(arr, e, s + (int) (Math.random() * (e - s)));
            int[] range = sortColors(arr, s, e);
            quickSort(arr, s, range[0] - 1);
            quickSort(arr, range[1] + 1, e);
        }
    }

    public void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
    // 荷兰国旗问题
    public int[] sortColors(int[] arr, int s, int e) {
        int l = s - 1;
        int r = e + 1;
        int p = arr[e];
        int i = s;
        while (i < r) {
            if (arr[i] > p) {
                swap(arr, i, --r);
            } else if (arr[i] < p) {
                swap(arr, i++, ++l);
            } else {
                i++;
            }
        }
        return new int[]{l + 1, r - 1};
    }

    // TODO
    // 快速排序非递归版本
    public class Op {
        public int l;
        public int r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

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
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                range = sortColors(arr, op.l, op.r);
                stack.push(new Op(op.l, range[0] - 1));
                stack.push(new Op(range[1] + 1, op.r));
            }
        }
    }

}

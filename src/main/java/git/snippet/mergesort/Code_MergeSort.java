package git.snippet.mergesort;

//归并排序
//1）整体是递归，左边排好序+右边排好序+merge让整体有序
//2）让其整体有序的过程里用了排外序方法
//3）利用master公式来求解时间复杂度
//T(N) = 2*T(N/2) + O(N^1)
//根据master可知时间复杂度为O(N*logN)
//merge过程需要辅助数组，所以额外空间复杂度为O(N)
//归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
public class Code_MergeSort {
    // 递归方法实现
    public static void sort1(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        p(arr, 0, arr.length - 1);
    }

    public static void p(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        p(arr, l, m);
        p(arr, m + 1, r);
        m(arr, l, m, r);
    }

    public static void m(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int s = l;
        int e = m + 1;
        int i = 0;
        while (s <= m && e <= r) {
            if (arr[s] < arr[e]) {
                help[i++] = arr[s++];
            } else {
                help[i++] = arr[e++];
            }
        }
        while (s <= m) {
            help[i++] = arr[s++];
        }
        while (e <= r) {
            help[i++] = arr[e++];
        }
        System.arraycopy(help, 0, arr, l, help.length);
    }

    // TODO 值得反复练习的Code
    // 归并排序的迭代版
    public static void mergeSort2(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        int step = 1; // 步长，初始为1
        int len = arr.length;
        while (step < len) {
            int lStart = 0;
            while (lStart < len) {
                if (lStart + step >= len) {
                    // 没有右组
                    break;
                }
                int mid = lStart + step - 1;
                int lEnd = Math.min(len - 1, mid + step);
                merge(arr, lStart, mid, lEnd);
                lStart = lEnd + 1;
            }
            if (step > len / 2) {
                // 防止溢出
                break;
            }
            step = step * 2;
        }
    }

    // 最好分开两个merge写，否则在做两个方法对数的时候，都引用了同一个merge方法，会导致同时对同时错，这样就测试不准确
    // arr[l……mid]已经有序
    // arr[mid+1……r]也已经有序
    // 将arr[l……r]整体变有序
    public static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int lStart = l;
        int index = 0;
        int rStart = mid + 1;
        while (lStart <= mid && rStart <= r) {
            help[index++] = arr[lStart] > arr[rStart] ? arr[rStart++] : arr[lStart++];
        }
        while (lStart <= mid) {
            help[index++] = arr[lStart++];
        }
        while (rStart <= r) {
            help[index++] = arr[rStart++];
        }
        System.arraycopy(help, 0, arr, l, help.length);
    }
}

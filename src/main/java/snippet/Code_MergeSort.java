package snippet;

/**
 * 归并排序
 * <p>
 * 1）整体是递归，左边排好序+右边排好序+merge让整体有序
 * <p>
 * 2）让其整体有序的过程里用了排外序方法
 * <p>
 * 3）利用master公式来求解时间复杂度
 * <p>
 * T(N) = 2*T(N/2) + O(N^1)
 * <p>
 * 根据master可知时间复杂度为O(N*logN)
 * <p>
 * merge过程需要辅助数组，所以额外空间复杂度为O(N)
 * <p>
 * 归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
 */
// 笔记：
public class Code_MergeSort {

    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int ls = l;
        int rs = mid + 1;
        int i = 0;
        while (ls <= mid && rs <= r) {
            if (arr[ls] < arr[rs]) {
                help[i++] = arr[ls++];
            } else {
                help[i++] = arr[rs++];
            }
        }
        while (ls <= mid) {
            help[i++] = arr[ls++];
        }
        while (rs <= r) {
            help[i++] = arr[rs++];
        }
        i = 0;
        for (int n : help) {
            arr[l + (i++)] = n;
        }
    }

    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        // 步长，1，2，4，8....
        int step = 1;
        while (step < len) {
            // 左组的第一个位置
            int lStart = 0;
            while (lStart < len) {
                if (lStart + step >= len) {
                    // 没有右组
                    break;
                }
                int mid = lStart + step - 1;
                // rEnd不能越界
                int rEnd = mid + Math.min(step, len - mid - 1);
                // 右组中第一个位置
                // 中点位置
                merge(arr, lStart, mid, rEnd);
                lStart = rEnd + 1;
            }
            // 防止溢出
            if (step > (len / 2)) {
                break;
            }
            step <<= 1;
        }
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
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

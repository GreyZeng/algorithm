package grey.algorithm;

//归并排序
//1）整体是递归，左边排好序+右边排好序+merge让整体有序
//2）让其整体有序的过程里用了排外序方法
//3）利用master公式来求解时间复杂度
//T(N) = 2*T(N/2) + O(N^1)
//根据master可知时间复杂度为O(N*logN)
//merge过程需要辅助数组，所以额外空间复杂度为O(N)
//归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
//笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
//测评链接：https://www.luogu.com.cn/problem/P1177 
// 或者：https://leetcode.com/problems/sort-an-array/
//acm测评风格，注意输入输出的处理
import java.util.Arrays;

public class Code_0014_MergeSort {

    // 递归解法
    public static void mergeSort1(int[] arr, int l, int r) {
        if (l >= r) {
            // 终止条件
            return;
        }
        int m = l + ((r - l) >> 1); // 中点位置
        mergeSort1(arr, l, m);
        mergeSort1(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    // 迭代版本的merge sort
    public static void mergeSort2(int[] arr, int l, int r) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int n = arr.length;

        // 1. 外层循环：控制每次要合并的子数组的大小（subArraySize）
        // subArraySize 从 1 开始，每次翻倍，表示当前有序子数组的长度
        // 依次为 1, 2, 4, 8, ...
        for (int subArraySize = 1; subArraySize < n; subArraySize = subArraySize * 2) {

            // 2. 内层循环：从左到右，找到每一对需要合并的子数组，然后调用merge
            // leftStart 是每一对子数组中，左边那个数组的起始位置
            for (int leftStart = 0; leftStart < n; leftStart = leftStart + subArraySize * 2) {

                // 确定左子数组的边界
                // 左数组是 arr[leftStart ... mid]
                int mid = leftStart + subArraySize - 1;

                // 如果 mid 已经越界，说明在 leftStart 之后连一个完整的左数组都凑不齐了
                // 那么剩下的部分自然就是有序的，无需再合并，直接跳出内层循环
                if (mid >= n - 1) {
                    break;
                }

                // 确定右子数组的边界
                // 右数组是 arr[mid + 1 ... rightEnd]
                // 这里用 Math.min 是为了防止右边界越界
                // 比如数组长度为13，当合并size=4的子数组时，最后一组是 arr[8...11] 和 arr[12...12]
                // 此时 rightEnd 就不能是 8 + 4*2 - 1 = 15，而应该是 n - 1 = 12
                int rightEnd = Math.min(leftStart + subArraySize * 2 - 1, n - 1);

                // 找到了左右两个要合并的有序子数组，执行合并
                // System.out.printf("合并: subArraySize=%d, merge(arr, %d, %d, %d)%n", subArraySize, leftStart, mid, rightEnd);
                merge(arr, leftStart, mid, rightEnd);
            }
            // System.out.println("--- subArraySize=" + subArraySize + " 的一轮合并结束 ---");
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int i = 0;
        int s = l;
        int e = m + 1;
        int[] help = new int[r - l + 1];
        while (s <= m && e <= r) {
            if (arr[s] <= arr[e]) {
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
        for (i = 0; i < help.length; i++) {
            arr[l++] = help[i];
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

        // 数组长度1~500，等概率随机
        int num = 500;
        // 每个值的大小在1~1024，等概率随机
        int value = 1024;
        // 测试次数
        int testTimes = 50000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(num, value);
            int[] copyArray1 = copyArray(arr);
            int[] copyArray2 = copyArray(arr);
            Arrays.sort(arr);
            mergeSort1(copyArray1, 0, copyArray1.length - 1);
            mergeSort2(copyArray2, 0, copyArray2.length - 1);
            if (!sameValue(arr, copyArray1)) {
                System.out.println("出错了！");
                break;
            }
            if (!sameValue(arr, copyArray2)) {
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static boolean sameValue(int[] arr1, int[] arr2) {
        if (null == arr1) {
            return null != arr2;
        }
        if (null == arr2) {
            return null != arr1;
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

    private static int[] generateArray(int num, int value) {
        int[] arr = new int[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value) + 1;
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        int[] copyArray = new int[arr.length];
        System.arraycopy(arr, 0, copyArray, 0, copyArray.length);
        return copyArray;
    }

}

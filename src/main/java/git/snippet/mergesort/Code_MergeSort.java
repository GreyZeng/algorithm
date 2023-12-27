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
    public static void mergeSort1(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        // 求中点
        int mid = l + ((r - l) >> 1);
        // 左边部分有序
        process(arr, l, mid);
        // 右边部分有序
        process(arr, mid + 1, r);
        // 整体变有序
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] helper = new int[r - l + 1];
        int index = 0;
        int ls = l;
        int rs = m + 1;
        while (ls <= m && rs <= r) {
            if (arr[ls] > arr[rs]) {
                helper[index++] = arr[rs++];
            } else {
                helper[index++] = arr[ls++];
            }
        }
        while (ls <= m) {
            helper[index++] = arr[ls++];
        }
        while (rs <= r) {
            helper[index++] = arr[rs++];
        }
        for (int i = 0; i < helper.length; i++) {
            arr[l + i] = helper[i];
        }
    }

    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (null != arr && arr.length > 1) {
            int group = 1;
            int len = arr.length;
            while (group < len) {
                int ls = 0;
                while (ls < len) {
                    if (ls + group >= len) {
                        break;
                    }
                    int mid = ls + group - 1;
                    int le = Math.min(mid + group, len - 1);
                    merge(arr, ls, mid, le);
                    ls = le + 1;
                }
                if (group * 2 > len) {
                    break;
                }
                group <<= 1;
            }
        }
    }
}

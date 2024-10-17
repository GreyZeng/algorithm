package git.snippet.recursion;

// 递归求一个数组中的最大值
public class Code_0016_GetMaxRecursive {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 23, 1, 0, 32, 0, 33, 21, 334};
        int[] arr2 = new int[]{1, 23, 1, 0, 32000, 0, 33, 21, 334};
        int[] arr3 = new int[]{1000, 23, 1, 0, 32, 0, 33, 21, 334};
        int[] arr4 = new int[]{1000, 2333333, 1, 0, 32, 0, 33, 21, 334};
        int[] arr5 = new int[]{1000, 2333333, 1, 0, 32, 0, 33, 23393333, 334};
        System.out.println(getMax(arr));
        System.out.println(getMax(arr2));
        System.out.println(getMax(arr3));
        System.out.println(getMax(arr4));
        System.out.println(getMax(arr5));
    }

    // 数组必须非空
    public static int getMax(int[] arr) {
        int N = arr.length;
        if (N == 1) {
            return arr[0];
        }
        return process(arr, 0, N - 1);
    }

    public static int process(int[] arr, int s, int e) {
        if (s == e) {
            return arr[s];
        }
        int mid = s + ((e - s) >> 1);
        int l = process(arr, s, mid);
        int r = process(arr, mid + 1, e);
        return Math.max(l, r);
    }
}

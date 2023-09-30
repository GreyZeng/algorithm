package leetcode;

// 归并排序
public class LeetCode_0912_SortAnArray {
  public static int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return nums;
  }

  public static void mergeSort(int[] num, int L, int R) {
    if (L == R) {
      return;
    }
    int mid = ((R - L) >> 1) + L;
    mergeSort(num, L, mid);
    mergeSort(num, mid + 1, R);
    merge(num, L, mid, R);
  }

  public static void merge(int[] num, int L, int mid, int R) {
    int[] helper = new int[R - L + 1];
    int i = L;
    int j = mid + 1;
    int index = 0;
    while (i <= mid && j <= R) {
      if (num[i] < num[j]) {
        helper[index++] = num[i++];
      } else {
        helper[index++] = num[j++];
      }
    }
    while (i <= mid) {
      helper[index++] = num[i++];
    }
    while (j <= R) {
      helper[index++] = num[j++];
    }
    int k = 0;
    for (int n : helper) {
      num[L + (k++)] = n;
    }
  }
}

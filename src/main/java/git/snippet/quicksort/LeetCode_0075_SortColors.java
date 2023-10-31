package git.snippet.quicksort;

// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html
// 荷兰国旗问题
// 仅0，1，2，所有的0放左边，所有的1放中间，所有的2放右边
// https://leetcode.com/problems/sort-colors/
public class LeetCode_0075_SortColors {
  public void sortColors(int[] nums) {
    if (nums == null || nums.length < 2) {
      return;
    }
    int i = 0;
    int l = -1;
    int r = nums.length;
    while (i < r) {
      if (nums[i] == 0) {
        swap(nums, i++, ++l);
      } else if (nums[i] == 2) {
        swap(nums, i, --r);
      } else {
        // nums[i] == 2
        i++;
      }
    }
  }

  public void swap(int[] arr, int i, int j) {
    if (i != j) {
      arr[i] = arr[i] ^ arr[j];
      arr[j] = arr[i] ^ arr[j];
      arr[i] = arr[i] ^ arr[j];
    }
  }
}

package nowcoder;


import java.util.Arrays;
import java.util.Scanner;


// 二分查找： 在一个有序数组中，找大于等于某个数最左侧的位置
// 链接：https://www.nowcoder.com/questionTerminal/fcd66d61e04e476d9bd116ff6b4d04e7
public class NowCoder_NearestLeft {

    public static int getNearestLeft(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (target < nums[mid]) {
                ans = mid;
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        // 虽然不排序也可以通过，但是题目未说明一定是有序数组
        Arrays.sort(arr);
        System.out.println(getNearestLeft(arr, k));
        in.close();
    }
}

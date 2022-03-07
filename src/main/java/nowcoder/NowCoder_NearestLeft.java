package nowcoder;


import java.util.Arrays;
import java.util.Scanner;

//链接：https://www.nowcoder.com/questionTerminal/fcd66d61e04e476d9bd116ff6b4d04e7
//        来源：牛客网
//
//        你需要输入一个n，一个数k，然后输入一个长度为n个大小的数组arr，然后你需要在arr上找满足>=K的最左位置，并且输出这个位置，如果不存在这个位置就输出-1。
//
//        输入描述:
//        第一行输入一个n，k
//        第二行输入长度为n个大小的数组arr
//
//
//        输出描述:
//        输出>=K的最左位置
//        示例1
//        输入
//        5 1
//        0 0 2 4 6
//        输出
//        2
//        示例2
//        输入
//        5 9
//        2 4 6 7 8
//        输出
//        -1
public class NowCoder_NearestLeft {
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

//    public static void test() {
//        int[] arr = {1, 3, 3, 5, 7};
//        System.out.println(getNearestLeft(arr, 5));
//    }

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
}

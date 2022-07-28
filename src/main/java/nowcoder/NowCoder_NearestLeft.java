package nowcoder;

import java.util.Arrays;
import java.util.Scanner;


// 二分查找： 在一个有序数组中，找大于等于某个数最左侧的位置
// 链接：https://www.nowcoder.com/questionTerminal/fcd66d61e04e476d9bd116ff6b4d04e7
public class NowCoder_NearestLeft {
    public static int getNearestLeft(int[] arr, int t) {
        int ans = -1;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] < t) {
                l = m + 1;
            } else {
                r = m - 1;
                ans = m;
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

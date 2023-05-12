package slidewindow;

import java.util.*;

// 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
// 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
// 绳子的边缘点碰到X轴上的点，也算盖住
// O(N*logN)
// 最优解
// 长度和范围有单调性 -> 滑动窗口，左右指针
// 在牛客上，[类似题目](https://www.nowcoder.com/questionTerminal/2b2567c9b95743f19c167bb1ec644b43)
// 和这题有点差别，牛客上的题目是不算边缘压中的情况的
// tips: 滑动窗口和双指针
public class Code_CordCoverMaxPoint {

    public static int maxPoint1(int[] arr, int L) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    public static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    // 最优解 滑动窗口
    public static int maxPoint2(int[] arr, int K) {
        int left = 0;
        int right = 0;
        int n = arr.length;
        int max = 1;
        while (left < n) {
            while (right < n && arr[right] - arr[left] <= K) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }
        System.out.println("测试结束");

    }

}

package leetcode;

//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
//
//        设计一个算法使得这 m 个子数组各自和的最大值最小。
//
//         
//
//        示例 1：
//
//        输入：nums = [7,2,5,10,8], m = 2
//        输出：18
//        解释：
//        一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//        因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
//        示例 2：
//
//        输入：nums = [1,2,3,4,5], m = 2
//        输出：9
//        示例 3：
//
//        输入：nums = [1,4,4], m = 3
//        输出：4
//         
//
//        提示：
//
//        1 <= nums.length <= 1000
//        0 <= nums[i] <= 106
//        1 <= m <= min(50, nums.length)
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/split-array-largest-sum
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//tips:
//        从左往右的尝试模型，按最后一个画家负责的情况枚举情况
//        k块
//        min(max(s1,s2...sk))
//        四边形不等式不是最优解
//        O(N * K)
//
//        目标定，至少需要几个画家？
//        累加和--》二分--》几个画家
//
//        有一个最优解
//
//        1. 先定一个目标（比如定成累加和 为 0 ~ sum 之间的一个值） 看下需要划分几块
//        2. 二分 达标位置（取最左的位置）
//        O(N*log2Sum)
public class LeetCode_0410_SplitArrayLargestSum {
    // 求原数组arr[L...R]的累加和
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 不优化枚举的动态规划方法，O(N^2 * K)
    public static int splitArray1(int[] nums, int m) {
        int n = nums.length;
        if (n == 1) {
            // 根据题意，m此时必定等于nums.length
            return nums[0];
        }
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        if (m == 1) {
            return sum(sum, 0, n - 1);
        }
        if (m == n) {
            return max(nums);
        }
        
        // TODO
        return -1;
    }

    public static int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    // 枚举优化，O(N * K)
    public static int splitArray2(int[] nums, int m) {
        // TODO
        return -1;
    }

    // 最优解（二分）
    public static int splitArray3(int[] nums, int M) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long l = 0;
        long r = sum;
        long ans = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            long cur = getNeedParts(nums, mid);
            if (cur <= M) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ans;
    }

    public static int getNeedParts(int[] arr, long aim) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > aim) {
                return Integer.MAX_VALUE;
            }
        }
        int parts = 1;
        int all = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (all + arr[i] > aim) {
                parts++;
                all = arr[i];
            } else {
                all += arr[i];
            }
        }
        return parts;
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 100;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int M = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(len, maxValue);
            int ans1 = splitArray1(arr, M);
            int ans2 = splitArray2(arr, M);
            int ans3 = splitArray3(arr, M);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.print("arr : ");
                printArray(arr);
                System.out.println("M : " + M);
                System.out.println("ans1 : " + ans1);
                System.out.println("ans2 : " + ans2);
                System.out.println("ans3 : " + ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

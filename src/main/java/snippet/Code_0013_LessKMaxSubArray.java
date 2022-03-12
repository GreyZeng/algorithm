package snippet;


// https://www.nowcoder.com/questionTerminal/3473e545d6924077a4f7cbc850408ade
// minSum  必须以i开头的子数组获得的最小累加和是多少
// minSumEnd 必须以i开头的子数组获得最小累加和的时候，扩到的右边界是多少。
public class Code_0013_LessKMaxSubArray {
    public static int maxSubArray(int[] nums, int k) {
        int n = nums.length;
        // i位置向右边最多能扩到多少累加和最小
        int[] minSumEnds = new int[n];
        // i位置向右边最多能扩到的最小累加和
        int[] minSumEndsValue = new int[n];
        initMinSumEnd(nums, minSumEnds, minSumEndsValue, n);
        int end = 0;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (end < n && sum + minSumEndsValue[end] <= k) {
                sum += minSumEndsValue[end];
                end = minSumEnds[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) {
                sum -= nums[i];
            } else {
                end = i + 1;
            }
        }

        return res;
    }

    private static void initMinSumEnd(int[] nums, int[] minSumEnds, int[] minSumEndsValue, int N) {
        minSumEnds[N - 1] = N - 1;
        minSumEndsValue[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            minSumEndsValue[i] = nums[i];
            minSumEnds[i] = i;
            if (minSumEndsValue[i + 1] <= 0) {
                minSumEndsValue[i] += minSumEndsValue[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            }
        }
    }

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxSubArray(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

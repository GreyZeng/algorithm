package git.snippet.candy;

// 来自网易
// 给定一个正数数组arr，表示每个小朋友的得分
// 任何两个相邻的小朋友，如果得分一样，怎么分糖果无所谓，但如果得分不一样，分数大的一定要比分数少的多拿一些糖果
// 假设所有的小朋友坐成一个环形，返回在不破坏上一条规则的情况下，需要的最少糖果数
public class Code_CircleCandy {
    public static int minCandy(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int n = arr.length;
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            // 寻找洼地
            if (arr[i] <= arr[last(i, n)] && arr[i] <= arr[next(i, n)]) {
                minIndex = i;
                break;
            }
        }
        // 原始数组为[3,4,2,3,2]
        // nums数组为[2,3,2,3,4,2]
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++, minIndex = next(minIndex, n)) {
            nums[i] = arr[minIndex];
        }
        int[] left = new int[n + 1];
        left[0] = 1;
        for (int i = 1; i <= n; i++) {
            left[i] = nums[i] > nums[i - 1] ? (left[i - 1] + 1) : 1;
        }
        int[] right = new int[n + 1];
        right[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            right[i] = nums[i] > nums[i + 1] ? (right[i + 1] + 1) : 1;
        }
        int sum = 0;
        // 这里不是到n而是到n-1，因为n是占位的，不需要统计进去
        for (int i = 0; i < n; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }

    // 环形数组的下一个位置
    private static int next(int i, int n) {
        return i == n - 1 ? 0 : (i + 1);
    }

    // 环形数组的上一个位置
    private static int last(int i, int n) {
        return i == 0 ? (n - 1) : (i - 1);
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 3, 2};
        System.out.println(minCandy(arr));
    }
}

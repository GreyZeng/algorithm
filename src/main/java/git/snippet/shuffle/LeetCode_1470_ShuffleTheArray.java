package git.snippet.shuffle;

// 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
//
// 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
//
//
//
// 示例 1：
//
// 输入：nums = [2,5,1,3,4,7], n = 3
// 输出：[2,3,5,4,1,7]
// 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
// 示例 2：
//
// 输入：nums = [1,2,3,4,4,3,2,1], n = 4
// 输出：[1,4,2,3,3,2,4,1]
// 示例 3：
//
// 输入：nums = [1,1,2,2], n = 2
// 输出：[1,2,1,2]
//
//
// 提示：
//
// 1 <= n <= 500
// nums.length == 2n
// 1 <= nums[i] <= 10^3
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/shuffle-the-array
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 完美洗牌问题
// @see NowCoder_Shuffle
// 笔记：https://www.cnblogs.com/greyzeng/p/16410631.html
public class LeetCode_1470_ShuffleTheArray {
    public static void shuffle(int[] arr) {
        if (arr == null || arr.length == 0 || (arr.length & 1) != 0) {
            return;
        }
        shuffle(arr, 0, arr.length - 1);
    }

    public static void swap(int[] nums, int L, int R) {
        if (nums == null || nums.length <= 1 || R == L) {
            return;
        }
        nums[L] = nums[L] ^ nums[R];
        nums[R] = nums[L] ^ nums[R];
        nums[L] = nums[L] ^ nums[R];
    }

    public static void shuffle(int[] arr, int L, int R) {
        while (R - L + 1 > 0) {
            int len = R - L + 1;
            int base = 3;
            int k = 1;
            while (base <= (len + 1) / 3) {
                base *= 3;
                k++;
            }
            int half = (base - 1) / 2;
            int mid = (L + R) / 2;
            rotate(arr, L + half, mid, mid + half);
            toNext(arr, L, base - 1, k);
            L = L + base - 1;
        }
    }

    // i位置下一个位置应该去哪里
    // i 从1开始，而不是从0开始!!!
    private static int findNextIndex(int i, int N) {
        // return (2 * i) % (N + 1);
        if (i <= N / 2) {
            return 2 * i;
        }
        return (i - N / 2) * 2 - 1;
    }

    private static void toNext(int[] arr, int start, int len, int k) {
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int pre = arr[start + trigger - 1];
            int next = findNextIndex(trigger, len);
            while (next != trigger) {
                int t = arr[next + start - 1];
                arr[next + start - 1] = pre;
                pre = t;
                next = findNextIndex(next, len);
            }
            arr[next + start - 1] = pre;
        }
    }

    // @see LeetCodeCN_0058_LCOF
    // L..M部分和M+1..R部分互换
    public static void rotate(int[] arr, int L, int M, int R) {
        reverse(arr, L, M);
        reverse(arr, M + 1, R);
        reverse(arr, L, R);
    }

    // L..R做逆序调整
    public static void reverse(int[] arr, int L, int R) {
        while (L < R) {
            swap(arr, L++, R--);
        }
    }

    public int[] shuffle(int[] arr, int n) {
        shuffle(arr);
        for (int i = 0; i < arr.length; i += 2) {
            reverse(arr, i, i + 1);
        }
        return arr;
    }
}

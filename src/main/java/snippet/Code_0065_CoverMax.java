
package snippet;

// 双指针似乎不行, 比如这种情况
// 1 2           ........101 102
// 左指针在左边1位置，右指针在右边102位置，这两个位置距离其相邻位置的差距都是1，这个时候，不知道该怎么判断要缩动左指针还是右指针
public class Code_0065_CoverMax {

    public static int coverMax(int[] arr, int n, int k) {
        int l = 0;
        int r = 0;
        int max = 0;
        while (l < n) {
            while (r < n && arr[r] - arr[l] <= k) {
                r++;
            }
            max = Math.max(max, r - l);
            l++;
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(coverMax(arr, n, k));
    }
}

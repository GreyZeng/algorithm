package leetcode.medium;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，M > 1, K < M, 找到出现了K次的数，要求，额外空间复杂度O(1)，时间复杂度O(N)
 *
 * @author Grey
 * @date 2021年7月18日 下午11:53:52
 * @since 1.8
 */
// https://www.cnblogs.com/greyzeng/p/15385402.html
public class LeetCode_0137_SingleNumberII {
    public int singleNumber(int[] nums) {
        return km(nums, 1, 3);
    }
   //  一个数组中有一种数出现k次，其他数都出现了m次，m > 1, k < m, 找到出现了k次的数
    public int km(int[] arr, int k, int m) {
        int[] bit = new int[32];
        // 将arr中的每个元素转换成二进制填充到bit数组中，每个位置上的数字累加
        for (int n : arr) {
            for (int i = 0; i < 32; i++) {
                bit[i] += ((n >>> i) & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (bit[i] % m != 0) {
                // 出现了k次的数在i位置是1
                ans |= (1 << i);
            }
        }
        return ans;
    }
}

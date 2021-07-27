package leetcode;

/**
 * @author Grey
 * @date 2021年7月18日 下午11:53:15
 * @since
 */
public class LeetCode_0260_SingleNumberIII {
    public static int[] singleNumber(int[] arr) {
        int eor = 0;
        for (int n : arr) {
            eor ^= n;
        }
        // 假设出现奇数次的两种数为 a和b
        // eor = a ^ b
        // 获取最右侧的1
        int a = 0;
        int rightOne = eor & ((~eor) + 1);
        for (int n : arr) {
            if ((n & rightOne) == 0) {
                a ^= n;
            }
        }
        int b = a ^ eor;
        return new int[]{a, b};
    }
}

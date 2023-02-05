package 练习题.leetcode.medium;

/**
 * @author Grey
 * @date 2021年7月18日 下午11:53:15
 * @since 1.8
 */
// https://www.cnblogs.com/greyzeng/p/15385402.html
public class LeetCode_0260_SingleNumberIII {
    public static int[] singleNumber(int[] arr) {
        int eor = 0;
        for (int n : arr) {
            eor ^= n;
        }
        // 最右侧的1
        int mostRightOne = (eor) & ((~eor) + 1);
        // eor的结果就是 a^b
        // 假设a和mostRightOne位置上的1一致
        // b就肯定和mostRightOne位置上的1不一致
        int a = 0;
        for (int n : arr) {
            if ((n & mostRightOne) != 0) {
                // a阵营中的数
                a ^= n;
            }
        }
        int b = a ^ eor;
        return new int[]{a, b};
    }
}

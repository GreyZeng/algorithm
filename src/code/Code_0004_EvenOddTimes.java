package code;


public class Code_0004_EvenOddTimes {
    public static void main(String[] args) {
        int[] array2 = {1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5};
        printEvenNum(array2);
    }


    /**
     * 题目四
     * <p>
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
     * tips
     * // 假设出现了奇数次的数字为a和b，我们对数组所有数做异或操作，得到的最后结果一定是：a^b，记这个值为：m = a^b
     * // 将m的二进制数的最右侧的1所代表的数找到，假设这个数为n，最右侧1所在的位置为r
     * // 那么整个数组中，一定分为两类数：r位置上为1和r位置上为0的数，且a和b一定分别位于这两类数中（a和b不可能在r位置上同时为0或者同时为1）
     * // 我们可以通过n和数组中每个数字做与操作，如果为0，说明这个数字中的r位置为0
     * // 然后将这些r位置中为0的数字做异或操作，得到最后得结果就是a和b中的一个，假设为a
     * // 然后将a和m做异或，得到b
     */
    public static void printEvenNum(int[] arr) {
        if (null == arr || arr.length < 2) {
            throw new RuntimeException("数组长度不够");
        }
        int m = arr[0];
        for (int i = 1; i < arr.length; i++) {
            m ^= arr[i]; // m = a^b;
        }

        int n = m & ((~m) + 1);
        int one = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((n & arr[i]) == 0) {
                one ^= arr[i];
            }
        }
        int two = one ^ m;
        System.out.println("one is :" + one + " two is :" + two);
    }
}

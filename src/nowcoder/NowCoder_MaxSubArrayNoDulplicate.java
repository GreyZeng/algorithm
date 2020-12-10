package nowcoder;


import java.util.HashSet;

public class NowCoder_MaxSubArrayNoDulplicate {
    public static int maxLength(int[] arr) {
        return p(arr, arr.length - 1);
    }

    // 0~ index范围内自由选择，可以拼出的最大无重复子串是多少
    public static int p(int[] arr, int index) {
        if (index == 0) {

            return 1;
        }
        if (index == 1) {
            return arr[0] == arr[1] ? 1 : 2;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 2; i <= index; i++) {

        }
        return max;

    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 4, 5,6};
        int a = maxLength(nums);
        System.out.println(a);
    }
}

package leetcode;


import java.util.Arrays;
import java.util.Comparator;

//Given a list of non negative integers, arrange them such that they form the largest number.
//
//        Example 1:
//
//        Input: [10,2]
//        Output: "210"
//        Example 2:
//
//        Input: [3,30,34,5,9]
//        Output: "9534330"
//        Note: The result may be very large, so you need to return a string instead of an integer.
public class LeetCode_0179_LargestNumber {

    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o2 + o1).compareTo(o1 + o2);
        }
    }

    public static String largestNumber(int[] nums) {
        int L = nums.length;
        String[] str = new String[L];
        for (int i = 0; i < L; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str, new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (String i : str) {
            sb.append(i);
        }
        String s = sb.toString();
        char[] chars = s.toCharArray();
        int index = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                index = i;
                break;
            }
        }
        return index == -1 ? "0" : s.substring(index);
    }

    public static void main(String[] args) {
        int[] ints = {0,0,0};
        System.out.println(largestNumber(ints));
    }

}

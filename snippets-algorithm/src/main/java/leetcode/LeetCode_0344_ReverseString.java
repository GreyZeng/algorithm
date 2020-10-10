package leetcode;

//Write a function that reverses a string. The input string is given as an array of characters char[].
//
//		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
//
//		You may assume all the characters consist of printable ascii characters.
//
//
//
//		Example 1:
//
//		Input: ["h","e","l","l","o"]
//		Output: ["o","l","l","e","h"]
//		Example 2:
//
//		Input: ["H","a","n","n","a","h"]
//		Output: ["h","a","n","n","a","H"]
public class LeetCode_0344_ReverseString {

    public static void reverseString(char[] s) {
        int L = 0;
        int R = s.length - 1;
        while (L < R) {
            char t = s[L];
            s[L++] = s[R];
            s[R--] = t;
        }
    }

    public static void main(String[] args) {
        String str = "abc";
        char[] t = str.toCharArray();
        reverseString(t);
        for (char i : t) {
            System.out.println(i);
        }
    }

}

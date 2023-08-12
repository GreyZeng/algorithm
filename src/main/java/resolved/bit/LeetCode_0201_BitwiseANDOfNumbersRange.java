package resolved.bit;

// https://leetcode.com/problems/bitwise-and-of-numbers-range/
public class LeetCode_0201_BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int left, int right) {
        while (right > left) {
            right -= (right & (-right));
        }
        return right;
    }
}

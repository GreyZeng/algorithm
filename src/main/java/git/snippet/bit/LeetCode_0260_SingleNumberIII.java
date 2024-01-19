package git.snippet.bit;

// 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
// https://www.cnblogs.com/greyzeng/p/15385402.html
// https://leetcode.com/problems/single-number-iii/
public class LeetCode_0260_SingleNumberIII {
	
    public int[] singleNumber(int[] nums) {
        int eor = 0;
        for (int num : nums) {
        	eor ^= num;
        }
        // eor = a ^ b
        // rightOne = (eor) & (-eor)
        int rightOne =(eor) & ((~eor) + 1);
        int a = 0;
        for (int num : nums) {
        	if ((num & rightOne) == 0) {
        		a ^= num;
        	}
        }
        int b = a ^ eor;
        return new int[] {a, b};
    }
}

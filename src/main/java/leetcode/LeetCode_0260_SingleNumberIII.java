package leetcode;

/**
 *  
 * @author Grey
 * @date 2021年7月18日 下午11:53:15
 * @since
 */
public class LeetCode_0260_SingleNumberIII {
	public int[] singleNumber(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		// 假设出现奇数的两个数是a和b，
		// 那么eor得到的结果是：a^b
		// onlyOneRightest表示提取eor最右侧的1
		// 这个最右侧的位置a和b一定不同（假设a这个位置是1，b这个位置是0）
		int onlyOneRigthest = eor & ((~eor) + 1);
		// 此时，数组可以分成两批数
		// 一批数和a一样，都是这个位置为1的
		// 另外一批数和b一样，都是这个位置为0的
		int[] result = new int[2];
		int a = 0;
		int b = 0;
		for (int num : arr) {
			if ((onlyOneRigthest & num) != 0) {
				a ^= num;
			} else {
				b ^= num;
			}
		}
 		result[0] = Math.min(a, b);
 		result[1] = Math.max(a, b);
		return result;
	}
}

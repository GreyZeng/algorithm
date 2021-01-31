/*题目描述
        先给出可整合数组的定义：如果一个数组在排序之后，每相邻两个数的差的绝对值都为1，或者该数组长度为1，则该数组为可整合数组。例如，[5, 3, 4, 6, 2]排序后为[2, 3, 4, 5, 6]，符合每相邻两个数差的绝对值都为1，所以这个数组为可整合数组
        给定一个数组arr, 请返回其中最大可整合子数组的长度。例如，[5, 5, 3, 2, 6, 4, 3]的最大可整合子数组为[5, 3, 2, 6, 4]，所以请返回5
        [要求]
        时间复杂度为O(n^2)，空间复杂度为O(n)
        输入描述:
        第一行一个整数N，表示数组长度
        第二行N个整数，分别表示数组内的元素
        输出描述:
        输出一个整数，表示最大可整合子数组的长度
        示例1
        输入
        7
        5 5 3 2 6 4 3
        输出
        5*/
package nowcoder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Young
 * @version 1.0
 * @date 2021/1/30 17:08
 */
// https://www.nowcoder.com/practice/677a21987e5d46f1a62cded9509a94f2
public class NowCoder_MaxUnionArray {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(in.next());
		}
		System.out.println(maxUnionArrayLen(arr));
		in.close();
	}
    public static int maxUnionArrayLen(int[] arr) {
    	if (arr == null || arr.length == 0) {
    		return 0;
    	}
    	int len = 0;
    	Set<Integer> set = new HashSet<>();
    	for (int L = 0; L < arr.length; L++) {
    		set.clear();
    		int max = Integer.MIN_VALUE;
    		int min = Integer.MAX_VALUE;
    		for (int R = L ; R < arr.length; R++) {
    			if (set.contains(arr[R])) {
    				L = R;
    				break;
    			}
    			max = Math.max(max, arr[R]);
    			min = Math.max(min, arr[R]);
    			if (max - min == R - L) {
    				len = Math.max(len, R - L + 1);
    			}
    		}
    	}
    	return len; 
    }
}

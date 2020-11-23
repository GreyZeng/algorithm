//在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
//(1) 每次只能移动一个盘子;
//(2) 盘子只能从柱子顶端滑出移到下一根柱子;
//(3) 盘子只能叠在比它大的盘子上。
//
//请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
//
//你需要原地修改栈。
//
//示例1:
//
// 输入：A = [2, 1, 0], B = [], C = []
// 输出：C = [2, 1, 0]
//示例2:
//
// 输入：A = [1, 0], B = [], C = []
// 输出：C = [1, 0]
//提示:
//
//A中盘子的数目不大于14个。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/hanota-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package leecode_cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeCN_0806_HanotaLcci {
	public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) { 
		hanoi(A.size(), A, C, B);
	}

	public static void hanoi(int N, List<Integer> from, List<Integer> to, List<Integer> other) {
		if (N == 1) {
			to.add(from.remove(from.size() - 1));
		} else {
			hanoi(N - 1, from, other, to);
			to.add(from.remove(from.size() - 1));
			hanoi(N - 1, other, to, from);
		}
	}

	public static void printH(int N, String from, String to, String other) {
		if (N == 1) {
			System.out.println("move " + N + " from " + from + " to " + to);
		} else {
			printH(N - 1, from, other, to);
			System.out.println("move " + N + " from " + from + " to " + to);
			printH(N - 1, other, to, from);
		}
	}

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>();
		List<Integer> B = new ArrayList<>();
		List<Integer> C = new ArrayList<>();
		A.add(3);
		A.add(2);
		A.add(1);
		A.add(0);
		hanota(A, B, C);
		System.out.println(A);
		System.out.println(B);
		System.out.println(C);

	}

}

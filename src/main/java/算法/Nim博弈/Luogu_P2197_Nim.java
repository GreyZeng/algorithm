/*
 * 甲，乙两个人玩 Nim 取石子游戏。
 *
 * Nim 游戏的规则是这样的：地上有 nn 堆石子（每堆石子数量小于 10^410 4
 * ），每人每次可从任意一堆石子里取出任意多枚石子扔掉，可以取完，不能不取。每次只能从一堆里取。最后没石子可取的人就输了。假如甲是先手，且告诉你这 nn
 * 堆石子的数量，他想知道是否存在先手必胜的策略。
 *
 * 输入格式 本题有多组测试数据。
 *
 * 第一行一个整数 T(T\le10)T(T≤10)，表示有 TT 组数据
 *
 * 接下来每两行是一组数据，第一行一个整数 nn，表示有 nn 堆石子，n\le10000n≤10000。
 *
 * 第二行有 nn 个数，表示每一堆石子的数量.
 *
 * 输出格式 共 TT 行，如果对于这组数据存在先手必胜策略则输出 Yes，否则输出 No，每个单词一行。
 *
 * 输入输出样例 输入 2 2 1 1 2 1 0 输出 No Yes
 */
package 算法.Nim博弈;

import java.util.Scanner;

// Nim博弈，给定一个正数数组arr，先手和后手每次可以选择在一个位置拿走若干值，这个值要大于0，但是要小于该处的剩余，谁最先拿空arr谁赢，根据arr返回谁赢
// https://www.luogu.com.cn/problem/P2197
// tips:
// 异或和如果是0的话，先手输
// 不是0的话，先手赢
// 先手总是可以让选择后的数据异或和为0
public class Luogu_P2197_Nim {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            System.out.println(preWin(arr));
        }
        in.close();
    }

    // 先手是否必胜
    public static String preWin(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        if (eor == 0) {
            return "No";
        } else {
            return "Yes";
        }
    }
}

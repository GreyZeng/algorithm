//链接：https://www.nowcoder.com/questionTerminal/3ee42c9155c340588729995561ace594
//        来源：牛客网
//
//        有N件物品和一个容量为V的背包。第i件物品的价值是C[i]，重量是W[i]。求解将哪些物品装入背包可使价值总和最大。
//
//
//        输入描述:
//        输入第一行数 N V (1 <=N <=500) (1<= V <= 10000)
//
//        输入 N行 两个数字 代表 C W (1 <= C <= 50000, 1 <= W <=10000)
//
//
//        输出描述:
//        输出最大价值
//        示例1
//        输入
//        5 10
//        8 6
//        10 4
//        4 2
//        5 4
//        5 3
//        输出
//        19
//        示例2
//        输入
//        1 1
//        10 2
//        输出
//        0
package nowcoder;

import java.util.Scanner;

public class NowCoder_Knapsack {
    // TODO
    public static int getMaxValue(int[] w, int[] v, int bag) {
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int bag = in.nextInt();
        int[] w = new int[N];
        int[] v = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = in.nextInt();
            w[i] = in.nextInt();
        }
        System.out.println(getMaxValue(w, v, bag));
        in.close();
    }

}

// 链接：https://www.nowcoder.com/questionTerminal/56ab932abac44c8aabf0af75f598a0b4
// 来源：牛客网

// 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
// 牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。牛牛想知道他最少需要涂染几个正方形。
// 如样例所示: s = RGRGR
// 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。

// 输入描述:
// 输入包括一个字符串s,字符串s长度length(1 ≤ length ≤ 50),其中只包括'R'或者'G',分别表示红色和绿色。

// 输出描述:
// 输出一个整数,表示牛牛最少需要涂染的正方形数量
// 示例1
// 输入
// RGRGR
// 输出
// 2
package git.snippet.nowcoder;

import java.util.Scanner;

// 笔记：https://www.cnblogs.com/greyzeng/p/16728462.html
// R都在左边，G都在右边，或者全G，全R
public class NowCoder_RedAndGreen {

    // 两个预处理数组
    // TODO 空间方面可以优化
    public static int minColors(String str) {
        if (str == null || str.length() <= 1) {
            return 0;
        }
        char[] strs = str.toCharArray();
        int N = strs.length;
        // leftG[i]表示左边包括i在内有几个G
        int[] leftG = new int[N];
        int[] rightR = new int[N];
        for (int i = 0; i < N; i++) {
            if (strs[i] == 'G') {
                if (i == 0) {
                    leftG[i]++;
                } else {
                    leftG[i] = leftG[i - 1] + 1;
                }
            } else {
                if (i != 0) {
                    leftG[i] = leftG[i - 1];
                }
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            if (strs[i] == 'R') {
                if (i == N - 1) {
                    rightR[i]++;
                } else {
                    rightR[i] = rightR[i + 1] + 1;
                }
            } else {
                if (i != N - 1) {
                    rightR[i] = rightR[i + 1];
                }
            }
        }

        // 全R或者全G的情况
        if (rightR[0] == N || leftG[N - 1] == N) {
            return 0;
        }

        int min = N;
        for (int i = 0; i < N; i++) {
            // 之所以要-1是因为重复算了i位置的处理情况
            min = Math.min(leftG[i] + rightR[i] - 1, min);
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String colors = in.nextLine();
        System.out.println(minColors(colors));
        in.close();
    }
}

/*链接：https://www.nowcoder.com/questionTerminal/6e732a9632bc4d12b442469aed7fe9ce
        来源：牛客网

        二叉树的前序、中序、后序遍历的定义： 前序遍历：对任一子树，先访问跟，然后遍历其左子树，最后遍历其右子树； 
        中序遍历：对任一子树，先遍历其左子树，然后访问根，最后遍历其右子树； 
        后序遍历：对任一子树，先遍历其左子树，然后遍历其右子树，最后访问根。 
        给定一棵二叉树的前序遍历和中序遍历，求其后序遍历（提示：给定前序遍历与中序遍历能够唯一确定后序遍历）。

        输入描述:
        两个字符串，其长度n均小于等于26。
        第一行为前序遍历，第二行为中序遍历。
        二叉树中的结点名称以大写字母表示：A，B，C....最多26个结点。


        输出描述:
        输入样例可能有多组，对于每组测试样例，
        输出一行，为后序遍历的字符串。
        示例1
        输入
        ABC
        BAC
        FDXEAG
        XDEFAG
        输出
        BCA
        XEDGAF*/
package nowcoder;

import java.util.HashMap;
import java.util.Scanner;

// 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
// 比如给定:
// int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
// int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
// {4,5,2,6,7,3,1}
// 假设 
// 先序遍历数组 {a, b, d, e, c, f}
// 中序遍历数组 {d, b, e, a, c, f}
// 后序遍历数组 {}
// a 一定是 后序数组的最后一个位置
// a在中序数组的位置是4， 这个位置把中序数组分成两部分，左边就是左树元素，右边就是右树元素
public class NowCoder_PreAndMidToPos {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            char[] pre = in.nextLine().toCharArray();
            char[] mid = in.nextLine().toCharArray();
            char[] pos = preAndMidToPos2(pre, mid);
            for (char c : pos) {
                System.out.print(c);
            }
            System.out.println();
        }
        in.close();
    }

    public static char[] preAndMidToPos2(char[] pre, char[] mid) {
        int N = pre.length;
        char[] pos = new char[N];
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(mid[i], i);
        }
        p2(pre, 0, N - 1, mid, 0, N - 1, pos, 0, N - 1, map);
        return pos;
    }

    public static void p2(char[] pre, int preL, int preR, char[] mid, int midL, int midR, char[] pos, int posL,
            int posR, HashMap<Character, Integer> map) {
        if (preL > preR) {
            return;
        }
        if (preL == preR) {
            pos[posL] = pre[preL];
            return;
        }
        pos[posR] = pre[preL];
        int index = map.get(pre[preL]);
        int gap = index - midL;
        p2(pre, preL + 1, preL + gap, mid, midL, midL + gap - 1, pos, posL, posL + gap - 1, map);
        p2(pre, preL + gap + 1, preR, mid, midL + gap + 1, midR, pos, posL + gap, posR - 1, map);
    }

    public static char[] preAndMidToPos(char[] pre, char[] mid) {
        int N = pre.length;
        char[] pos = new char[N];
        p(pre, 0, N - 1, mid, 0, N - 1, pos, 0, N - 1);
        return pos;
    }

    public static void p(char[] pre, int preL, int preR, char[] mid, int midL, int midR, char[] pos, int posL,
            int posR) {
        if (preL > preR) {
            return;
        }
        if (preL == preR) {
            pos[posL] = pre[preL];
            return;
        }
        pos[posR] = pre[preL];
        int index = midL;
        for (; index <= midR; index++) {
            if (mid[index] == pre[preL]) {
                break;
            }
        }
        int gap = index - midL;
        p(pre, preL + 1, preL + gap, mid, midL, midL + gap - 1, pos, posL, posL + gap - 1);
        p(pre, preL + gap + 1, preR, mid, midL + gap + 1, midR, pos, posL + gap, posR - 1);
    }
}

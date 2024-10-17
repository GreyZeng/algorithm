/*
 * 链接：https://www.nowcoder.com/questionTerminal/e13bceaca5b14860b83cbcc4912c5d4a 来源：牛客网
 *
 * 给定一颗二叉树，已知所有节点的值都不一样， 返回其中最大的且符合搜索二叉树条件的最大拓扑结构的大小。 拓扑结构是指树上的一个联通块。
 *
 * 输入描述: 第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。
 *
 * 以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
 *
 * ps:节点的编号就是节点的值。
 *
 *
 * 输出描述: 输出一个整数表示满足条件的最大拓扑结构的大小。 示例1 输入 3 2 2 1 3 1 0 0 3 0 0 输出 3
 *
 * 备注: 1≤n≤200000 1≤fa,lch,rch,root≤n
 */
package git.snippet.tree;

import java.util.HashMap;
import java.util.Scanner;

// 从二叉树的某个节点x开始，往下子节点都要的，叫子树；在二叉树上只要能连起来的任何结构，叫子拓扑结构；
// 返回二叉树上满足搜索二叉树性质的、最大子拓扑结构的节点数
// tips:
// 二叉树的递归套路，每个位置的贡献记录，左树的最右节点，右树的最左节点
// 复杂度 O(N) 因为任何一个节点过左树右边界都是不重复的
public class NowCoder_BiggestTopology {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        TreeNode root = new TreeNode(in.nextInt());
        HashMap<Integer, TreeNode> hashMap = new HashMap<>();
        hashMap.put(root.val, root);
        int[][] nodes = new int[count][3];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 3; j++) {
                int num = in.nextInt();
                if (num != 0) {
                    // 0表示无节点
                    hashMap.put(num, new TreeNode(num));
                }
                nodes[i][j] = num;
            }
        }
        for (int i = 0; i < count; i++) {
            int[] arr = nodes[i];
            TreeNode parent = hashMap.get(arr[0]);
            if (arr[1] != 0) {
                parent.left = hashMap.get(arr[1]);
            }
            if (arr[2] != 0) {
                parent.right = hashMap.get(arr[2]);
            }
        }
        root = hashMap.get(root.val);
        System.out.println(maxBSTSize(root));
        in.close();
    }

    public static int maxBSTSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 以头为节点的最大BST拓扑结构大小是多少
        int max = maxTopo(root, root);
        // 最大拓扑结构来自于左树
        max = Math.max(maxBSTSize(root.left), max);
        // 最大拓扑结构来自于右树
        max = Math.max(maxBSTSize(root.right), max);
        return max;
    }

    public static int maxTopo(TreeNode h, TreeNode n) {
        if (n == null || h == null) {
            return 0;
        }
        // 从h出发，到叶子节点，如果不是bst节点，则直接返回0（贡献值为0）
        if (!isBSTNode(h, n, n.val)) {
            return 0;
        }
        return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
    }

    public static boolean isBSTNode(TreeNode h, TreeNode n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.val > value ? h.left : h.right, n, value);
    }

    // h: 代表当前的头节点
    // t: 代表树 t[i][0]是i节点的父节点、t[i][1]是i节点的左孩子、t[i][2]是i节点的右孩子
    // m: i节点为头的最大bst拓扑结构大小 -> m[i]
    // 返回: 以h为头的整棵树上，最大bst拓扑结构的大小
    public static int maxBSTTopology(int h, int[][] t, int[] m) {
        if (h == 0) {
            return 0;
        }
        int l = t[h][1];
        int r = t[h][2];
        int c = 0;
        int p1 = maxBSTTopology(l, t, m);
        int p2 = maxBSTTopology(r, t, m);
        while (l < h && m[l] != 0) {
            l = t[l][2];
        }
        if (m[l] != 0) {
            c = m[l];
            while (l != h) {
                m[l] -= c;
                l = t[l][0];
            }
        }
        while (r > h && m[r] != 0) {
            r = t[r][1];
        }
        if (m[r] != 0) {
            c = m[r];
            while (r != h) {
                m[r] -= c;
                r = t[r][0];
            }
        }
        m[h] = m[t[h][1]] + m[t[h][2]] + 1;
        return Math.max(Math.max(p1, p2), m[h]);
    }

    // 用数组实现
    // public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // int n = sc.nextInt();
    // int h = sc.nextInt();
    // int[][] tree = new int[n + 1][3];
    // for (int i = 1; i <= n; i++) {
    // int c = sc.nextInt();
    // int l = sc.nextInt();
    // int r = sc.nextInt();
    // tree[l][0] = c;
    // tree[r][0] = c;
    // tree[c][1] = l;
    // tree[c][2] = r;
    // }
    // System.out.println(maxBSTTopology(h, tree, new int[n + 1]));
    // sc.close();
    // }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}

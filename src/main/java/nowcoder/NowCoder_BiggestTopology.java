/*链接：https://www.nowcoder.com/questionTerminal/e13bceaca5b14860b83cbcc4912c5d4a
        来源：牛客网

        给定一颗二叉树，已知所有节点的值都不一样， 返回其中最大的且符合搜索二叉树条件的最大拓扑结构的大小。
        拓扑结构是指树上的一个联通块。

        输入描述:
        第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

        以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

        ps:节点的编号就是节点的值。


        输出描述:
        输出一个整数表示满足条件的最大拓扑结构的大小。
        示例1
        输入
        3 2
        2 1 3
        1 0 0
        3 0 0
        输出
        3

        备注:
        1≤n≤200000
        1≤fa,lch,rch,root≤n*/
package nowcoder;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Young
 * @version 1.0
 * @date 2021/2/13 17:09
 */
public class NowCoder_BiggestTopology {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        Node root = new Node(in.nextInt());
        HashMap<Integer, Node> hashMap = new HashMap<>();
        hashMap.put(root.value, root);
        int[][] nodes = new int[count][3];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 3; j++) {
                int num = in.nextInt();
                if (num != 0) {
                    // 0表示无节点
                    hashMap.put(num, new Node(num));
                }
                nodes[i][j] = num;
            }
        }
        for (int i = 0; i < count; i++) {
            int[] arr = nodes[i];
            Node parent = hashMap.get(arr[0]);
            if (arr[1] != 0) {
                parent.left = hashMap.get(arr[1]);
            }
            if (arr[2] != 0) {
                parent.right = hashMap.get(arr[2]);
            }
        }
        root = hashMap.get(root.value);
        System.out.println(maxBSTSize(root));
        in.close();
    }

    public static int maxBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        // 以头为节点的最大BST拓扑结构大小是多少
        int max = maxTopo(head, head);
        // 最大拓扑结构来自于左树
        max = Math.max(maxBSTSize(head.left), max);
        // 最大拓扑结构来自于右树
        max = Math.max(maxBSTSize(head.right), max);
        return max;
    }

    public static int maxTopo(Node h, Node n) {
        if (n == null || h == null) {
            return 0;
        }
        // 从h出发，到叶子节点，如果不是bst节点，则直接返回0（贡献值为0）
        if (!isBSTNode(h, n, n.value)) {
            return 0;
        }
        return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
    }

    public static boolean isBSTNode(Node h, Node n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, value);
    }
}

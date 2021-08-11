/*链接：https://www.nowcoder.com/questionTerminal/380d49d7f99242709ab4b91c36bf2acc
        来源：牛客网

        输入描述:
        第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

        以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

        ps:节点的编号就是节点的值。


        输出描述:
        1 <= n <= 1000000
        n1≤fa,lch,rch,root≤n
        示例1
        输入
        3 2
        2 1 3
        1 0 0
        3 0 0
        输出
        3
        */
package nowcoder;

import java.util.HashMap;
import java.util.Scanner;

// https://www.nowcoder.com/questionTerminal/380d49d7f99242709ab4b91c36bf2acc
public class NowCoder_MaxSubBSTSize {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node head = constructTree(sc, n);
        System.out.println(maxSubBSTSize(head));
        sc.close();
    }

    public static Node constructTree(Scanner sc, int n) {
        HashMap<Integer, Node> map = new HashMap<>();
        int rootVal = sc.nextInt();
        Node root = new Node(rootVal);
        map.put(rootVal, root);
        for (int i = 0; i < n; i++) {
            int nodeVal = sc.nextInt();
            int leftVal = sc.nextInt();
            int rightVal = sc.nextInt();
            if (map.containsKey(nodeVal)) {
                Node tmpNode = map.get(nodeVal);
                Node leftNode = leftVal == 0 ? null : new Node(leftVal);
                Node rightNode = rightVal == 0 ? null : new Node(rightVal);
                tmpNode.left = leftNode;
                tmpNode.right = rightNode;
                if (leftVal != 0)
                    map.put(leftVal, leftNode);
                if (rightVal != 0)
                    map.put(rightVal, rightNode);
            }
        }
        return root;
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxSubBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubBSTSize;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int maxSubBSTSize = 1;
        int max = head.value;
        int min = head.value;
        boolean isBST = true;
        if (left != null) {
            maxSubBSTSize = Math.max(left.maxSubBSTSize, maxSubBSTSize);
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            isBST = isBST && left.isBST && (head.value > left.max);
        }
        if (right != null) {
            maxSubBSTSize = Math.max(right.maxSubBSTSize, maxSubBSTSize);
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            isBST = isBST && right.isBST && (head.value < right.min);
        }
        if (isBST) {
            maxSubBSTSize = Math.max(
                    (left != null ? left.maxSubBSTSize : 0) + (right != null ? right.maxSubBSTSize : 0) + 1,
                    maxSubBSTSize);
        }
        return new Info(maxSubBSTSize, max, min, isBST);
    }

    public static class Info {
        public int maxSubBSTSize;
        public boolean isBST;
        public int max;
        public int min;

        public Info(int maxSubBSTSize, int max, int min, boolean isBST) {
            this.isBST = isBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
        }

    }

}

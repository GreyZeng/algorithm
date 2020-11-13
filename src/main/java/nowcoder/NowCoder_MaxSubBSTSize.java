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
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Node root = new Node(scanner.nextInt());
        HashMap<Integer, Node> hashMap = new HashMap<>();
        hashMap.put(root.value, root);
        int[][] nodes = new int[count][3];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 3; j++) {
                int v = scanner.nextInt();
                if (v != 0)
                    hashMap.put(v, new Node(v));
                nodes[i][j] = v;
            }
        }
        for (int i = 0; i < count; i++) {
            int[] arr = nodes[i];
            Node fakeRoot = hashMap.get(arr[0]);
            if (arr[1] != 0)
                fakeRoot.left = hashMap.get(arr[1]);
            if (arr[2] != 0)
                fakeRoot.right = hashMap.get(arr[2]);
        }
        root = hashMap.get(root.value);
        System.out.println(maxSubBSTSize(root));
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public boolean isBST;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info(boolean isBST, int maxSubBSTSize, int min, int max) {
            this.isBST = isBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    public static int maxSubBSTSize(Node head) {
        return p(head).maxSubBSTSize;
    }

    public static Info p(Node head) {
        if (null == head) {
            return null;
        }
        Info left = p(head.left);
        boolean isBST = true;
        int maxSubBSTSize = 0;
        int max = head.value;
        int min = head.value;
        if (left != null) {
            min = Math.min(left.min, min);
            max = Math.max(left.max, max);
            isBST = (left.isBST && head.value > left.max);
        }
        Info right = p(head.right);
        if (right != null) {
            min = Math.min(right.min, min);
            max = Math.max(right.max, max);
            isBST = isBST && (right.isBST && head.value < right.min);
        }
        if (isBST) {
            maxSubBSTSize =
                    (right != null ? right.maxSubBSTSize : 0)
                            +
                            (left != null ? left.maxSubBSTSize : 0)
                            + 1;
        } else {
            maxSubBSTSize = Math.max((right != null ? right.maxSubBSTSize : 0), (left != null ? left.maxSubBSTSize : 0));
        }

        return new Info(isBST, maxSubBSTSize, min, max);
    }
}

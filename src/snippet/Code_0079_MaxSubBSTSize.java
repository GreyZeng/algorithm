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
package snippet;

import java.util.ArrayList;

// https://www.nowcoder.com/questionTerminal/380d49d7f99242709ab4b91c36bf2acc
public class Code_0079_MaxSubBSTSize {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return p(head).maxSubBSTSize;
    }

    public static Info p(Node head) {
        if (head == null) {
            return null;
        }
        Info left = p(head.left);
        Info right = p(head.right);
        int maxSize = 1;
        int max = head.value;
        int min = head.value;
        boolean isBST = true;
        if (left != null) {
            isBST = left.isBST && left.max < head.value;
            max = Math.max(left.max, max);
            min = Math.min(left.min, min);
            maxSize = Math.max(maxSize, left.maxSubBSTSize);
        }
        if (right != null) {
            isBST = isBST && right.isBST && right.min > head.value;
            max = Math.max(right.max, max);
            min = Math.min(right.min, min);
            maxSize = Math.max(maxSize, right.maxSubBSTSize);
        }
        if (isBST) {
            maxSize = Math.max(
                    (left != null ? left.maxSubBSTSize : 0) + (right != null ? right.maxSubBSTSize : 0) + 1,
                    maxSize);
        }
        return new Info(maxSize, max, min, isBST);
    }

    public static class Info {
        public Info(int maxSubBSTSize, int max, int min, boolean isBST) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }

        private int maxSubBSTSize;
        private int max;
        private int min;
        private boolean isBST;

    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}

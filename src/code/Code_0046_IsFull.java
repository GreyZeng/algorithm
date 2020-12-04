package code;

public class Code_0046_IsFull {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFull(Node head) {
        if (head == null) {
            return true;
        }
        Info all = process(head);
        int height = all.height;
        int nodes = all.nodes;
        return ((1 << height) - 1) == nodes;
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        int height = Math.max(left.height, right.height) + 1;
        int nodes = left.nodes + right.nodes + 1;
        return new Info(height, nodes);
    }
}

package leetcode;

import java.util.LinkedList;

public class LeetCode_0116_PopulatingNextRightPointersInEachNode {

    // 可以自定义数据结构
    public static Node connect(Node root) {
        if (null == root) {
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int size = 0;
        int i = 0;
        Node cur = null;
        while (!queue.isEmpty()) {
            size = queue.size();
            Node pre = null;
            for (i = 0; i < size; i++) {
                cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }
        return root;
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    }

}

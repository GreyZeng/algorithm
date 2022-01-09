package snippet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点 发现每一层的开始或者每一层的结束位置 可以用map实现，也可以不用map实现
 */
public class Code_0039_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 使用Hash表
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        HashMap<Node, Integer> map = new HashMap<>();
        int currentLevel = 1;
        map.put(head, currentLevel);
        int currentWidth = 0; // 遍历到下一层的时候再结算上一层的宽度
        int max = 0;
        while (!queue.isEmpty()) {
            Node c = queue.poll();
            int levelOfCur = map.get(c);
            if (c.left != null) {
                queue.offer(c.left);
                map.put(c.left, map.get(c) + 1);
            }
            if (c.right != null) {
                queue.offer(c.right);
                map.put(c.right, map.get(c) + 1);
            }
            if (levelOfCur == currentLevel) {
                currentWidth++;
            } else {
                max = Math.max(max, currentWidth);
                currentLevel++;
                currentWidth = 1;
            }
        }
        max = Math.max(currentWidth, max);
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        int max = 1;
        Node nextEnd = null; //
        Node curEnd = head; //
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            Node c = queue.poll();
            if (c.left != null) {
                queue.offer(c.left);
                nextEnd = c.left;
            }
            if (c.right != null) {
                queue.offer(c.right);
                nextEnd = c.right;
            }
            curLevelNodes++;
            if (curEnd == c) {
                curEnd = nextEnd;
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
            }
        }
        return max;
    }
}

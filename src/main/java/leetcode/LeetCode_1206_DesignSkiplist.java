package leetcode;


import java.util.*;

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/5/11
 * @since
 */
// FIXME
public class LeetCode_1206_DesignSkiplist {

}
class Skiplist {
    private ArrayList<Node> heads;
    private final static double POSSIBLE = 0.5d;

    public Skiplist() {
        heads = new ArrayList<>();
        heads.add(new Node(-1));
    }

    public static class Node {
        private int val;
        private Node up, down, left, right;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node getLessOrEqual(int target) {
        Node cur = heads.get(heads.size() - 1);
        Node pre = cur;
        while (cur != null) {

            if (cur.val <= target) {
                pre = cur;
                cur = cur.right;
            } else {
                if (pre.down != null) {
                    cur = pre.down;
                    pre = cur;

                } else {
                    break;
                }
            }
        }
        return pre;

    }

    public boolean search(int target) {
        return getLessOrEqual(target).val == target;
    }

    public boolean roll() {
        return Math.random() < POSSIBLE;
    }

    public void add(int num) {
        Node lessOrEqual = getLessOrEqual(num);
        if (lessOrEqual.val == num) {
            return;
        }
        Node newNode = new Node(num);
        // 无论如何，最底层都要连起来的
        connect(lessOrEqual, newNode);
        // 掷骰子随机确定其他的层数
        Node pre = lessOrEqual;
        Node cur = newNode;
        while (roll()) {
            while (pre.left != null && pre.up == null) {
                pre = pre.left;
            }
            if (pre.left == null) {
                // 到达heads节点
                Node head = new Node(-1);
                pre.up = head;
                head.down = pre;
                heads.add(head);
            }
            pre = pre.up;
            Node toInsert = new Node(num);
            cur.up = toInsert;
            toInsert.down = cur;
            cur = cur.up;
            pre.right = cur;
            cur.left = pre;

        }


    }

    private void connect(Node pre, Node cur) {
        Node right = pre.right;
        pre.right = cur;
        cur.left = pre;
        if (right != null) {
            // lessOrEqual不是最后一个节点
            cur.right = right;
            right.left = cur;
        }

    }


    public boolean erase(int num) {
        Node lessOrEqual = getLessOrEqual(num);
        if (lessOrEqual.val != num) {
            return false;
        }

        Node cur = lessOrEqual;
        while (cur != null) {
            Node pre = cur.left;
            Node after = cur.right;

            pre.right = after;
            if (after != null) {
                after.left = pre;
            }
            cur = cur.up;
        }
        return true;
    }
}
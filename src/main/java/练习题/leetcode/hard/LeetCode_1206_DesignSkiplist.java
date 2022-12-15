package 练习题.leetcode.hard;


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
public class LeetCode_1206_DesignSkiplist {
    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(1);
        skiplist.add(1);
        skiplist.add(1);
        skiplist.erase(1);
        System.out.println(skiplist.search(1));
    }


}

class Skiplist {
    private final ArrayList<Node> heads;
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
        while (cur != null) {
            if (cur.right == null || cur.right.val > target) {
                if (cur.val <= target) {
                    if (cur.down != null) {
                        cur = cur.down;
                    } else {
                        break;
                    }
                }
            } else {
                cur = cur.right;
            }
        }
        return cur;

    }

    public boolean search(int target) {
        return getLessOrEqual(target).val == target;
    }

    public boolean roll() {
        return Math.random() < POSSIBLE;
    }

    public void add(int num) {
        // 如果节点存在则不增加
        Node lessOrEqual = getLessOrEqual(num);
        // 支持重复数据插入，所以这里不能直接判断存在就退出
      /*  if (lessOrEqual.val == num) {
            return;
        }*/

        // 到这里说明节点不存在，先建出节点
        Node newNode = new Node(num);

        // 无论如何，最底层都要连起来的
        Node right = lessOrEqual.right;
        lessOrEqual.right = newNode;
        newNode.left = lessOrEqual;
        if (right != null) {
            // lessOrEqual不是最后一个节点
            newNode.right = right;
            right.left = newNode;
        }


        // 掷骰子随机确定其他的层数
        Node pre = lessOrEqual;
        Node cur = newNode;
        while (roll()) {
            while (pre.left != null && pre.up == null) {
                pre = pre.left;
            }
            if (pre.left == null) {
                // 到达heads节点
                final Node head = new Node(-1);
                pre.up = head;
                head.down = pre;
                heads.add(head);
            }
            pre = pre.up;
            Node toInsert = new Node(num);
            cur.up = toInsert;
            toInsert.down = cur;
            cur = cur.up;
            pre.right = toInsert;
            cur.left = pre;
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
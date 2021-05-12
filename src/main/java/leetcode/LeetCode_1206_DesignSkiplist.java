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
// TODO
public class LeetCode_1206_DesignSkiplist {

}

class Skiplist {
    public ArrayList<Node> heads;
    public static final double POSSIBLE = 0.5d;

    public Skiplist() {
        heads = new ArrayList<>();
        heads.add(new Node(-1));
    }

    public boolean roll() {
        return Math.random() > POSSIBLE;
    }

    // 从最高层开始，获取到小于等于某个值的最右边位置
    public Node getLessOrEqual(final int target) {
        Node cur = heads.get(heads.size() - 1);
        Node pre = null;
        while (cur != null) {
            pre = cur;
            if (cur.val <= target) {
                cur = cur.right;
            } else {
                cur = cur.down;
            }
        }
        return pre;
    }

    public boolean search(int target) {
        return getLessOrEqual(target).val == target;
    }

    public void add(int num) {
        if (search(num)) {
            return;
        }

        while (roll()) {

        }
    }

    public boolean erase(int num) {
        if (!search(num)) {
            // 没有找到，无法删除，返回false
            return false;
        }
        return true;
    }

    public static class Node {
        private int val;
        private Node left, right, up, down;

        public Node(int v) {
            val = v;
        }
    }
}


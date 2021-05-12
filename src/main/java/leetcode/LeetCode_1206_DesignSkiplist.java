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
public class LeetCode_1206_DesignSkiplist {

}

class Skiplist {
    private static final double DEFAULT_PROB = 0.5;
    private final Random rand = new Random();
    private final List<Node> sentinels = new ArrayList<>();

    {
        sentinels.add(new Node(Integer.MIN_VALUE));
    }

    public boolean search(int target) {
        Node smallerOrEquals = getSmallerOrEquals(target);
        return smallerOrEquals.val == target;
    }

    public void add(int num) {
        Node cur = getSmallerOrEquals(num);
        // cur 最下层，比他小或者等于 num
        final Node toInsert = new Node(num);
        append(cur, toInsert);
        // populate the level
        populateLevelUp(toInsert);
    }
    private void populateLevelUp2(final Node toInsert) {
        Node curPrev = toInsert.left, cur = toInsert;

        while (flipCoin()) {
            while (curPrev.left != null && curPrev.up == null) {
                curPrev = curPrev.left;
            }
            if (curPrev.up == null) {
                final Node newSentinel = new Node(Integer.MIN_VALUE);
                curPrev.up = newSentinel;
                newSentinel.down = curPrev;
                sentinels.add(curPrev.up);
            }
            curPrev = curPrev.up;
            final Node newNode = new Node(toInsert.val);
            cur.up = newNode;
            newNode.down = cur;
            cur = cur.up;
            curPrev.right = cur;
            cur.left = curPrev;
        }
    }

    private void populateLevelUp(final Node toInsert) {
        Node curPrev = toInsert.left, cur = toInsert;

        while (flipCoin()) {
            while (curPrev.left != null && curPrev.up == null) {
                curPrev = curPrev.left;
            }
            if (curPrev.up == null) {
                final Node newSentinel = new Node(Integer.MIN_VALUE);
                curPrev.up = newSentinel;
                newSentinel.down = curPrev;
                sentinels.add(curPrev.up);
            }
            curPrev = curPrev.up;
            Node temp = curPrev.right; //change as per this comment

            final Node newNode = new Node(toInsert.val);
            cur.up = newNode;
            newNode.down = cur;
            cur = cur.up;
            curPrev.right = cur;
            cur.left = curPrev;
            if (temp != null) //added
            {
                cur.right = temp; //change as per this comment
                temp.left = cur; //change as per this comment
            }
        }
    }

    private void append(Node prev, Node cur) {
        final Node next = prev.right;
        prev.right = cur;
        cur.left = prev;
        if (next != null) {
            next.left = cur;
            cur.right = next;
        }
    }

    public boolean erase(int num) {
        final Node toRemove = getSmallerOrEquals(num);
        if (toRemove.val != num) {
            return false;
        }
        Node cur = toRemove;
        while (cur != null) {
            final Node prev = cur.left, next = cur.right;
            prev.right = next;
            if (next != null) {
                next.left = prev;
            }
            cur = cur.up;
        }
        return true;
    }

    private Node getSmallerOrEquals(final int target) {
        Node cur = getSentinel();
        while (cur != null) {
            if (cur.right == null || cur.right.val > target) {
                if (cur.down == null) break;
                cur = cur.down;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }

    private boolean flipCoin() {
        return rand.nextDouble() < DEFAULT_PROB;
    }

    private Node getSentinel() {
        return sentinels.get(sentinels.size() - 1);
    }

    @Override
    public String toString() {
        Node node = sentinels.get(0);
        final StringBuilder sb = new StringBuilder();
        while (node != null) {
            Node itr = node;
            while (itr != null) {
                sb.append(itr.val).append(",");
                itr = itr.up;
            }
            sb.append("\n");
            node = node.right;
        }
        return sb.toString();
    }

    private static final class Node {
        private final int val;
        private Node left, right, up, down;

        private Node(int val) {
            this.val = val;
        }
    }

    public Skiplist() {

    }


}


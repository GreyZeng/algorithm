package d链表;

// FIXME
//The number of nodes in the list is n.
//1 <= k <= n <= 5000
//0 <= Node.val <= 1000
// K个节点的组内逆序调整问题
// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0025_ReverseNodesInKGroup {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {

    }

    // 从start开始，数够k个，然后返回。
    // 例如：
    // start->b->c->d->e
    // k = 3
    // 从start开始，数够3个，所以返回c节点
    // start->b->c
    // k = 6
    // 不够6个，所以返回 null
    public ListNode getKGroupEnd(ListNode start, int k) {
        ListNode cur = start;
        while (cur != null && k != 1) {
            k--;
            cur = cur.next;
        }
        return cur;
    }

    // ....->a->b->c->d->e....
    // 假设start = a, end = d
    // 经过如下方法，会变成
    // ...d->c->b->a->e.....
    public void reverse(ListNode start, ListNode end) {
        ListNode pre = end.next;
        ListNode cur = start;
        while (cur != end.next) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
    }
}

package d链表;

// K个节点的组内逆序调整问题
// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0025_ReverseNodesInKGroup {

    public class ListNode {
        public int val;
        public ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode end = getKGroupEnd(head, k);
        if (end == null) {
            return head;
        }
        ListNode cur = head;
        head = end;
        reverse(cur, end);
        ListNode lastEnd = cur;
        while (lastEnd.next != null) {
            cur = lastEnd.next;
            end = getKGroupEnd(cur, k);
            if (end == null) {
                break;
            }
            reverse(cur, end);
            lastEnd.next = end;
            lastEnd = cur;
        }
        return head;
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
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    // ....->a->b->c->d->e....
    // 假设start = a, end = d
    // 经过如下方法，会变成
    // ...d->c->b->a->e.....
    public void reverse(ListNode start, ListNode end) {
        if (start == null) {
            return;
        }
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode tmp;
        while (cur != end) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        start.next = end;
    }
}



package d链表;

// TODO
// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
// 笔记：https://www.cnblogs.com/greyzeng/p/16629407.html
public class LeetCode_0025_ReverseNodesInKGroup {

    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        // 第一组凑齐了！
        head = end;
        reverse(start, end);
        // 上一组的结尾节点
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
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
    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    // ....->a->b->c->d->e....
    // 假设start = a, end = d
    // 经过如下方法，会变成
    // ...d->c->b->a->e.....
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

}
